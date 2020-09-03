package com.imooc.flink.project

import java.text.SimpleDateFormat
import java.util.{Date, Properties}

import org.apache.flink.api.common.functions.RuntimeContext
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.watermark.Watermark
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.streaming.connectors.elasticsearch.{ElasticsearchSinkFunction, RequestIndexer}
import org.apache.flink.streaming.connectors.elasticsearch6.ElasticsearchSink
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.util.Collector
import org.apache.http.HttpHost
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.Requests
import org.slf4j.LoggerFactory

import scala.collection.mutable.ArrayBuffer

/**
 * Author: Michael PK
 */
object LogAnalysis {

  // 在生产上记录日志建议采用这种方式
  val logger = LoggerFactory.getLogger("LogAnalysis")

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //env.setParallelism(3)  配置在这里的并行度，并行度别写死，最好是外面传递进来
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)

    import org.apache.flink.api.scala._
    val topic = "test"

    val properties = new Properties()
    properties.setProperty("bootstrap.servers", "localhost:9092")
    properties.setProperty("group.id", "test")

    val consumer = new FlinkKafkaConsumer[String](topic,
      new SimpleStringSchema(),
      properties)

    // 接收Kafka数据
    val data = env.addSource(consumer) //.setParallelism(4)

    val logData = data.map(x => {
      val splits = x.split("\t")
      val level = splits(2)

      val timeStr = splits(3)
      var time = 0l
      try {
        val sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        time = sourceFormat.parse(timeStr).getTime
      } catch {
        case e: Exception => {
          logger.error(s"time parse error: $timeStr", e.getMessage)
        }
      }

      val domain = splits(5)
      val traffic = splits(6).toLong

      (level, time, domain, traffic)
    }).filter(_._2 != 0).filter(_._1 == "E")
      .map(x => {
        (x._2, x._3, x._4)   // 1 level(抛弃)  2 time  3 domain   4 traffic
      })

    /**
     * 在生产上进行业务处理的时候，一定要考虑处理的健壮性以及你数据的准确性
     * 脏数据或者是不符合业务规则的数据是需要全部过滤掉之后
     * 再进行相应业务逻辑的处理
     *
     * 对于我们的业务来说，我们只需要统计level=E的即可
     * 对于level非E的，不做为我们业务指标的统计范畴
     *
     * 数据清洗：就是按照我们的业务规则把原始输入的数据进行一定业务规则的处理
     * 使得满足我们的业务需求为准
     */

    //logData.print().setParallelism(1)

    val resultData = logData.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks[(Long,String,Long)]{

      val maxOutOfOrderness = 10000L // 3.5 seconds

      var currentMaxTimestamp: Long = _  // scala里面的_是非常重要的，请参考Scala实战

      override def getCurrentWatermark: Watermark = {
        new Watermark(currentMaxTimestamp - maxOutOfOrderness)
      }

      override def extractTimestamp(element: (Long, String, Long), previousElementTimestamp: Long): Long = {
        val timestamp = element._1
        currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp)
        timestamp
      }
    }).keyBy(1) //此处是按照域名进行keyBy的
      .window(TumblingEventTimeWindows.of(Time.seconds(60)))
      .apply(new WindowFunction[(Long,String,Long),(String,String,Long),Tuple, TimeWindow] {
        override def apply(key: Tuple, window: TimeWindow, input: Iterable[(Long, String, Long)], out: Collector[(String, String, Long)]): Unit = {

          val domain = key.getField(0).toString
          var sum = 0l

          val times = ArrayBuffer[Long]()

          val iterator = input.iterator
          while(iterator.hasNext) {
            val next = iterator.next()
            sum += next._3  // traffic求和

            // TODO... 是能拿到你这个window里面的时间的  next._1
            times.append(next._1)
          }

          /**
           *  第一个参数：这一分钟的时间 2019-09-09 20:20
           *  第二个参数：域名
           *  第三个参数：traffic的和
           */
          val time = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(times.max))
          out.collect((time,domain,sum))
        }
      })

    val httpHosts = new java.util.ArrayList[HttpHost]
    httpHosts.add(new HttpHost("localhost", 9200, "http"))

    val esSinkBuilder = new ElasticsearchSink.Builder[(String,String,Long)](
      httpHosts,
      new ElasticsearchSinkFunction[(String,String,Long)] {
        def createIndexRequest(element: (String,String,Long)): IndexRequest = {
          val json = new java.util.HashMap[String, Any]
          json.put("time", element._1)
          json.put("domain", element._2)
          json.put("traffics", element._3)

          val id = element._1 + "-" + element._2

          return Requests.indexRequest()
            .index("cdn")
            .`type`("traffic")
            .id(id)
            .source(json)
        }

        override def process(t: (String, String, Long), runtimeContext: RuntimeContext, requestIndexer: RequestIndexer): Unit = {
          requestIndexer.add(createIndexRequest(t))
        }
      }
    )

    // configuration for the bulk requests; this instructs the sink to emit after every element, otherwise they would be buffered
    esSinkBuilder.setBulkFlushMaxActions(1)

    // finally, build and add the sink to the job's pipeline
    resultData.addSink(esSinkBuilder.build) //.setParallelism(5)

    env.execute("LogAnalysis")
  }
}
