` 注意 `

本机直接使用Linux发行版deepin，未使用虚拟机安装。如果使用虚拟机安装，则安装如下软件时，需要更改config配置文件，将其中的host.namme进行指定。本机使用的是flink1.7.0版本，为了兼容，因此下载es 6.8.12版本。

## Elastic的部署

在官网下载源文件 https://www.elastic.co/cn/downloads/elasticsearch 

解压源文件，在bin目录下执行./elasticsearch，然后在浏览器中输入，http://localhost:9200/，查看是否出现elasticsearch相关信息。

如果想要后台运行，则在bin目录下，执行 ./elasticsearch -d 

## Kibana部署

与Elastic一样在官网下载，解压源文件，在bin目录下，执行 ./kibana ，然后在浏览器中输入 http://localhost:5601/app/home# 即可访问kibana