package flinkprimary.dataset.discache

import org.apache.commons.io.FileUtils
import org.apache.flink.api.common.functions.RichMapFunction
import org.apache.flink.api.scala.{ExecutionEnvironment, createTypeInformation}
import org.apache.flink.configuration.Configuration



object DistributedCacheApp {

  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment

    val filePath = "file:///home/willhope/data/input/hello.txt"

    env.registerCachedFile(filePath,"cache-scala")

    val data = env.fromElements("hadoop","spark","flink","storm")


    data.map(new RichMapFunction[String,String] {

      // step2：在open方法中获取到分布式缓存的内容即可
      override def open(parameters: Configuration): Unit = {
        val dcFile = getRuntimeContext.getDistributedCache().getFile("cache-scala")

        val lines = FileUtils.readLines(dcFile)  // java


        /**
         * 此时会出现一个异常：java集合和scala集合不兼容的问题
         */
        import scala.collection.JavaConverters._
        for(ele <- lines.asScala){ // scala
          println(ele)
        }
      }

      override def map(value: String): String = {
        value
      }
    }).print()

  }


}
