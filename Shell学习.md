# Shell学习

## 一、变量

### 1.1 变量替换

|语法|说明|
|:----|:----|
|${变量#匹配规则}|从头开始匹配，最短删除|
|${变量##匹配规则}|从头开始匹配，最长删除|
|${变量%匹配规则}|从尾开始匹配，最短删除|
|${变量%%匹配规则}|从尾开始匹配，最长删除|
|${变量/旧字符串/新字符串}|替换第一个旧字符串为新字符串|
|${变量//旧字符串/新字符串}|替换所有旧字符串为新字符串|
<br/>

语法实现 ：

```shell

# 定义一个变量
variable_1="I love you , Do you love me"

# 打印这个变量
echo $variable_1

# 最短删除这个变量中的以任意字符开头包含ov，并返回结果
var1=${variable_1#*ov}

# 输出一下
echo $var1

# 最长删除这个变量中的以任意字符开头包含ov，并返回结果
var2=${variable_1##*ov}

# 输出一下
echo $var2

# 从尾部最短删除这个变量中的以ov开头的任意字符串，并返回结果
var3=${variable_1%ov*}

# 输出一下
echo $var3

# 从尾部最长删除这个变量中的以ov开头的任意字符串，并返回结果
var4=${variable_1%%ov*}

# 输出一下
echo $var4

# 例如更改本机当前PATH路径变量的第一个bin
echo $PATH
var5=${PATH/bin/BIN}

# 更改PATH路径中的所有bin
var6=${PATH//bin/BIN}
```

### 1.2 字符串处理

|语法|说明|
|:-------|:-------|
|${#string}|无|
|expr length "$string"|string有空格，则必须加双引号，无空格时候，有没有双引号都可以|
|expr index $string $substring|获取字串在字符串中的索引位置|
|${string:position}|从string中的position开始|
|${string:position:length}|从string中的position开始，匹配长度为length|
|${string:-position}|从string中的右边开始匹配|
|${string:(position)}|从string中的左边开始匹配|
|expr substr $string $position $length|从position开始，匹配长度为length|

<br/>

