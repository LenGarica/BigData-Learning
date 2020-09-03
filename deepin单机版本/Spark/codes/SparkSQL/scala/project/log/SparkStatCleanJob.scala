package org.example.project.log

import org.apache.spark.sql.{SaveMode, SparkSession}

object SparkStatCleanJob {

  def main(args: Array[String]) {
    val spark = SparkSession.builder().appName("SparkStatCleanJob")
      .master("local[2]").getOrCreate()

    val accessRDD = spark.sparkContext.textFile("/home/willhope/Documents/dev/BigData-Learning/Spark/data/access.log")

    //accessRDD.take(10).foreach(println)

    //RDD ==> DF
    val accessDF = spark.createDataFrame(accessRDD.map(x => AccessConvertUtil.parseLog(x)),
      AccessConvertUtil.struct)

//        accessDF.printSchema()
//        accessDF.show(false)
    //如果resources目录下有hdfs.xml和core.xml两个文件，则地址写本地的话，会报错。将两个文件删除后，就不会有问题
//    accessDF.write.format("parquet").partitionBy("day").save("/home/willhope/Documents/dev/BigData-Learning/Spark/data/logClean")
    //coalesce设置成一个分区，如果输出路径已经存在，使用mode()覆盖源目录下的文件
    accessDF.coalesce(1).write.format("parquet").mode(SaveMode.Overwrite).partitionBy("day").save("/home/willhope/Documents/dev/BigData-Learning/Spark/data/logClean")

    spark.stop
  }
}

