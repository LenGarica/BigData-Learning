package org.example.dataframe

import org.apache.spark.sql.SparkSession

object DataFrameCase {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder().appName("DataFrameCase").master("local[2]").getOrCreate()

    //sparkContext可以获取到rdd，textfile可以从hdfs上读取文件，或者从本地获取文件
    val rdd = spark.sparkContext.textFile("file:///home/willhope/app/data/student.data")

    //注意：需要导入隐式转换
    import  spark.implicits._
    val studentDF = rdd.map(_.split("\\|")).map(line => Student(line(0).toInt, line(1), line(2),line(3))).toDF()

    //只显示前20条
    studentDF.show()

    //指定显示条数
    studentDF.show(30)

    //查看前10条记录，可以不加foreach，但是会显示的很乱
    studentDF.take(10).foreach(println)

    //显示第一条记录
    studentDF.first()

    //显示前三条
    studentDF.head(3)

    //查询指定的列
    studentDF.select("email").show(30,false)

    //查询多个指定的列
    studentDF.select("name","email").show(30,false)

    //显示名字为空的和为Null的列
    studentDF.filter("name = '' or name = 'NULL'").show()

    //显示以M开头的人的信息，其中substr等价与substring
    studentDF.filter("substr(name,0,1)='M'").show()

    //查看spark-sql中内置的功能函数，显示1000条
    spark.sql("show functions").show(1000)

    //排序，默认升序
    studentDF.sort(studentDF("name")).show()
    //设置为降序
    studentDF.sort(studentDF("name").desc).show()
    //名字的升序，id的降序排列
    studentDF.sort(studentDF("name").asc , studentDF("id").desc).show()

    //将列名重命名
    studentDF.select(studentDF("name").as("student_name")).show()

    //内联结
    val studentDF2 = rdd.map(_.split("\\|")).map(line => Student(line(0).toInt, line(1), line(2),line(3))).toDF()
    //在内联结的时候，等于号必须写3个
    studentDF.join(studentDF2 , studentDF.col("id") === studentDF2.col("id")).show()


    spark.stop()

  }

  case class Student(id : Int , name : String , phone : String , email : String)
}
