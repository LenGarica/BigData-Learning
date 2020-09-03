## 一、Flume安装

1. 在终端下载： wget：http://archive.cloudera.com/cdh5/cdh/5/flume-ng-1.6.0-cdh5.15.1.tar.gz

http://archive.cloudera.com/cdh5/cdh/5/zookeeper-3.4.5-cdh5.15.1.tar.gz

2. 解压缩： tar -zxvf flume-ng-1.6.0-cdh5.15.1.tar.gz /home/willhope/app

3. 添加环境变量： sudo vi /etc/profile

```
# flume environment

export FLUME_HOME=/home/willhope/app/apache-flume-1.6.0-cdh5.15.1-bin

export PATH=$FLUME_HOME/bin:$PATH
```

4. 生效 source /etc/profile

5. 到flume下面的conf目录设置

```
cp flume-env.sh.template flume-env.sh
vi flume-env.sh
export JAVA_HOME=/..........  在终端输入echo JAVA_HOME可以查看
```

6. 查看安装成功

在flume的bin目录下输入：flume-ng version


## 二、zookeeper的安装 

下载zookeeper-3.4.5-cdh5.15.1.tar.gz，解压缩到app目录下，然后配到系统环境变量，再source一下

```
#zookeeper environment
export ZK_HOME=/home/willhope/app/zookeeper-3.4.5-cdh5.15.1
export PATH=$ZK_HOME/bin:$PATH
```

将conf目录下的zoo_sample.cfg复制一份为zoo.cfg，然后修改conf目录下的zoo.cfg，设置dataDir=/home/willhope/app/tmp/zookeeper

进入到bin目录，输入 ./zkServer.sh start 启动zookeeper

jps查看成功与否：出现QuorumPeerMain 则表明成功

连接客户端： ./zkCli.sh 

## 三、Kafka安装

官网下载 kafka_2.11-2.4.0.tgz，然后解压到 app目录中，配置到环境变量中，再source一下

```
#kafka environment
export KF_HOME=/home/willhope/app/kafka_2.11-2.4.0
export PATH=$KF_HOME/bin:$PATH
```

- 单节点单brocker的部署以及使用

Kafka目录下的config下的server.properties

```
broker.id=0c
listeners=PLAINTEXT://:9092
host.name=willhope-pc
log.dirs=/home/willhope/app/tmp/kafka-logs
zookeeper.connect=willhope-pc:2181
```

启动Kafka， 在bin目录下执行： kafka-server-start.sh -daemon $KAFKA_HOME/config/server.properties ，jps 之后会出现kafka进程
