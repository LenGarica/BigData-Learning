### clickhouse的安装(centos)

这里给出官方地址：https://clickhouse.tech/docs/zh/getting-started/install/


1. 首先，需要添加官方存储库：
```bash
sudo yum install yum-utils
sudo rpm --import https://repo.clickhouse.tech/CLICKHOUSE-KEY.GPG
sudo yum-config-manager --add-repo https://repo.clickhouse.tech/rpm/stable/x86_64
```

2. 然后运行命令安装：

```bash
sudo yum install clickhouse-server clickhouse-client

```

### clickhouse的安装（ubuntu）
```
sudo apt-get install apt-transport-https ca-certificates dirmngr
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv E0C56BD4

echo "deb https://repo.clickhouse.tech/deb/stable/ main/" | sudo tee \
    /etc/apt/sources.list.d/clickhouse.list
sudo apt-get update

sudo apt-get install -y clickhouse-server clickhouse-client

sudo service clickhouse-server start
clickhouse-client
```

### clickhouse的启动与应用

clickhouse的使用与大多数的nosql数据库使用方法一样。

执行下面的语句进行clickhouse的启动
```
sudo clickhouse start
```

执行下面的语句进行使用

```
clickhouse-client
```


