package flinkprimary.datastream.source

import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, createTypeInformation}

object DataStreamSourceApp {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

//    socketFunction(env)
//    nonParallelSourceFunction(env)
//    parallelSourceFunction(env)
    richParallelSourceFunction(env)
    env.execute("DataStreamSourceApp")
  }

  def socketFunction(environment: StreamExecutionEnvironment): Unit ={
    val data = environment.socketTextStream("localhost",9999)
    //设置并行度，如果不设置，本机有多少个线程数，就有多少个并行
    data.print().setParallelism(1)

  }


  def nonParallelSourceFunction(env: StreamExecutionEnvironment) = {
    //非并行所以不能设置setParallelism，若设置则报错
    val data = env.addSource(new CustomNonParallelSourceFunction)//.setParallelism(2)
    data.print().setParallelism(1)
  }

  def parallelSourceFunction(env: StreamExecutionEnvironment) = {
    val data = env.addSource(new CustomParallelSourceFunction).setParallelism(2)
    data.print().setParallelism(1)
  }

  def richParallelSourceFunction(env : StreamExecutionEnvironment)={
    val data = env.addSource(new CustomParallelSourceFunction).setParallelism(2)
    data.print()
  }

}
