package org.example.dataframe

import org.apache.spark.sql.SparkSession

/**
 * DataFrame API的基本操作
 */
object DataFrameApp {

    def main(args: Array[String]): Unit = {

      val spark = SparkSession.builder().appName("DataFrameApp").master("local[2]").getOrCreate()

      //将json文件加载成一个dataframe
      val peopleDF = spark.read.format("json").load("file:///home/willhope/app/data/people.json")

      //输出dataframe对应的schema信息
      peopleDF.printSchema()

      //显示记录，括号里面可以填写想要输出的行数
      peopleDF.show()

      //查询name列的数据：select name from table
      peopleDF.select("name").show()

      //查询某几列所有数据，并对列进行计算：select name,age + 10 as age2 from table;
      peopleDF.select(peopleDF.col("name"), (peopleDF.col("age") + 10).as("age2")).show()

      //过滤年龄大于19岁的人
      peopleDF.filter(peopleDF.col("age") > 19).show()

      //根据某一列分组，然后再聚合操作select age,count(1) from table group by age
      peopleDF.groupBy("age").count().show()

      spark.stop()
    }
}
