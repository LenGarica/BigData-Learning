package org.example.exdatasource

import org.apache.spark.sql.SparkSession

object JDBCMySQL {

  val spark = SparkSession.builder().appName("JDBCMySQL").master("local[2]").getOrCreate()

  val jdbcDF = spark.read.format("jdbc").option("url", "jdbc:mysql://localhost:3306/hadoop_hive").option("dbtable", "hadoop_hive.TBLS").option("user", "root").option("password", "123456").option("driver", "com.mysql.jdbc.Driver").load()

  jdbcDF.printSchema

  jdbcDF.show

  jdbcDF.select("TBL_ID","TBL_NAME").show


}
