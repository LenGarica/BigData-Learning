package flinkprimary.datastream.sink
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.api.scala._
object CustomSinkToMySQL {

  def main(args: Array[String]): Unit = {


    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val data = env.socketTextStream("localhost",7777)

    val ds: DataStream[Student] = data.map(x => {
      val strings = x.split(",")
      Student(strings(0).toInt, strings(1), strings(2).toInt)
    })

    ds.addSink(new SinkToMySQL)
    //.setParallelism(1)
    env.execute("CustomSinkToMySQL")



  }


}
