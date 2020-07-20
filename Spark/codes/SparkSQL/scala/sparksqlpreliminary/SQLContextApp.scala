package org.example.sparksqlpreliminary

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

object SQLContextApp {

  def main(args: Array[String]): Unit = {

    //如果要在服务器提交作业，这个地址要写成val path = args(0)。
    // 也可以本地测试时候这么写，然后在IDEA中设置传入的参数。
    val path = "file:///home/willhope/app/data/people.json"

    /**
     * 1.设置sparkConf，加载主类，就像是MR中的Driver类。以及设置local[N]代表在本地运行，
     * 使用N个线程，也就是说可以同时执行N个程序，
     * 虽然在本地运行，但是因为cpu大多是多个核心，所以使用多个线程会加速执行，
     * 那么local[2]就代表2个线程
     */
    val sparkConf = new SparkConf()
    //本地测试的时候要使用这句话，如果提交到服务器时，这句话可以不写
    sparkConf.setAppName("SQLContextApp").setMaster("local[2]")

    /**
     * 2.创建SparkContext，我在这里将其翻译为spark环境，或者spark容器，
     * 通常情况下翻译为上下文，但这样不利于理解。让spark容器加载设置的sparkConf
     */
    val sc = new SparkContext(sparkConf)

    /**
     * 3.创建SQLContext，创建sql容器，将SparkContext对象加载
     */
    val sqlContext = new SQLContext(sc)


    /**
     * 4.使用SQLContext做相关的处理，例如读取json文件，传入路径
     */
    val people = sqlContext.read.format("json").load(path)
    //printSchema函数展示文件的结构
    people.printSchema()
    //show函数展示文件的内容
    people.show()


    /**
     * 5.关闭资源
     */
    sc.stop()

  }

}
