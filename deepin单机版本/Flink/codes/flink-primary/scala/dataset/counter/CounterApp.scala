package flinkprimary.dataset.counter

import org.apache.flink.api.common.accumulators.LongCounter
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala._
import org.apache.flink.configuration.Configuration
import org.apache.flink.core.fs.FileSystem.WriteMode

object CounterApp {

  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    val data = env.fromElements("hadoop","flink","spark","storm")

    val info = data.map(new RichMapFunction[String , String]() {

      //定义计数器
      val counter = new LongCounter()

      override def open(parameters : Configuration) : Unit = {
        //注册计数器
        getRuntimeContext.addAccumulator("ele-counts-scala",counter)
      }

      override def map(value: String): String = {
        counter.add(1)
        value
      }
    })

    val filePath = "file:///home/willhope/data/counter-out-scala"
    info.writeAsText(filePath,WriteMode.OVERWRITE).setParallelism(5)
    val jobResult = env.execute("CounterApp")

    //获取计数器
    val num = jobResult.getAccumulatorResult[Long]("ele-counts-scala")
    print(num)
  }

}
