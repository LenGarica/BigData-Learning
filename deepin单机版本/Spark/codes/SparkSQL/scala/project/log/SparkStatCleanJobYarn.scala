package org.example.project.log

import org.apache.spark.sql.{SaveMode, SparkSession}

/**
 * 运行在Yarn上
 */
object SparkStatCleanJobYarn {

  def main(args: Array[String]) {

    if(args.length != 2){
      println("Usage: SparkStatCleanJobYarn <inputPath> <outputPath>")
      System.exit(1)
    }

    val Array(inputPath , outputPath) = args

    val spark = SparkSession.builder().getOrCreate()

    val accessRDD = spark.sparkContext.textFile(inputPath)

    //accessRDD.take(10).foreach(println)

    //RDD ==> DF
    val accessDF = spark.createDataFrame(accessRDD.map(x => AccessConvertUtil.parseLog(x)),
      AccessConvertUtil.struct)

    accessDF.coalesce(1).write.format("parquet").mode(SaveMode.Overwrite).partitionBy("day").save(outputPath)

    spark.stop
  }
}

