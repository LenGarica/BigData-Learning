# hbase的安装

1. 本仓库使用的是hbase-1.2.0-cdh5.15.1版本，将仓库中提供的hbase包进行解压，然后放在app目录中，并将hbase添加进环境变量。

```bash

export HBASE_HOME=/home/hadoop/app/hbase-1.2.0-cdh5.15.1 
export PATH=$HBASE_HOME/bin:$PATH

```

写好后，执行source ~/.bash_profile

2. 进入conf目录，修改hbase-env.sh，将下面的代码添加进去

```bash

export JAVA_HOME=/home/hadoop/app/jdk1.8.0_91
export HBASE_MANAGES_ZK=false

```

3. 修改conf/文件下的配置文件hbase-site.xml

```xml

<configuration>
<property>
     <name>hbase.root.dir</name>
     <value>hdfs://hadoop000:8020/hbase</value>
</property>


<property>
     <name>hbase.zookeeper.property.dataDir</name>
     <value>/home/hadoop/tmp/hbase</value>
</property>

<--端口默认60000-->
<property>
     <name>hbase.master.port</name>
     <value>16000</value>
</property>

</configuration>


```

4. 启动hbase，启动hbase之前一定要先启动hadoop和zookeeper

进入bin目录，执行./start-hbase.sh。
