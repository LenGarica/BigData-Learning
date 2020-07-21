package org.example.sparksqlpreliminary

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object HiveContextApp {

  def main(args: Array[String]): Unit = {

    // 1. 设置sparkConf
    val sparkConf = new SparkConf()
    //本地测试的时候要使用这句话，如果提交到服务器时，这句话可以不写
    sparkConf.setAppName("HiveContextApp").setMaster("local[2]")

    // 2. 设置spark环境
    val sparkContext = new SparkContext(sparkConf)

    //3. 设置hivecontext
    val hiveContext = new HiveContext(sparkContext)

    //4. 进行相关处理
    hiveContext.table("emp").show

    //5. 关闭资源
    sparkContext.stop()

  }


}
