package org.example.exdatasource

import org.apache.spark.sql.SparkSession

object ParquetApp {

  def main(args: Array[String]): Unit = {

    //1. 设置文件路径
    val inputPath = "file:///home/willhope/app/data/users.parquet"

    //2. 设置SparkSession，master
    val spark = SparkSession.builder().appName("ParquetApp").master("local[2]").getOrCreate()

    //3. 读取文件，设置文件的格式，获取Dataframe，处理parquet文件时，可以不指定format，因为spark默认处理parquet
    val userDF = spark.read.format("parquet").load(inputPath)
    userDF.printSchema()
    userDF.show()


    //4. 上面三句可以更改为下面一句
//    spark.read.format("parquet").option("path",inputPath).load().show()

    //5. 显示自己需要的两列
    userDF.select("name","favorite_color").show()

    //6. 将显示的两列写出到json文件中
    userDF.select("name","favorite_color").write.format("json").save("file:///home/willhope/app/data/userjsonout")

    // 关闭SparkSession
    spark.close()

  }

}
