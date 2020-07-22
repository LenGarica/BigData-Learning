package org.example.dataframe

import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object DataFrameRDDProgram {

  def main(args: Array[String]): Unit = {

    //1. 设置文件路径
    val inputPath = "file:///home/willhope/app/data/infos.txt"

    //2. 设置SparkSession，master
    val spark = SparkSession.builder().appName("DataFrameRDDProgram").master("local[2]").getOrCreate()

    //3. sparkContext可以获取到rdd，textfile可以从hdfs上读取文件，或者从本地获取文件
    val rdd = spark.sparkContext.textFile(inputPath)

    //4. 处理文件内容，不需要像反射方法中定义类，使用Row接收数据即可
    val infoRDD = rdd.map(_.split(",")).map(line => Row(line(0).toInt, line(1), line(2).toInt))

    //5. 设置structType，设置Row数据的结构，第三个参数表示能否为空
    val structType = StructType(Array(StructField("id",IntegerType,true),
      StructField("name",StringType,true),
      StructField("age",IntegerType,true)
    ))

    //6. 将rdd转换为Dataframe
    val infoDF = spark.createDataFrame(infoRDD,structType)
    infoDF.printSchema()
    infoDF.show()

    infoDF.filter(infoDF.col("age") > 30).show()

    //如果对编程不了解，也可以采用sql的方式来解决，创建一张临时的表，然后可以直接使用spark-sql
//    infoDF.createOrReplaceTempView("infos")
//    spark.sql("select * from infos where age>30").show()
    spark.stop()
  }

}
