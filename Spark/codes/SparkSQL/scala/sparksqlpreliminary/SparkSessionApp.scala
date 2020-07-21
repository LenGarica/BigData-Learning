package org.example.sparksqlpreliminary

import org.apache.spark.sql.SparkSession

object SparkSessionApp {

  def main(args: Array[String]): Unit = {

    // 1. 使用SparkSession加载主类
    val sparkSession  = SparkSession.builder().appName("SparkSessionApp").master("local[2]").getOrCreate()

    // 2. 读取json文件
//    sparkSession.read.format("json").load("")
    //或者使用
    val people = sparkSession.read.json("file:///home/willhope/app/data/people.json")
    people.show()

    sparkSession.stop()


  }


}
