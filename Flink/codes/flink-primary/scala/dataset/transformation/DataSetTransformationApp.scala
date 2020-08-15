package flinkprimary.dataset.transformation

import flinkprimary.DBUtils
import org.apache.flink.api.common.operators.Order
import org.apache.flink.api.scala._

import scala.collection.mutable.ListBuffer

object DataSetTransformationApp {


  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
//    mapFunction(env)
//    filterFunction(env)
//    mapPartitionFunction(env)
//    firstFunction(env)
//    flatMapFunction(env)
//    distinctFunction(env)
//    joinFunction(env)
//    outerJoinFunction(env)
    crossFunction(env)
  }

  /**
   * 对数据集中每个元素进行操作
   * @param env
   */
  def mapFunction(env: ExecutionEnvironment): Unit ={
    val data = env.fromCollection(List(1,2,3,4,5,6,7,8,9,10))
    data.map(_+1).print()
  }

  /**
   * 对集合中的数据进行过滤
   * @param env
   */
  def filterFunction(env: ExecutionEnvironment) : Unit ={
    val data = env.fromCollection(List(1,2,3,4,5,6,7,8,9,10))
    data.map(_+1).filter(_>5).print()
  }

  /**
   * 将100个元素放入数据库中，此处模拟一个DB工具类
   * 使用分区，并行处理大量数据，提高效率
   * @param env
   */
  def mapPartitionFunction(env: ExecutionEnvironment) : Unit = {

    val students = new ListBuffer[String]
    for (i <- 1 to 100){
      students.append("student:" + i)
    }
    //并行度设置几个，就有几个分区
    val data = env.fromCollection(students).setParallelism(4)
    data.mapPartition(x => {
      //每个元素要存储到数据库中，要先获取connection
      val connection = DBUtils.getConnection()
      println(connection + "......")

      DBUtils.returnConnection(connection)
      x
    }).print()
  }

  /**
   * first算子的使用，取出前三条
   * @param env
   */
  def firstFunction(env: ExecutionEnvironment) : Unit ={
    val info = ListBuffer[(Int ,String)]()
    info.append((1,"hadoop"))
    info.append((1,"spark"))
    info.append((1,"flink"))
    info.append((2,"java"))
    info.append((2,"scala"))
    info.append((2,"python"))
    info.append((3,"linux"))
    info.append((4,"windows"))
    val data = env.fromCollection(info)
    data.first(3).print()
    //分组后，从组里取出前2个
    data.groupBy(0).first(2).print()
    //升序排列分组，从组里取出前2个
    data.groupBy(0).sortGroup(1,Order.ASCENDING).first(2).print()
  }

  /**
   * flatMapFunction的学习，将一个字符串拆分成多个字符串
   * @param env
   */
  def flatMapFunction(env: ExecutionEnvironment): Unit = {
    val info = ListBuffer[String]()
    info.append("hadoop,spark")
    info.append("hadoop,flink")
    info.append("flink,flink")

    val data = env.fromCollection(info)
    data.flatMap(_.split(",")).map((_,1)).groupBy(0).sum(1).print()
  }

  /**
   * 去重操作
   * @param env
   */
  def distinctFunction(env: ExecutionEnvironment): Unit = {
    val info = ListBuffer[String]()
    info.append("hadoop,spark")
    info.append("hadoop,flink")
    info.append("flink,flink")

    val data = env.fromCollection(info)

    data.flatMap(_.split(",")).distinct().print()
  }

  /**
   * join方法的学习
   * @param env
   */
  def joinFunction(env: ExecutionEnvironment): Unit = {
    val info1 = ListBuffer[(Int ,String)]()
    info1.append((1,"hadoop"))
    info1.append((2,"java"))
    info1.append((3,"linux"))
    info1.append((4,"windows"))

    val info2 = ListBuffer[(Int ,String)]()
    info2.append((1,"spark"))
    info2.append((2,"scala"))
    info2.append((3,"ubuntu"))
    info2.append((4,"vim"))

    val data1 = env.fromCollection(info1)
    val data2 = env.fromCollection(info2)

    //where左边,equalTo右边的
    data1.join(data2).where(0).equalTo(0).apply((first,second)=>{
      (first._1,first._2,second._2)
    }).print()
  }

  /**
   * 外联结outerJoin的用法
   * @param env
   */
  def outerJoinFunction(env: ExecutionEnvironment): Unit = {
    val info1 = ListBuffer[(Int ,String)]()
    info1.append((1,"hadoop"))
    info1.append((2,"java"))
    info1.append((3,"linux"))
    info1.append((4,"windows"))

    val info2 = ListBuffer[(Int ,String)]()
    info2.append((1,"spark"))
    info2.append((2,"scala"))
    info2.append((3,"ubuntu"))
    info2.append((5,"vim"))

    val data1 = env.fromCollection(info1)
    val data2 = env.fromCollection(info2)

    //where左边,equalTo右边的
    //左连接
    data1.leftOuterJoin(data2).where(0).equalTo(0).apply((first,second)=>{

      if(second == null){
        (first._1,first._2,'-')
      }else{
        (first._1,first._2,second._2)
      }

    }).print()

    println("-----------------------------------------")

    //右连接
    data1.rightOuterJoin(data2).where(0).equalTo(0).apply((first,second)=>{

      if(first == null){
        (second._1,'-',second._2)
      }else{
        (first._1,first._2,second._2)
      }

    }).print()


    println("-----------------------------------------")

    //全连接
    data1.fullOuterJoin(data2).where(0).equalTo(0).apply((first,second)=>{

      if(first == null){
        (second._1,'-',second._2)
      }else if (second == null){
        (first._1,first._2,'-')
      }else{
        (first._1,first._2,second._2)
      }

    }).print()
  }

  /**
   * cross方法的学习，即笛卡尔积
   * @param env
   */
  def crossFunction(env: ExecutionEnvironment): Unit = {
    val info1 = List("曼联","曼城")
    val info2 = List(3,1,0)

    val data1 = env.fromCollection(info1)
    val data2 = env.fromCollection(info2)

    data1.cross(data2).print()

  }

}
