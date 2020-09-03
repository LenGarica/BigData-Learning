package org.example.exdatasource

import java.util.Properties

import org.apache.spark.sql.SparkSession

object JDBCMySQL2 {

  val connectionProperties = new Properties()
  connectionProperties.put("user", "root")
  connectionProperties.put("password", "123456")
  connectionProperties.put("driver", "com.mysql.jdbc.Driver")

  val spark = SparkSession.builder().appName("JDBCMySQL").master("local[2]").getOrCreate()

  val jdbcDF2 = spark.read.jdbc("jdbc:mysql://localhost:3306", "hadoop_hive.TBLS", connectionProperties)



}
