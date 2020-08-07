` 目录：`

- [第一部分——Flink初识](#第一部分flink初识)
    - [一、Flink概述](#一flink概述)
        - [1.1 Flink是什么](#11-flink是什么)
        - [1.2 Flink架构](#12-flink架构)
            - [1. API & Libraries 层](#1-api--libraries-层)
            - [2. Runtime 核心层](#2-runtime-核心层)
            - [3. 物理部署层](#3-物理部署层)
        - [1.3 Flink 分层 API](#13-flink-分层-api)
            - [1. SQL & Table API](#1-sql--table-api)
            - [2. DataStream & DataSet API](#2-datastream--dataset-api)
            - [3. Stateful Stream Processing](#3-stateful-stream-processing)
        - [1.4 Flink 集群架构](#14-flink-集群架构)
            - [1. 核心组件](#1-核心组件)
            - [2. Task & SubTask](#2-task--subtask)
            - [3. 资源管理](#3-资源管理)
            - [4. 组件通讯](#4-组件通讯)
        - [1.5 Flink 的优点](#15-flink-的优点)
    - [二、Flink开箱即用](#二flink开箱即用)
    - [三、Flink批处理完成WordCount](#三flink批处理完成wordcount)
        - [3.1 Flink + Java方式](#31-flink--java方式)
        - [3.2 使用Java调用FlinkAPI完成wordcount](#32-使用java调用flinkapi完成wordcount)
        - [3.3 Flink + Scala方式](#33-flink--scala方式)
        - [3.4 使用scala调用FlinkAPI完成wordcount](#34-使用scala调用flinkapi完成wordcount)
    - [四、Flink实时处理完成WordCount](#四flink实时处理完成wordcount)
        - [4.1 Flink + Java方式](#41-flink--java方式)
        - [4.2 Flink + scala方式](#42-flink--scala方式)


# 第一部分——Flink初识

## 一、Flink概述

### 1.1 Flink是什么

官网 flink.apache.org

Flink 是一个分布式的流处理框架，它能够对有界和无界的数据流进行高效的处理。Flink 的核心是流处理，当然它也能支持批处理，Flink 将批处理看成是流处理的一种特殊情况，即数据流是有明确界限的。这和 Spark Streaming 的思想是完全相反的，Spark Streaming 的核心是批处理，它将流处理看成是批处理的一种特殊情况， 即把数据流进行极小粒度的拆分，拆分为多个微批处理。Flink能够运行在常规集群上，在内存上执行，可水平扩展。

无界流数据：数据有开始但没有结尾，不间断，此数据流需要不停处理，可理解为此模式使用实时计算。

有界数据流：有开始有结束，可以等所有的数据一起批处理，可理解为此模式使用离线批处理。

Flink 有界数据流和无界数据流：

<div align="center"> <img width="600px" src="pictures/flink-bounded-unbounded.png"/> </div>

Spark Streaming 数据流的拆分：

<div align="center"> <img width="600px" src="pictures/streaming-flow.png"/> </div>

### 1.2 Flink架构

Flink 采用分层的架构设计，从而保证各层在功能和职责上的清晰。如下图所示，由上而下分别是 API & Libraries 层、Runtime 核心层以及物理部署层：

<div align="center"> <img width="600px"  src="pictures/flink-stack.png"/> </div>

#### 1. API & Libraries 层

这一层主要提供了编程 API 和 顶层类库：

+ 编程 API : 用于进行流处理的 DataStream API 和用于进行批处理的 DataSet API；
+ 顶层类库：包括用于复杂事件处理的 CEP 库；用于结构化数据查询的 SQL & Table 库，以及基于批处理的机器学习库 FlinkML 和 图形处理库 Gelly。

#### 2. Runtime 核心层

这一层是 Flink 分布式计算框架的核心实现层，包括作业转换，任务调度，资源分配，任务执行等功能，基于这一层的实现，可以在流式引擎下同时运行流处理程序和批处理程序。

#### 3. 物理部署层

Flink 的物理部署层，用于支持在不同平台上部署运行 Flink 应用。

### 1.3 Flink 分层 API

在前一节介绍的 API & Libraries 这一层，Flink 又进行了更为具体的划分。具体如下：

<div align="center"> <img src="pictures/flink-api-stack.png"/> </div>

按照如上的层次结构，API 的一致性由下至上依次递增，接口的表现能力由下至上依次递减，各层的核心功能如下：

#### 1. SQL & Table API

SQL & Table API 同时适用于批处理和流处理，这意味着你可以对有界数据流和无界数据流以相同的语义进行查询，并产生相同的结果。除了基本查询外， 它还支持自定义的标量函数，聚合函数以及表值函数，可以满足多样化的查询需求。 

#### 2. DataStream & DataSet API

DataStream &  DataSet API 是 Flink 数据处理的核心 API，支持使用 Java 语言或 Scala 语言进行调用，提供了数据读取，数据转换和数据输出等一系列常用操作的封装。当前企业开发中，此层API用的最多。

#### 3. Stateful Stream Processing

Stateful Stream Processing 是最低级别的抽象，它通过 Process Function 函数内嵌到 DataStream API 中。 Process Function 是 Flink 提供的最底层 API，具有最大的灵活性，允许开发者对于时间和状态进行细粒度的控制。

### 1.4 Flink 集群架构

#### 1. 核心组件

按照上面的介绍，Flink 核心架构的第二层是 Runtime 层， 该层采用标准的 Master - Slave 结构， 其中，Master 部分又包含了三个核心组件：Dispatcher、ResourceManager 和 JobManager，而 Slave 则主要是 TaskManager 进程。它们的功能分别如下：

- **JobManagers** (也称为 *masters*) ：JobManagers 接收由 Dispatcher 传递过来的执行程序，该执行程序包含了作业图 (JobGraph)，逻辑数据流图 (logical dataflow graph) 及其所有的 classes 文件以及第三方类库 (libraries) 等等 。紧接着 JobManagers 会将 JobGraph 转换为执行图 (ExecutionGraph)，然后向 ResourceManager 申请资源来执行该任务，一旦申请到资源，就将执行图分发给对应的 TaskManagers 。因此每个作业 (Job) 至少有一个 JobManager；高可用部署下可以有多个 JobManagers，其中一个作为 *leader*，其余的则处于 *standby* 状态。
- **TaskManagers** (也称为 *workers*) : TaskManagers 负责实际的子任务 (subtasks) 的执行，每个 TaskManagers 都拥有一定数量的 slots。Slot 是一组固定大小的资源的合集 (如计算能力，存储空间)。TaskManagers 启动后，会将其所拥有的 slots 注册到 ResourceManager 上，由 ResourceManager 进行统一管理。
- **Dispatcher**：负责接收客户端提交的执行程序，并传递给 JobManager 。除此之外，它还提供了一个 WEB UI 界面，用于监控作业的执行情况。
- **ResourceManager** ：负责管理 slots 并协调集群资源。ResourceManager 接收来自 JobManager 的资源请求，并将存在空闲 slots 的 TaskManagers 分配给 JobManager 执行任务。Flink 基于不同的部署平台，如 YARN , Mesos，K8s 等提供了不同的资源管理器，当 TaskManagers 没有足够的 slots 来执行任务时，它会向第三方平台发起会话来请求额外的资源。

<div align="center"> <img src="pictures/flink-application-submission.png"/> </div>


#### 2. Task & SubTask

上面我们提到：TaskManagers 实际执行的是 SubTask，而不是 Task，这里解释一下两者的区别：

在执行分布式计算时，Flink 将可以链接的操作 (operators) 链接到一起，这就是 Task。之所以这样做， 是为了减少线程间切换和缓冲而导致的开销，在降低延迟的同时可以提高整体的吞吐量。 但不是所有的 operator 都可以被链接，如下 keyBy 等操作会导致网络 shuffle 和重分区，因此其就不能被链接，只能被单独作为一个 Task。  简单来说，一个 Task 就是一个可以链接的最小的操作链 (Operator Chains) 。如下图，source 和 map 算子被链接到一块，因此整个作业就只有三个 Task：

<div align="center"> <img src="pictures/flink-task-subtask.png"/> </div>


解释完 Task ，我们在解释一下什么是 SubTask，其准确的翻译是： *A subtask is one parallel slice of a task*，即一个 Task 可以按照其并行度拆分为多个 SubTask。如上图，source & map 具有两个并行度，KeyBy 具有两个并行度，Sink 具有一个并行度，因此整个虽然只有 3 个 Task，但是却有 5 个 SubTask。Jobmanager 负责定义和拆分这些 SubTask，并将其交给 Taskmanagers 来执行，每个 SubTask 都是一个单独的线程。

#### 3. 资源管理

理解了 SubTasks ，我们再来看看其与 Slots 的对应情况。一种可能的分配情况如下：

<div align="center"> <img src="pictures/flink-tasks-slots.png"/> </div>


这时每个 SubTask 线程运行在一个独立的 TaskSlot， 它们共享所属的 TaskManager 进程的TCP 连接（通过多路复用技术）和心跳信息 (heartbeat messages)，从而可以降低整体的性能开销。此时看似是最好的情况，但是每个操作需要的资源都是不尽相同的，这里假设该作业 keyBy 操作所需资源的数量比 Sink 多很多 ，那么此时 Sink 所在 Slot 的资源就没有得到有效的利用。

基于这个原因，Flink 允许多个 subtasks 共享 slots，即使它们是不同 tasks 的 subtasks，但只要它们来自同一个 Job 就可以。假设上面 souce & map 和 keyBy 的并行度调整为 6，而 Slot 的数量不变，此时情况如下：

<div align="center"> <img src="pictures/flink-subtask-slots.png"/> </div>


可以看到一个 Task Slot 中运行了多个 SubTask 子任务，此时每个子任务仍然在一个独立的线程中执行，只不过共享一组 Sot 资源而已。那么 Flink 到底如何确定一个 Job 至少需要多少个 Slot 呢？Flink 对于这个问题的处理很简单，默认情况一个 Job 所需要的 Slot 的数量就等于其 Operation 操作的最高并行度。如下， A，B，D 操作的并行度为 4，而 C，E 操作的并行度为 2，那么此时整个 Job 就需要至少四个 Slots 来完成。通过这个机制，Flink 就可以不必去关心一个 Job 到底会被拆分为多少个 Tasks 和 SubTasks。

<div align="center"> <img src="pictures/flink-task-parallelism.png"/> </div>



#### 4. 组件通讯

Flink 的所有组件都基于 Actor System 来进行通讯。Actor system是多种角色的 actor 的容器，它提供调度，配置，日志记录等多种服务，并包含一个可以启动所有 actor 的线程池，如果 actor 是本地的，则消息通过共享内存进行共享，但如果 actor 是远程的，则通过 RPC 的调用来传递消息。

<div align="center"> <img src="pictures/flink-process.png"/> </div>


### 1.5 Flink 的优点

最后基于上面的介绍，来总结一下 Flink 的优点：

+ 适用
+ Flink 是基于事件驱动 (Event-driven) 的应用，能够同时支持流处理和批处理；
+ 基于内存的计算，能够保证高吞吐和低延迟，具有优越的性能表现；
+ 支持精确一次 (Exactly-once) 语意，能够完美地保证一致性和正确性；
+ 分层 API ，能够满足各个层次的开发需求；
+ 支持高可用配置，支持保存点机制，能够提供安全性和稳定性上的保证；
+ 多样化的部署方式，支持本地，远端，云端等多种部署方案；
+ 具有横向扩展架构，能够按照用户的需求进行动态扩容；
+ 活跃度极高的社区和完善的生态圈的支持。


## 二、Flink开箱即用

本地测试的话，不需要向Hadoop章节那样安装相应的包来执行，它是一个开箱即用的工具，如同Spring、MyBatis这类框架。只需要使用maven来构建项目，使用Java或者Scala均可以，具体方法看第三节Flink批处理完成WordCount。

如何要在服务器集群上跑，则需要下载与Hadoop相匹配的flink包。下载地址 https://archive.apache.org/dist/flink/flink-1.7.0/

## 三、Flink批处理完成WordCount
	
### 3.1 Flink + Java方式
			
前置条件： Maven 3.0.4 (or higher) and Java 8.x

创建Flink+java项目的方式，选择本地一个文件夹，从终端中进入文件夹，并执行下面的语句：

```
mvn archetype:generate                           \
-DarchetypeGroupId=org.apache.flink              \
-DarchetypeArtifactId=flink-quickstart-java      \
-DarchetypeVersion=1.7.0 \
-DarchetypeCatalog=local
```

执行了上面的操作后，会自动创建一个maven项目，并且配置好相关的pom文件，我们使用IDEA打开此项目，进入后，会有一个使用maven构建项目的选项，点击import。文档pom文件如下：

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.zyx.flink.java</groupId>
	<artifactId>flink-project-java</artifactId>
	<version>1.0</version>
	<packaging>jar</packaging>

	<name>Flink Quickstart Job</name>
	<url>http://www.myorganization.org</url>

	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<flink.version>1.7.0</flink.version>
		<java.version>1.8</java.version>
		<scala.binary.version>2.11</scala.binary.version>
		<maven.compiler.source>${java.version}</maven.compiler.source>
		<maven.compiler.target>${java.version}</maven.compiler.target>
	</properties>

	<repositories>
		<repository>
			<id>apache.snapshots</id>
			<name>Apache Development Snapshot Repository</name>
			<url>https://repository.apache.org/content/repositories/snapshots/</url>
			<releases>
				<enabled>false</enabled>
			</releases>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
		</repository>
	</repositories>

	<dependencies>
		<!-- Apache Flink dependencies -->
		<!-- These dependencies are provided, because they should not be packaged into the JAR file. -->
		<dependency>
			<groupId>org.apache.flink</groupId>
			<artifactId>flink-java</artifactId>
			<version>${flink.version}</version>
		</dependency>
		<dependency>
			<groupId>org.apache.flink</groupId>
			<artifactId>flink-streaming-java_${scala.binary.version}</artifactId>
			<version>${flink.version}</version>
		</dependency>

		<dependency>
			<groupId>org.slf4j</groupId>
			<artifactId>slf4j-log4j12</artifactId>
			<version>1.7.7</version>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.17</version>
			<scope>runtime</scope>
		</dependency>

	</dependencies>

	<build>
		<plugins>

			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.1</version>
				<configuration>
					<source>${java.version}</source>
					<target>${java.version}</target>
				</configuration>
			</plugin>

			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-shade-plugin</artifactId>
				<version>3.0.0</version>
				<executions>
					<execution>
						<phase>package</phase>
						<goals>
							<goal>shade</goal>
						</goals>
						<configuration>
							<artifactSet>
								<excludes>
									<exclude>org.apache.flink:force-shading</exclude>
									<exclude>com.google.code.findbugs:jsr305</exclude>
									<exclude>org.slf4j:*</exclude>
									<exclude>log4j:*</exclude>
								</excludes>
							</artifactSet>
							<filters>
								<filter>
									<artifact>*:*</artifact>
									<excludes>
										<exclude>META-INF/*.SF</exclude>
										<exclude>META-INF/*.DSA</exclude>
										<exclude>META-INF/*.RSA</exclude>
									</excludes>
								</filter>
							</filters>
							<transformers>
								<transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
									<mainClass>com.zyx.flink.java.StreamingJob</mainClass>
								</transformer>
							</transformers>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>

		<pluginManagement>
			<plugins>


				<plugin>
					<groupId>org.eclipse.m2e</groupId>
					<artifactId>lifecycle-mapping</artifactId>
					<version>1.0.0</version>
					<configuration>
						<lifecycleMappingMetadata>
							<pluginExecutions>
								<pluginExecution>
									<pluginExecutionFilter>
										<groupId>org.apache.maven.plugins</groupId>
										<artifactId>maven-shade-plugin</artifactId>
										<versionRange>[3.0.0,)</versionRange>
										<goals>
											<goal>shade</goal>
										</goals>
									</pluginExecutionFilter>
									<action>
										<ignore/>
									</action>
								</pluginExecution>
								<pluginExecution>
									<pluginExecutionFilter>
										<groupId>org.apache.maven.plugins</groupId>
										<artifactId>maven-compiler-plugin</artifactId>
										<versionRange>[3.1,)</versionRange>
										<goals>
											<goal>testCompile</goal>
											<goal>compile</goal>
										</goals>
									</pluginExecutionFilter>
									<action>
										<ignore/>
									</action>
								</pluginExecution>
							</pluginExecutions>
						</lifecycleMappingMetadata>
					</configuration>
				</plugin>
			</plugins>
		</pluginManagement>
	</build>

	<profiles>
		<profile>
			<id>add-dependencies-for-IDEA</id>

			<activation>
				<property>
					<name>idea.version</name>
				</property>
			</activation>

			<dependencies>
				<dependency>
					<groupId>org.apache.flink</groupId>
					<artifactId>flink-java</artifactId>
					<version>${flink.version}</version>
					<scope>compile</scope>
				</dependency>
				<dependency>
					<groupId>org.apache.flink</groupId>
					<artifactId>flink-streaming-java_${scala.binary.version}</artifactId>
					<version>${flink.version}</version>
					<scope>compile</scope>
				</dependency>
			</dependencies>
		</profile>
	</profiles>

</project>

```

`注意在本地开发测试的时候，需要将<scope>provided</scope>删除掉，否则会报错。`


### 3.2 使用Java调用FlinkAPI完成wordcount

1. 开发流程/开发八股文编程

+ set up the batch execution environment
+ read
+ transform operations  开发的核心所在：开发业务逻辑
+ execute program


2. 功能拆解
    
+ 读取数据  
+ 每一行的数据按照指定的分隔符拆分
+ 为每一个单词赋上次数为1
+ 合并操作  groupBy	

```java

package com.zyx.flink.java;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * 使用Java调用Flink API 批处理进行词频统计
 */
public class BatchWCJavaApp {

    public static void main(String[] args) throws Exception{

        String input = "file:///home/willhope/data/input";

        //获取执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        //读取数据
        DataSet<String> text = env.readTextFile(input);

        //处理，使用Lambda函数式编程
        text.flatMap((FlatMapFunction<String, Tuple2<String, Integer>>) (values, collector) -> {
            String[] tokens = values.toLowerCase().split("\t");
            for(String token : tokens){
                if(token.length() > 0){
                    collector.collect(new Tuple2<String,Integer>(token,1));
                }
            }
        }).groupBy(0).sum(1).print();

        //非Lambda式
//        text.flatMap(new FlatMapFunction<String, Tuple2<String,Integer>>() {
//            @Override
//            public void flatMap(String values, Collector<Tuple2<String, Integer>> collector) throws Exception {
//                String[] tokens = values.toLowerCase().split("\t");
//                for(String token : tokens){
//                    if(token.length() > 0){
//                        collector.collect(new Tuple2<String,Integer>(token,1));
//                    }
//                }
//            }
//        }).groupBy(0).sum(1).print();

    }

}


```

### 3.3 Flink + Scala方式

前置条件：Maven 3.0.4 (or higher) and Java 8.x 

```
mvn archetype:generate                               \
-DarchetypeGroupId=org.apache.flink              \
-DarchetypeArtifactId=flink-quickstart-scala     \
-DarchetypeVersion=1.7.0 \
-DarchetypeCatalog=local
```

构建完毕后，与java构建方式相同，使用idea将项目打开。

### 3.4 使用scala调用FlinkAPI完成wordcount

1. 开发流程/开发八股文编程

+ set up the batch execution environment
+ read
+ transform operations  开发的核心所在：开发业务逻辑
+ execute program


2. 功能拆解
    
+ 读取数据  
+ 每一行的数据按照指定的分隔符拆分
+ 为每一个单词赋上次数为1
+ 合并操作  groupBy	


```scala
import org.apache.flink.api.scala.{ExecutionEnvironment, createTypeInformation}

/**
 * 使用scala调用Flink API 批处理进行词频统计
 */
object BatchWCScalaApp {


  def main(args: Array[String]): Unit = {

    val input = "file:///home/willhope/data/input"

    val env = ExecutionEnvironment.getExecutionEnvironment

    val text = env.readTextFile(input)

    text.flatMap(_.toLowerCase().split("\t"))
      .filter(_.nonEmpty).map((_,1)).groupBy(0).sum(1)
      .print()

  }
}
```

## 四、Flink实时处理完成WordCount

### 4.1 Flink + Java方式

首先，我们需要对某个端口进行监听，因此电脑上需要安装netcat，使用sudo apt install netcat -y进行安装。其次，我们需要与代码中约定一个端口号。本次使用9990端口。在终端输入nc -lk 9990，然后终端等待要输入的内容。  


```java

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * 使用Java API来开发Flink的实时处理应用程序.
 *
 * wc统计的数据我们源自于socket
 */
public class StreamingWCJavaApp {


    public static void main(String[] args) throws Exception {

        // step1 ：获取执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        // step2：读取数据
        DataStreamSource<String> text = env.socketTextStream("localhost", 9990);


        // step3: transform
        text.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> collector) throws Exception {
                String[] tokens = value.toLowerCase().split(",");
                for(String token : tokens) {
                    if(token.length() > 0) {
                        collector.collect(new Tuple2<String,Integer>(token,1));
                    }
                }
            }
        }).keyBy(0).timeWindow(Time.seconds(5)).sum(1).print().setParallelism(1);


        env.execute("StreamingWCJavaApp");
    }

}

```

### 4.2 Flink + scala方式

与Java方式同样，我们需要对某个端口进行监听，在终端输入nc -lk 9990，然后终端等待要输入的内容。  

```scala
import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, createTypeInformation}
import org.apache.flink.streaming.api.windowing.time.Time

/**
 * 使用Scala开发flink的实时处理应用程序
 */
object StreamingWCScalaApp {

  def main(args: Array[String]): Unit = {

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val text = env.socketTextStream("localhost",9990)

    text.flatMap(_.split(",")).map((_,1)).keyBy(0)
      .timeWindow(Time.seconds(5)).sum(1).print().setParallelism(1)


    env.execute("StreamingWCScalaApp")

  }

}

```