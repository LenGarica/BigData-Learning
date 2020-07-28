Redis
===

` 目录：`

- [第一部分——Redis学习笔记](#第一部分redis学习笔记)
    - [一、Redis初识](#一redis初识)
        - [1.1 Redis是什么](#11-redis是什么)
        - [1.2 Redis的特性](#12-redis的特性)
    - [二、Redis API的使用](#二redis-api的使用)
        - [2.1 通用命令的介绍](#21-通用命令的介绍)
            - [1. keys](#1-keys)
            - [2. dbsize](#2-dbsize)
        - [2.2 字符串类型](#22-字符串类型)
            - [1. 特点](#1-特点)
            - [2. 适用场景](#2-适用场景)
            - [3. 命令](#3-命令)
        - [2.3 哈希类型](#23-哈希类型)
            - [1. 特点](#1-特点-1)
            - [2. 适用场景](#2-适用场景-1)
        - [2.4 列表类型](#24-列表类型)
        - [2.5 集合类型](#25-集合类型)
        - [2.6 有序集合类型](#26-有序集合类型)
    - [三、Redis客户端的使用](#三redis客户端的使用)
    - [四、Redis持久化的取舍和选择](#四redis持久化的取舍和选择)
    - [五、常见的持久化开发运维问题](#五常见的持久化开发运维问题)
    - [六、Redis复制原理和优化](#六redis复制原理和优化)
    - [七、Redis Sentinel](#七redis-sentinel)
    - [八、Redis Cluster](#八redis-cluster)
    - [九、缓存设计与优化](#九缓存设计与优化)
- [第二部分——Redis面试题](#第二部分redis面试题)

# 第一部分——Redis学习笔记

## 一、Redis初识

### 1.1 Redis是什么

c语言编写的，开源的，支持网络，可基于内存亦可持久化的日志k-v型非关系数据库，并支持多种语言的API，支持多种数据结构，具有非常高的性能。

### 1.2 Redis的特性

1. 分为客户端、服务端。可以分别部署在不同的机器上，通过自定义协议进行传输和交互。

2. 基于内存。所有数据结构存在内存中，因此速度非常快，但也意味着非常吃内存。

3. 适用于做缓存。当系统的接口数据比较慢的时候，可以将系统数据接口的数据缓存起来，当下次取得时候，可以直接从缓存中取。消息队列，视频点赞数，播放数等等。

4. 适用做数据存储。redis具有两种玩呗的持久化机制AOF和RDB，可以定期的将数据持久化，保障数据的完整性，安全性。

5. 单线程模型。

6. 支持发布订阅，lua脚本，事务，pipeline

7. 提供主从复制，高可用、分布式


## 二、Redis 的使用

### 2.1 通用命令的介绍

| 命令        | 解释      | 
| :--------:   | :-----------: | 
|./redis-server|启动redis服务端|
|./redis-cli|启动redis客户端|
|select 0 | 切换数据库|
|ping|检验是否连接正常|
|keys*|查看当前库的所有键|
|exists key|判断key是否存在|
|type key | 判断key的类型|
|del key | 删除某个键|
|expire key seconds|设置key的过期时间，单位为秒|
|ttl key| 查看还有多少秒过期|
|persist key|去掉key的过期时间|
|dbsize | 查看当前数据库的key的数量|
|flushdb|清空当前库|
|flushall|通杀全部库|


### 2.2 字符串类型

#### 1. 特点
string类型是redis最近本的类型，是二进制安全的，意味着redis的string可以包含任何数据，比如jpg图片或者序列化的对象。一个string类型的value最多可以是512mb大小。

#### 2. 适用场景
计数器：网站播放量；

预定值（阀值）：当一个IP地址访问网站超过了预定次数，对其禁止访问，设置预定次数可以使用string

缓存

分布式锁

#### 3. 命令

| 命令        | 解释      | 
| :--------:   | :-----------: | 
get key |获取key对应的value
set key value | 设置k-v
append key value |将给定的value追加到原值的末尾
strlen key |获取值的长度
setnx key value |只有在key不存在的时候，设置key的值
del key |删除k-v 删除成功，返回1
incr key |key自增1，如果key不存在，则自增为1
decr key |key自减1，如果key不存在，则自减为-1
incrby key k |key自增k，如果key不存在，自增后为k
decr key k |key自减k，如果key不存在，自增后为-k
mset key1 value1 key2 value2 ... |设置多个k-v
mget key1 key2 key3 | 同时获取一个或多个value
msetnx key1 value1  key2 value2 ...|只有在key不存在的时候，设置k-v
getrange key start end | 获取值的范围
setrange key start value | 从起始位置开始，覆写key所存储的字符串值
setex key 过期时间 value | 设置键值的同事，设置过期时间，单位为秒
getset key value | 设置新值的同事获取旧值

### 2.3 哈希类型

#### 1. 特点
hash是键值对的集合，是string类型的field和value的映射表，适合存储对象。

#### 2. 适用场景
存储用户信息

#### 3. 命令

| 命令        | 解释      | 
| :--------:   | :-----------: | 
|hset <key> <field> <value>| 

### 2.4 列表类型

list支持单键多值，按照插入顺序排序，底层是双向链表，在两端操作性能很高，也可以通过下标来操作中间的结点。

### 2.5 集合类型

set对外提供与list相似的功能，但是set不能重复存储数据。

### 2.6 有序集合类型

Zset与set相似，每个结点都有一个评分，根据评分对结点进行排序，从低到高。

## 三、Redis客户端的使用

## 四、Redis持久化的取舍和选择

## 五、常见的持久化开发运维问题

## 六、Redis复制原理和优化

## 七、Redis Sentinel

## 八、Redis Cluster

## 九、缓存设计与优化


# 第二部分——Redis面试题