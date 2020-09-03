package org.example.project.log

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.collection.mutable.ListBuffer

/**
 * TopN统计Spark作业
 */
object TopNStatJob {

  /**
   * 最受欢迎的TopN课程
   */
  def videoAccessTopNStat(spark: SparkSession, accessDF:DataFrame, day:String): Unit = {

    /**
     * 使用DataFrame的方式进行统计
     */
    import spark.implicits._
    //$ "" 相当于column，agg是聚合函数放在org.apache.spark.sql.functions._包下面
    val videoAccessTopNDF = accessDF.filter($"day" === day && $"cmsType" === "video")
      .groupBy("day","cmsId").agg(count("cmsId").as("times")).orderBy($"times".desc)

    /**
     * 使用临时表形式，sql进行查询
     */
//    accessDF.createOrReplaceTempView("access_logs")
//    val videoAccessTopNDF = spark.sql("select from access_logs where day=$day and cmsType='video' group by day,cmsId order by times desc")

    /**
     * 将统计结果写入到MySQL中
     */
    try{
      videoAccessTopNDF.foreachPartition(partitionOfRecords => {
      val list = new ListBuffer[DayVideoAccessStat]

      partitionOfRecords.foreach(info =>{
        val day = info.getAs[String]("day")
        val cmsId = info.getAs[Long]("cmsId")
        val times = info.getAs[Long]("times")

        list.append(DayVideoAccessStat(day,cmsId,times))
      })
      StatDAO.insertDayVideoAccessTopN(list)
    })
    }catch {
      case e : Exception => e.printStackTrace()
    }



    videoAccessTopNDF.show(false)

  }


  /**
   * 按照地市进行统计TopN课程
   * @param spark
   * @param accessDF
   * @param day
   */
  def cityAccessTopNStat(spark: SparkSession, accessDF:DataFrame, day:String): Unit = {
    import spark.implicits._

    val cityAccessTopNDF = accessDF.filter($"day" === day && $"cmsType" === "video")
      .groupBy("day","city","cmsId")
      .agg(count("cmsId").as("times"))

    //cityAccessTopNDF.show(false)

    //Window函数在Spark SQL的使用

    val top3DF = cityAccessTopNDF.select(
      cityAccessTopNDF("day"),
      cityAccessTopNDF("city"),
      cityAccessTopNDF("cmsId"),
      cityAccessTopNDF("times"),
      row_number().over(Window.partitionBy(cityAccessTopNDF("city"))
        .orderBy(cityAccessTopNDF("times").desc)
      ).as("times_rank")
    ).filter("times_rank <=3") //.show(false)  //Top3


    /**
     * 将统计结果写入到MySQL中
     */
    try {
      top3DF.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[DayCityVideoAccessStat]

        partitionOfRecords.foreach(info => {
          val day = info.getAs[String]("day")
          val cmsId = info.getAs[Long]("cmsId")
          val city = info.getAs[String]("city")
          val times = info.getAs[Long]("times")
          val timesRank = info.getAs[Int]("times_rank")
          list.append(DayCityVideoAccessStat(day, cmsId, city, times, timesRank))
        })

        StatDAO.insertDayCityVideoAccessTopN(list)
      })
    } catch {
      case e:Exception => e.printStackTrace()
    }

  }

  /**
   * 按照流量进行TopN统计
   * @param spark
   * @param accessDF
   * @param day
   */
  def videoTrafficsTopNStat(spark: SparkSession, accessDF: DataFrame, day: String) = {
    import spark.implicits._

    val videoTrafficsTopNDF = accessDF.filter($"day" === day && $"cmsType" === "video")
      .groupBy("day","cmsId")
      .agg(count("traffic").as("traffics")).orderBy($"traffics".desc)
    /**
     * 将统计结果写入到MySQL中
     */
    try {
      videoTrafficsTopNDF.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[DayVideoTrafficsStat]

        partitionOfRecords.foreach(info => {
          val day = info.getAs[String]("day")
          val cmsId = info.getAs[Long]("cmsId")
          val traffics = info.getAs[Long]("traffics")
          list.append(DayVideoTrafficsStat(day, cmsId, traffics))
        })

        StatDAO.insertDayVideoTrafficsTopN(list)
      })
    } catch {
      case e:Exception => e.printStackTrace()
    }
  }

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("TopNStatJob").config("spark.sql.sources.partitionColumnTypeInference.enabled","false")
      .master("local[2]").getOrCreate()

    val acDF = spark.read.format("parquet").load("/home/willhope/Documents/dev/BigData-Learning/Spark/data/logClean")

    acDF.printSchema()
    //printSchema发现，day的属性是Integer类型，但是我们原先定义的是String类型，我们需要在SparkSession中设置参数
    /**
     * root
     * |-- url: string (nullable = true)
     * |-- cmsType: string (nullable = true)
     * |-- cmsId: long (nullable = true)
     * |-- traffic: long (nullable = true)
     * |-- ip: string (nullable = true)
     * |-- city: string (nullable = true)
     * |-- time: string (nullable = true)
     * |-- day: integer (nullable = true)
     */

//    acDF.show(false)
    val day = "20170511"
    //为了使数据不重复，在写入MySQL前，进行一次删除操作
    StatDAO.deleteData(day)

    videoAccessTopNStat(spark,acDF,day)

    cityAccessTopNStat(spark,acDF,day)

    videoTrafficsTopNStat(spark,acDF,day)

    spark.stop()

  }

}

