package org.example.dataframe

import org.apache.spark.sql.SparkSession
/**
 * DataFrame 与 RDD 的互相操作
 */

object DataFrameRDDReflection {

  def main(args: Array[String]): Unit = {

    // 1. 输入地址
    val inputPath = "file:///home/willhope/app/data/infos.txt"

    //2. SparkSession设置
    val sparkSession = SparkSession.builder().master("local[2]").appName("DataFrameRDDApp").getOrCreate()

    //3. RDD ==> DataFrame
    // 首先通过sparkContext的textFile()读取文件，获得RDD，返回类型为String。
    // 这里可以理解为将文本内容进行行分割，就像MapReduce的map阶段前，将文本行分割
    val rdd = sparkSession.sparkContext.textFile(inputPath)

    //导入隐式转换，注意这个implicits前面的值是我们设置的SparkSession的名
    import sparkSession.implicits._
    //将数据按照分隔符分割，split分割后，返回的是一个String类型数组，然后将数组中的内容取出来，并且转换类型，赋值给定义的类
    val infoDF = rdd.map(_.split(",")).map(line => Info(line(0).toInt , line(1),line(2).toInt)).toDF()

    /**
     * infoDF的操作方式一：使用DataFrame的API
     */
    //展示
    infoDF.show()
    //过滤
    infoDF.filter(infoDF.col("age") > 30).show()

    /**
     * infoDF的操作方式二：使用SparkSql的API
     * 如果对编程不了解，也可以采用sql的方式来解决，创建一张临时的表，然后可以直接使用spark-sql
     */
    //注册一张临时的表，可以理解为视图，可以方便使用sql语句来进行操作
    infoDF.createOrReplaceGlobalTempView("infos")
    sparkSession.sql("select * from infos where age > 30").show()

    //4. SparkSession停止
    sparkSession.stop()

  }
  case class Info(id:Int , name:String , age:Int)

}
