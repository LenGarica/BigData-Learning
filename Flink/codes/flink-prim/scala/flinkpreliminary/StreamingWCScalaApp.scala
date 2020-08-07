package com.zyx.flink.scala

import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * 使用Scala开发flink的实时处理应用程序
 */
object StreamingWCScalaApp {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val text = env.socketTextStream("localhost",9990)

    text.flatMap(_.split(",")).map((_,1)).keyBy(0)
      .timeWindow(Time.seconds(5)).sum(1).print().setParallelism(1)


    env.execute("StreamingWCScalaApp")

  }

}
