# Spark安装

## 一、Spark源码的编译

在spark.apache.org中下载spark，这里我们选择2.4.4的source code版本。下载后解压到software文件夹中。[Spark学习的官网](http://spark.apache.org/docs/latest/)。

注意：如果使用maven编译的话，这里有一个非常大的坑，官网要求基于 maven 的构建是 Apache Spark 的引用构建。 使用 Maven 构建 Spark 需要 Maven 3.5.4和 java8。 注意，从 Spark 2.2.0开始，Java 7的支持就被移除了。因此，一定要下载好对应的maven版本，否则会出现编译错误的情况。此外，因为本项目都是在CDH5.15.1平台上，因此在spark源码下的pom.xml文件中，要加上下面。但其实也可以使用Spark自带的maven进行编译，这样会省一些事情，本项目用的是自带的进行编译。

````xml
  <repository>
        <id>cloudera</id>
        <url>https://repository.cloudera.com/artifactory/cloudera-repos</url>
  </repository>
````

在spark源码目录下的dev目录中更改 make-distribution.sh

```bash
export MAVEN_OPTS="${MAVEN_OPTS:--Xmx2g -XX:ReservedCodeCacheSize=512m -XX:ReservedCodeCacheSize=512m}"
```

此时看一下自己的scala版本，在终端中输入scala -version进行查看，spark2.4版本只支持2.11和2.12版本的scala。然后在spark目录下执行./dev/change-scala-version.sh 2.11

在Spark源码目录下，执行下面的语句，这个过程会非常缓慢。估计得30分钟到1个小时，刚开始编译时，可能会发现终端一直卡着不动，这是正在检索所需要的环境，要耐心等待。

```m
./build/mvn -Pyarn -Phadoop-2.6 -Phive -Phive-thriftserver -Dhadoop.version=2.6.0-cdh5.15.1 -DskipTests clean package

推荐使用这个
./dev/make-distribution.sh --name 2.6.0-cdh5.15.1 --tgz  -Pyarn -Phadoop-2.6 -Phive -Phive-thriftserver -Dhadoop.version=2.6.0-cdh5.15.1
执行完后，会出现一个spark-2.4.4-bin-2.6.0-cdh5.15.1.tgz包。
```

## 二、Spark环境的搭建

将编译好的spark-2.4.4-bin-2.6.0-cdh5.15.1.tgz包解压到app中。进入到解压的目录下，我们在spark的bin目录下，看到很多.cmd的文件，这些文件是在windows上运行的，因此我们可以删除，使用rm -rf *.cmd

将spark配入到系统环境变量中，使用pwd查看路径，然后在终端中打开/etc/profile，添加如下的代码：

```xml
  export SPARK_HOME=/home/willhope/app/spark-2.4.4-bin-2.6.0-cdh5.15.1
  export PATH=$SPARK_HOME/bin:$PATH

  然后保存后，执行source etc/profile
```

修改sbin目录下的spark-config.sh,添加jdk的环境变量。

在spark目录下的bin下，执行spark-shell --master local[2]

可以在 http://192.168.0.100:4040 监控spark。**此地址每个电脑都不一样。在执行上面的指令后，我们会看到Spark context Web UI available at http://192.168.1.4:4041 这句话**，这个地址是多少，我们就使用多少。


在conf目录下，设置 cp spark-env.sh.template spark-env.sh，然后配置下面的语句

```bash
SPARK_MASTER_HOST=willhope-pc
SPARK_WORKER_CORES=2
SPARK_WORKER_MEMORY=2g
SPARK_WORKER_INSTANCES=1  # 这里以后可以任意设置
```

Spark Standalone模式的架构和Hadoop HDFS/YARN很类似的 1 master + n worker

启动spark，进入sbin目录，然后输入./start-all.sh，进入 http://192.168.0.100:8080/ (**前面的地址每个电脑都不一样，但是端口号通常是一样的**)可以查看信息，然后在bin目录下，执行spark-shell --master spark://willhope-PC:7077  (后面这个spark://willhope-PC:7077在你的 http://192.168.0.100:8080/ 页面的顶部位置可见)，启动时间有些长。


## 三、使用Spark完成wordcount统计

在bin目录下，执行spark-shell --master spark://willhope-PC:7077 ，会出现一个spark图像；也可以使用spark-shell --master local[2],推荐使用后者，这样可以使机器负载低一些。

```
Welcome to
      ____              __
     / __/__  ___ _____/ /__
    _\ \/ _ \/ _ `/ __/  '_/
   /___/ .__/\_,_/_/ /_/\_\   version 2.4.4
      /_/

Using Scala version 2.11.12 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_211)
Type in expressions to have them evaluated.
Type :help for more information.

scala>

```

然后进行scala输入，可以将下面三行代码直接粘贴进去。注意，自己定义一个文件，用来操作wordcount统计

```s
# 仅仅只需要三行，就可以完成之前java写MR那些代码，但其实我们也可以使用Java8提供的函数式编程来简化代码

//获取文件
val file = spark.sparkContext.textFile("file:///home/willhope/data/hello.txt")
val wordCounts = file.flatMap(line => line.split("\t")).map((word => (word, 1))).reduceByKey(_ + _)
wordCounts.collect

运行结果：

scala> val file = spark.sparkContext.textFile("file:///home/willhope/data/hello.txt")
file: org.apache.spark.rdd.RDD[String] = file:///home/willhope/data/hello.txt MapPartitionsRDD[1] at textFile at <console>:23

scala> val wordCounts = file.flatMap(line => line.split("\t")).map((word => (word, 1))).reduceByKey(_ + _)
wordCounts: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[4] at reduceByKey at <console>:25

scala> wordCounts.collect
res0: Array[(String, Int)] = Array((word,3), (hello,5), (world,3))  

```