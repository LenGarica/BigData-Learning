目录：

- [Hadoop单机安装篇](#hadoop单机安装篇)
    - [安装软件的环境变量配置](#安装软件的环境变量配置)
    - [deepin下QQ、微信的安装](#deepin下qq微信的安装)
    - [deepin下JDK的安装（JDK8和JDK13）](#deepin下jdk的安装jdk8和jdk13)
    - [deepin下Scala的安装](#deepin下scala的安装)
    - [deepin下MySQL安装](#deepin下mysql安装)
    - [deepin下Redis安装（暂时不用）](#deepin下redis安装暂时不用)
    - [Hadoop安装](#hadoop安装)
    - [Hive部署](#hive部署)
    - [Hive配置遇到的坑](#hive配置遇到的坑)
- [Hadoop集群安装篇](#hadoop集群安装篇)
    - [集群规划](#集群规划)
    - [前置条件](#前置条件)
    - [配置免密登录](#配置免密登录)
    - [集群搭建](#集群搭建)
    - [提交服务到集群](#提交服务到集群)


## Hadoop单机安装篇

### 安装软件的环境变量配置

```bash
# java environment
export	JAVA_HOME=/usr/lib/jdk1.8.0_211
export	CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
export	PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
export	JRE_HOME=$JAVA_HOME/jre

# scala environment
export SCALA_HOME=/usr/lib/scala-2.11.12
export PATH=$SCALA_HOME/bin:$PATH

# hadoop environment
export HADOOP_HOME=/home/willhope/app/hadoop-2.6.0-cdh5.15.1
export PATH=$HADOOP_HOME/bin:$PATH

# hive environment
export HIVE_HOME=/home/willhope/app/hive-1.1.0-cdh5.15.1
export PATH=$HIVE_HOME/bin:$PATH

# spark environment
export SPARK_HOME=/home/willhope/app/spark-2.4.4-bin-2.6.0-cdh5.15.1
export PATH=$SPARK_HOME/bin:$PATH

# hbase environment
export HBASE_HOME=/home/willhope/app/hbase-1.2.0-cdh5.15.1 
export PATH=$HBASE_HOME/bin:$PATH

# sqoop environment
export SQOOP_HOME=/home/willhope/app/sqoop2-1.99.5-cdh5.15.1
export PATH=$SQOOP_HOME/bin:$PATH

# zookeeper environment
export ZOOKEEPER_HOME=/home/willhope/app/zookeeper-3.4.5-cdh5.15.1
export PATH=$ZOOKEEPER_HOME/bin:$PATH

# kafka environment
export KAFKA_HOME=/home/willhope/app/kafka_2.11-2.4.0
export PATH=$KAFKA_HOME/bin:$PATH

# flume environment
export FLUME_HOME=/home/willhope/app/apache-flume-1.6.0-cdh5.15.1-bin
export PATH=$FLUME_HOME/bin:$PATH

# anaconda environment
export PATH=/home/willhope/anaconda3/bin:$PATH

```

### deepin下QQ、微信的安装

- QQ可以安装直接在应用商店里面安装Tim版本。

- 微信的安装：

    sudo apt install deepin.com.wechat -y 

    mkdir /tmp/wechat

    cd /tmp/wechat

    wget https://dldir1.qq.com/weixin/Windows/WeChatSetup.exe

    env WINEPREFIX=~/.deepinwine/Deepin-WeChat deepin-wine WeChatSetup.exe


- deepin扩展屏幕

    1. xrandr(先查看自己的设备信息，一般会出现一个eDP-1这个设备是自己的，还有一个HDMI-1设备)
    2. xrandr --output HDMI-1 --auto --primary(再输入这句话)
    3. xrandr --output eDP-1 --right-of HDMI-1 --auto(再输入这句话)
    4. 然后外接显示器的时候，进行设置。
    5. 没有上面的这几句，显示器有可能不亮
    
### deepin下JDK的安装（JDK8和JDK13）
    
1. 首先进入到root权限

    sudo -i

2. 其次为Java创建目录，并且将JDK压缩包COPY一份放到创建的目录下

    mkdir /usr/java

    cp /home/willhope/Downloads/jdk-8u211-linux-x64.tar.gz /usr/java/   （这里前面一个地址是你安装包所在的地址）

    ls /usr/java/    （检查一下，是否copy成功）

    cd /usr/java/    （进到JDK所在目录）

    tar -zxvf jdk-8u211-linux-x64.tar.gz    （进行解压缩）

    ls   （检查一下是否解压缩成功）

3. 环境变量的配置

    cd  (退出到根目录)

    vi /etc/profile （进入全局环境变量）

    a   （随便找到一行，进入编辑模式）

    #Java environment<br>

    export JAVA_HOME=/usr/java/jdk1.8.0_211

    export JRE_HOME=${JAVA_HOME}/jre

    export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib

    export PATH=${JAVA_HOME}/bin:$PATH

    esc  （退出编辑模式）

    ：wq    （保存退出）

    source /etc/profile  （执行命令）

    java -version    （查看是否安装成功）

    Java13的安装：在官网或者任意开源镜像中下载包，这里推荐deb的包，可以让deepin自动安装。

    
### deepin下Scala的安装

1. 首先进入到root权限

    sudo -i

2. 其次为Java创建目录，并且将JDK压缩包COPY一份放到创建的目录下

    mkdir /usr/scala/

    cp /home/willhope/Downloads/scala-2.11.8.tgz /usr/scala/ （这里前面一个地址是你安装包所在的地址）

    ls /usr/scala/    （检查一下，是否copy成功）

    cd /usr/scala/    （进到JDK所在目录）

    tar -zxvf scala-2.11.8.tgz   （进行解压缩）

    ls   （检查一下是否解压缩成功）


3. 环境变量的配置

    cd  (退出到根目录)

    vi /etc/profile （进入全局环境变量）
    
    a   （随便找到一行，进入编辑模式）

    #Scala environment

    export SCALA_HOME=/usr/scala/scala-2.11.8

    export PATH=${SCALA_HOME}/bin:$PATH
    
    esc  （退出编辑模式）

    ：wq    （保存退出）

    source /etc/profile  （执行命令）

    scala -version    （查看是否安装成功）
    
安装完成后会出现  Picked up _JAVA_OPTIONS:   -Dawt.useSystemAAFontSettings=gasp   且在以后的IDEA中，编译时也会出现这句话，不需要担心，这是因为JVM的原因。因为Java和Scala同属JVM上的，所以忽略就行，如果实在是强迫症患者，可以自行搜索别人博客修改。



### deepin下MySQL安装

1. apt install mysql-server
2. mysql -u root -p  （此时无密码，直接回车）
3. show databases;
4. 使用 use mysql; 命令打开mysql命名的数据库，显示当前数据库的表：show tables; 查询user表里的数据：select host,user,authentication_string from mysql.user;（user表里是mysql数据库的所有账户信息）
5. 修改密码update mysql.user set authentication_string=password("123456") where user="root";
6. 如果安装失败，可参考这篇文章　https://www.cnblogs.com/cpl9412290130/p/9583868.html

    
### deepin下Redis安装（暂时不用）

方法一：这个方法安装的redis可能不是新的版本

1. sudo apt-get install redis-server
2. 检查Redis服务器系统进程  ps -aux|grep redis
3. 查看redis端口状态  netstat -nlt|grep 6379
4. 卸载操作 sudo apt-get purge --auto-remove redis-server

方法二：在官网上下载5.0版本的包，然后解压缩

然后进入redis目录下，执行 make && make install

安装成功之后，进入Redis的src目录

启动服务端：./redis-server

启动客户端：./redis-cli

    
### Hadoop安装

使用的是CDH版本，下载地址archive.cloudera.com/cdh5/cdh/5
    
1. 打开终端，创建几个文件夹mkdir software (存放软件安装包), app （存放软件安装目录）, data （存放要使用的数据）, lib　（存放开发的jar包) ,shell (存放相关脚本),maven仓库存放在默认的.m2下面，也可以自行更改

2. uname -a 查看自己电脑的用户名，或者打开终端后，用户名字@后面的字就是用户名

3. vi /etc/hosts  查看自己电脑的ip与名字的映射，记住自己用户名映射的ip地址。

4. 本项目使用的是CDH版本是hadoop-2.6.0-cdh5.15.1，往后hive,hbase等版本也要与cdh5.15.1此版本相同。可以在终端直接使用wegt http://archive.cloudera.com/cdh5/cdh/5/hadoop-2.6.0-cdh5.15.1.tar.gz

5. 文档：http://archive.cloudera.com/cdh5/cdh/5/hadoop-2.6.0-cdh5.15.1/

6. Java安装看上面

7. ssh的安装：在root权限下输入：apt install openssh-server；service sshd restart；ssh-keygen -t rsa；cd .ssh ; ls 后会发现多了三个文件id_rsa,id_rsa.pub,known_hosts；cat id_rsa.pub >> authorized_keys;chmod 600 authorized_keys；ssh　自己电脑的用户名，如果没有输密码，则正确。

8. 将下载的cdh包解压到app文件夹中，hadoop目录常见说明，bin:存放hadoop命令，etc：存放hadoop的相关配置，sbin：存放hadoop相关进程的脚本，examples：存放一些简单的案例,share:存放一些例子的jar包。

9. cd app/hadoop-2.6.0-cdh5.15.1/etc/hadoop，进入到此目录中进行相关的配置

10. vi hadoop-env.sh进入到hadoop环境的配置，将java环境配置到这个文件中，因为hadoop环境是默认，因此不用配置

11. vi core-site.xml添加如下配置
    
    ````xml
    <configuration> 
          <property>
              <name>fs.defaultFS</name>
              <value>hdfs://willhope-pc:8020</value>
         </property>
    </configuration>
    ````
       
       
12. vi hdfs-site.xml
    
    ````xml
    <configuration> 
         <property>
             <name>dfs.replication</name>
             <value>1</value>
         </property>
    
         <property>
             <name>hadoop.tmp.dir</name>
             <value>/home/willhope/app/tmp</value>
         </property>
    </configuration>
    ````

13. vi mapred-site.xml

```
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>

```

14. vi yarn-site.xml

```
<configuration>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
</configuration>

```


15. vi slaves;　　　　　将里面的localhost改成电脑的用户名，如果是单机linux而非虚拟机，则可以不用更改
    
16. 重新开一个终端，sudo vi /etc/profile ; 添加hadoop环境到系统环境中　export HADOOP_HOME=/home/willhope/app/hadoop-2.6.0-cdh5.15.1;export PATH=$HADOOP_HOME/bin:$PATH; 保存后，source /etc/profile
    
17. 启动HDFS，第一次执行的时候一定要格式化文件系统，不要重复执行,cd $HADOOP_HOME/bin ; hdsf namenode -format
    
18. 启动集群: cd .. ; cd sbin/;  ./start-dfs.sh;结束后，jps查看当前是否启动成功，如果出现DataNode,NameNode,SecondaryNameNode则成功。如果以后使用jps，只要保证DataNode,NameNode存在就行。
    
19. 在浏览器中输入自己用户名映射的ip地址:50070。例如,我这里就是`http://127.0.0.1:50070`如果出现界面，表明成功。如果jps出现，但是浏览器没出现，请关闭防火墙，然后在输入地址。
    
20. 如果要停止hdfs，则在hadoop的sbin目录下输入./stop-dfs.sh；要单一启动或关闭，hadoop-daemons.sh start/stop NameNode/DataNode/SecondaryNameNode ;
    
21. HDFS的操作跟shell的操作一致，在hadoop目录下进行操作，前缀hadoop fs 加上下面的各类操作，常用的-put（将文件上传）,-ls（显示当前仓库中有哪些文件），-cat（查看文件），-mkdir（创建一个文件夹），-get（从hdfs上获得一份文件到本地），-mv（移动某个文件到某个位置），-cp（将一个文件拷贝一份），-getmerge（将两个文件合并起来），-rm（删除一个文件），-rmdir（删除一个为空的文件夹），-rmr(此命令相当于-rm -r，删除一个文件夹),-text(查看某个文件),-R(递归显示某个文件夹中的文件)
    
22. 上传一个本地文件到hdfs后，可以使用Hadoop fs -du -s -h /project/文件，可以查看文件的具体大小。
    
    ```bash
       Usage: hadoop fs [generic options]
            [-appendToFile <localsrc> ... <dst>]
            [-cat [-ignoreCrc] <src> ...]
            [-checksum <src> ...]
            [-chgrp [-R] GROUP PATH...]
            [-chmod [-R] <MODE[,MODE]... | OCTALMODE> PATH...]
            [-chown [-R] [OWNER][:[GROUP]] PATH...]
            [-copyFromLocal [-f] [-p] [-l] <localsrc> ... <dst>]
            [-copyToLocal [-p] [-ignoreCrc] [-crc] <src> ... <localdst>]
            [-count [-q] [-h] [-v] [-x] <path> ...]
            [-cp [-f] [-p | -p[topax]] <src> ... <dst>]
            [-createSnapshot <snapshotDir> [<snapshotName>]]
            [-deleteSnapshot <snapshotDir> <snapshotName>]
            [-df [-h] [<path> ...]]
            [-du [-s] [-h] [-x] <path> ...]
            [-expunge]
            [-find <path> ... <expression> ...]
            [-get [-p] [-ignoreCrc] [-crc] <src> ... <localdst>]
            [-getfacl [-R] <path>]
            [-getfattr [-R] {-n name | -d} [-e en] <path>]
            [-getmerge [-nl] <src> <localdst>]
            [-help [cmd ...]]
            [-ls [-C] [-d] [-h] [-q] [-R] [-t] [-S] [-r] [-u] [<path> ...]]
            [-mkdir [-p] <path> ...]
            [-moveFromLocal <localsrc> ... <dst>]
            [-moveToLocal <src> <localdst>]
            [-mv <src> ... <dst>]
            [-put [-f] [-p] [-l] <localsrc> ... <dst>]
            [-renameSnapshot <snapshotDir> <oldName> <newName>]
            [-rm [-f] [-r|-R] [-skipTrash] <src> ...]
            [-rmdir [--ignore-fail-on-non-empty] <dir> ...]
            [-setfacl [-R] [{-b|-k} {-m|-x <acl_spec>} <path>]|[--set <acl_spec> <path>]]
            [-setfattr {-n name [-v value] | -x name} <path>]
            [-setrep [-R] [-w] <rep> <path> ...]
            [-stat [format] <path> ...]
            [-tail [-f] <file>]
            [-test -[defsz] <path>]
            [-text [-ignoreCrc] <src> ...]
            [-touchz <path> ...]
            [-usage [cmd ...]]
    ```
    
    

### Hive部署

在 http://archive.cloudera.com/cdh5/cdh/5/找到hive-1.1.0-cdh5.15.1.tar.gz 这个包，将其下载下来，解压到app目录下。将hive添加到系统环境中，方便使用，但是这里最好重新启动一下机器。在使用hive前，必须先将hadoop平台的所有东西启动起来。

进入hive目录进行配置，修改配置conf目录下的hive-env.sh、hive-site.xml，再拷贝MySQL驱动包到$HIVE_HOME/lib，但前提是要准备安装一个MySQL数据库，sudo apt-get install去安装一个MySQL数据库 https://www.cnblogs.com/julyme/p/5969626.html

```xml

    <!--本部分写在hive-site.xml，注意更换你的mysql配置-->
    <?xml version="1.0"?>
    <?xml-stylesheet type="text/xsl" href="configuration.xsl"?>

    <configuration>
    <property>
      <name>javax.jdo.option.ConnectionURL</name>
      <value>jdbc:mysql://127.0.0.1:3306/hadoop_hive?createDatabaseIfNotExist=true</value>
    </property>

    <property>
      <name>javax.jdo.option.ConnectionDriverName</name>
      <value>com.mysql.jdbc.Driver</value>
    </property>

    <property>
      <name>javax.jdo.option.ConnectionUserName</name>
      <value>root</value>
    </property>

    <property>
      <name>javax.jdo.option.ConnectionPassword</name>
      <value>123456</value>
    </property>
    </configuration>

```

这部分写在hive-env.sh中

```bash

HADOOP_HOME=/home/willhope/app/hadoop-2.6.0-cdh5.15.1（注意更换你的地址）

```

### Hive配置遇到的坑

遇到一个坑，以前学的时候用的是别人提供的镜像没有这种问题，现在学的时候用的是deepin，在配置好各种内容，启动hive，在hive中查询时，总是会提出Error: Syntax error: Encountered “” at line 1, column 64。

搜到网上各种教程，说是hive默认的是derby，要进行初始化。然而跟着网上的教程做，发现依然无法解决上面的错。

最终，解决方法是，删除原来的hive，然后重新配置好hive，在启动hive之前，进行初始化，进入到bin目录，执行 ./schematool -dbType mysql -initSchema -verbose，schemaTool completed则表明成功，并且会完成在mysql中数据库的创建（也就是hive-site.xml中配置的数据库），此时数据库中的表都是空的，没有内容。然后在bin下执行hive，执行create database test_db后，表中就有内容了，以及其他查询操作，即可成功。（在hive执行sql语句时，会发现一个ssl警告，可以忽略，也可以在hive-site.xml，配置数据库名字那一行createDatabaseIfNotexist=true后面添加上;ssl=true）

## Hadoop集群安装篇

### 集群规划

这里搭建一个 3 节点的 Hadoop 集群，其中三台主机均部署 `DataNode` 和 `NodeManager` 服务，但只有 hadoop001 上部署 `NameNode` 和 `ResourceManager` 服务。

<div align="center"> <img  src="../pictures/hadoop集群规划.png"/> </div>

### 前置条件

Hadoop 的运行依赖 JDK，需要预先安装。使用虚拟机的话，可以直接镜像复制。

### 配置免密登录

1. 生成密匙

在每台主机上使用 `ssh-keygen` 命令生成公钥私钥对：

```shell
ssh-keygen
```

2. 免密登录

将 `hadoop001` 的公钥写到本机和远程机器的 ` ~/ .ssh/authorized_key` 文件中：

```shell
ssh-copy-id -i ~/.ssh/id_rsa.pub hadoop001
ssh-copy-id -i ~/.ssh/id_rsa.pub hadoop002
ssh-copy-id -i ~/.ssh/id_rsa.pub hadoop003
```

3. 验证免密登录

```she
ssh hadoop002
ssh hadoop003
```

### 集群搭建

1. 下载并解压

下载 Hadoop。这里我下载的是 CDH 版本 Hadoop，下载地址为：http://archive.cloudera.com/cdh5/cdh/5/

```shell
# tar -zvxf hadoop-2.6.0-cdh5.15.1.tar.gz 
```

2. 配置环境变量

编辑 `profile` 文件：

```shell
# vim /etc/profile
```

增加如下配置：

```
export HADOOP_HOME=/usr/app/hadoop-2.6.0-cdh5.15.2
export  PATH=${HADOOP_HOME}/bin:$PATH
```

执行 `source` 命令，使得配置立即生效：

```shell
# source /etc/profile
```

3. 修改配置

进入 `${HADOOP_HOME}/etc/hadoop` 目录下，修改配置文件。各个配置文件内容如下：

4. hadoop-env.sh

```shell
# 指定JDK的安装位置
export JAVA_HOME=/usr/java/jdk1.8.0_201/
```

5. core-site.xml

```xml
<configuration>
    <property>
        <!--指定 namenode 的 hdfs 协议文件系统的通信地址-->
        <name>fs.defaultFS</name>
        <value>hdfs://hadoop001:8020</value>
    </property>
    <property>
        <!--指定 hadoop 集群存储临时文件的目录-->
        <name>hadoop.tmp.dir</name>
        <value>/home/hadoop/tmp</value>
    </property>
</configuration>
```

6. hdfs-site.xml

```xml
<property>
      <!--namenode 节点数据（即元数据）的存放位置，可以指定多个目录实现容错，多个目录用逗号分隔-->
    <name>dfs.namenode.name.dir</name>
    <value>/home/hadoop/namenode/data</value>
</property>
<property>
      <!--datanode 节点数据（即数据块）的存放位置-->
    <name>dfs.datanode.data.dir</name>
    <value>/home/hadoop/datanode/data</value>
</property>
```

7. yarn-site.xml

```xml
<configuration>
    <property>
        <!--配置 NodeManager 上运行的附属服务。需要配置成 mapreduce_shuffle 后才可以在 Yarn 上运行 MapReduce 程序。-->
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
    <property>
        <!--resourcemanager 的主机名-->
        <name>yarn.resourcemanager.hostname</name>
        <value>hadoop001</value>
    </property>
</configuration>

```

8.  mapred-site.xml

```xml
<configuration>
    <property>
        <!--指定 mapreduce 作业运行在 yarn 上-->
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>
```

9. slaves

配置所有从属节点的主机名或 IP 地址，每行一个。所有从属节点上的 `DataNode` 服务和 `NodeManager` 服务都会被启动。

```properties
hadoop001
hadoop002
hadoop003
```

10. 分发程序

将 Hadoop 安装包分发到其他两台服务器，分发后建议在这两台服务器上也配置一下 Hadoop 的环境变量。

```shell
# 将安装包分发到hadoop002
scp -r /usr/app/hadoop-2.6.0-cdh5.15.2/  hadoop002:/usr/app/
# 将安装包分发到hadoop003
scp -r /usr/app/hadoop-2.6.0-cdh5.15.2/  hadoop003:/usr/app/
```

11.  初始化

在 `Hadoop001` 上执行 namenode 初始化命令：

```
hdfs namenode -format
```

12. 启动集群

进入到 `Hadoop001` 的 `${HADOOP_HOME}/sbin` 目录下，启动 Hadoop。此时 `hadoop002` 和 `hadoop003` 上的相关服务也会被启动：

```shell
# 启动dfs服务
start-dfs.sh
# 启动yarn服务
start-yarn.sh
```

13. 查看集群

在每台服务器上使用 `jps` 命令查看服务进程，或直接进入 Web-UI 界面进行查看，端口为 `50070`。可以看到此时有三个可用的 `Datanode`：

<div align="center"> <img  src="../pictures/hadoop-集群环境搭建.png"/> </div>
<BR/>

点击 `Live Nodes` 进入，可以看到每个 `DataNode` 的详细情况：

<div align="center"> <img  src="../pictures/hadoop-集群搭建2.png"/> </div>
<BR/>

接着可以查看 Yarn 的情况，端口号为 `8088` ：

<div align="center"> <img  src="../pictures/hadoop-集群搭建3.png"/> </div>


### 提交服务到集群

提交作业到集群的方式和单机环境完全一致，这里以提交 Hadoop 内置的计算 Pi 的示例程序为例，在任何一个节点上执行都可以，命令如下：

```shell
hadoop jar /usr/app/hadoop-2.6.0-cdh5.15.2/share/hadoop/mapreduce/hadoop-mapreduce-examples-2.6.0-cdh5.15.2.jar  pi  3  3
```

