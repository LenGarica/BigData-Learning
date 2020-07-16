# Linux基础

目录：

- [Liunx常用基本命令](#liunx常用基本命令)
  - [关机和重启](#关机和重启)
  - [帮助命令](#帮助命令)
  - [目录操作命令](#目录操作命令)
  - [文件操作命令](#文件操作命令)
  - [权限修改](#权限修改)
  - [压缩文件操作](#压缩文件操作)
  - [查找命令](#查找命令)
  - [系统服务](#系统服务)
  - [网络管理](#网络管理)
  - [定时任务指令crontab 配置](#定时任务指令crontab-配置)
  - [其他命令](#其他命令)
- [Vim](#vim)


## Liunx常用基本命令

### 关机和重启

```
关机
    shutdown -h now        立刻关机
    shutdown -h 5        5分钟后关机
    poweroff            立刻关机
重启
    shutdown -r now        立刻重启
    shutdown -r 5        5分钟后重启
    reboot                立刻重启
```

### 帮助命令

```
--help命令
  shutdown --help：
  ifconfig  --help：查看网卡信息
 
man命令（命令说明书） 
  man shutdown
  注意：man shutdown打开命令说明书之后，使用按键q退出
```

### 目录操作命令


- 目录切换 cd
```
命令：cd 目录

cd /        切换到根目录
cd /usr        切换到根目录下的usr目录
cd ../        切换到上一级目录 或者  cd ..
cd ~        切换到home目录
cd -        切换到上次访问的目录
```

- 目录查看 ls [-al]
```
命令：ls [-al]

ls                查看当前目录下的所有目录和文件
ls -a            查看当前目录下的所有目录和文件（包括隐藏的文件）
ls -l 或 ll       列表查看当前目录下的所有目录和文件（列表查看，显示更多信息）
ls /dir            查看指定目录下的所有目录和文件   如：ls /usr
```

- 目录操作【增，删，改，查】
```
创建目录【增】 mkdir
命令：mkdir 目录

mkdir    aaa            在当前目录下创建一个名为aaa的目录
mkdir    /usr/aaa    在指定目录下创建一个名为aaa的目录
```

- 删除目录或文件【删】rm
```
命令：rm [-rf] 目录

删除文件：
rm 文件        删除当前目录下的文件
rm -f 文件    删除当前目录的的文件（不询问）

删除目录：
rm -r aaa    递归删除当前目录下的aaa目录
rm -rf aaa    递归删除当前目录下的aaa目录（不询问）

全部删除：
rm -rf *    将当前目录下的所有目录和文件全部删除
rm -rf /*    【自杀命令！慎用！慎用！慎用！】将根目录下的所有文件全部删除

注意：rm不仅可以删除目录，也可以删除其他文件或压缩包，为了方便大家的记忆，无论删除任何目录或文件，都直接使用 rm -rf 目录/文件/压缩包
```

- 目录修改【改】mv 和 cp
```
一、重命名目录
    命令：mv 当前目录  新目录
    例如：mv aaa bbb    将目录aaa改为bbb
    注意：mv的语法不仅可以对目录进行重命名而且也可以对各种文件，压缩包等进行    重命名的操作

二、剪切目录
    命令：mv 目录名称 目录的新位置
    示例：将/usr/tmp目录下的aaa目录剪切到 /usr目录下面     mv /usr/tmp/aaa /usr
    注意：mv语法不仅可以对目录进行剪切操作，对文件和压缩包等都可执行剪切操作

三、拷贝目录
    命令：cp -r 目录名称 目录拷贝的目标位置   -r代表递归
    示例：将/usr/tmp目录下的aaa目录复制到 /usr目录下面     cp /usr/tmp/aaa  /usr
    注意：cp命令不仅可以拷贝目录还可以拷贝文件，压缩包等，拷贝文件和压缩包时不    用写-r递归
```

- 搜索目录【查】find
```
命令：find 目录 参数 文件名称
示例：find /usr/tmp -name 'a*'    查找/usr/tmp目录下的所有以a开头的目录或文件
```

### 文件操作命令
文件操作【增，删，改，查】

```
新建文件【增】touch
命令：touch 文件名
示例：在当前目录创建一个名为aa.txt的文件        touch  aa.txt


删除文件 【删】 rm
命令：rm -rf 文件名

修改文件【改】 vi或vim
【vi编辑器的3种模式】
    基本上vi可以分为三种状态，分别是命令模式（command mode）、插入模式（Insert mode）和底行模式（last line mode），各模式的功能区分如下：
1) 命令行模式command mode）
      控制屏幕光标的移动，字符、字或行的删除，查找，移动复制某区段及进入Insert mode下，或者到 last line mode。
      命令行模式下的常用命令：
      【1】控制光标移动：↑，↓，j
      【2】删除当前行：dd 
      【3】查找：/字符
      【4】进入编辑模式：i o a
      【5】进入底行模式：:
      
2) 编辑模式（Insert mode）
      只有在Insert mode下，才可以做文字输入，按「ESC」键可回到命令行模式。
      编辑模式下常用命令：
      【1】ESC 退出编辑模式到命令行模式；
      
3) 底行模式（last line mode）
     将文件保存或退出vi，也可以设置编辑环境，如寻找字符串、列出行号……等。
     底行模式下常用命令：
     【1】退出编辑：   :q
     【2】强制退出：   :q!
     【3】保存并退出：  :wq

- 打开文件

命令：vi 文件名
示例：打开当前目录下的aa.txt文件     vi aa.txt 或者 vim aa.txt

注意：使用vi编辑器打开文件后，并不能编辑，因为此时处于命令模式，点击键盘i/a/o进入编辑模式。

编辑文件

使用vi编辑器打开文件后点击按键：i ，a或者o即可进入编辑模式。

i:在光标所在字符前开始插入
a:在光标所在字符后开始插入
o:在光标所在行的下面另起一新行插入

保存或者取消编辑

保存文件：

第一步：ESC  进入命令行模式
第二步：:     进入底行模式
第三步：wq     保存并退出编辑

取消编辑：

第一步：ESC  进入命令行模式
第二步：:     进入底行模式
第三步：q!     撤销本次修改并退出编辑


- 文件的查看【查】
文件的查看命令：cat/more/less/tail

cat：看最后一屏

示例：使用cat查看/etc/sudo.conf文件，只能显示最后一屏内容
cat sudo.conf

more：百分比显示

示例：使用more查看/etc/sudo.conf文件，可以显示百分比，回车可以向下一行，空格可以向下一页，q可以退出查看
more sudo.conf

less：翻页查看

示例：使用less查看/etc/sudo.conf文件，可以使用键盘上的PgUp和PgDn向上    和向下翻页，q结束查看
less sudo.conf

tail：指定行数或者动态查看

示例：使用tail -10 查看/etc/sudo.conf文件的后10行，Ctrl+C结束  
tail -10 sudo.conf

```


### 权限修改

```
rwx：r代表可读，w代表可写，x代表该文件是一个可执行文件，如果rwx任意位置变为-则代表不可读或不可写或不可执行文件。

示例：给aaa.txt文件权限改为可执行文件权限，aaa.txt文件的权限是-rw-------

第一位：-就代表是文件，d代表是文件夹
第一段（3位）：代表拥有者的权限
第二段（3位）：代表拥有者所在的组，组员的权限
第三段（最后3位）：代表的是其他用户的权限

   421  421  421
-  rw-   ---     ---

命令：chmod +x aaa.txt
或者采用8421法
命令：chmod 100 aaa.txt
```


### 压缩文件操作

- 打包和压缩
```
Windows的压缩文件的扩展名  .zip/.rar
linux中的打包文件：aa.tar      
linux中的压缩文件：bb.gz    
linux中打包并压缩的文件：.tar.gz

Linux中的打包文件一般是以.tar结尾的，压缩的命令一般是以.gz结尾的。
而一般情况下打包和压缩是一起进行的，打包并压缩后的文件的后缀名一般.tar.gz。

命令：tar -zcvf 打包压缩后的文件名 要打包的文件
其中：z：调用gzip压缩命令进行压缩
  c：打包文件
  v：显示运行过程
  f：指定文件名
  
示例：打包并压缩/usr/tmp 下的所有文件 压缩后的压缩包指定名称为xxx.tar
tar -zcvf ab.tar aa.txt bb.txt 
或：tar -zcvf ab.tar  *
```

- 解压

```
命令：tar [-zxvf] 压缩文件    
其中：x：代表解压
示例：将/usr/tmp 下的ab.tar解压到当前目录下
示例：将/usr/tmp 下的ab.tar解压到根目录/usr下
tar -xvf ab.tar -C /usr------C代表指定解压的位置
```


### 查找命令
- grep
```
grep命令是一种强大的文本搜索工具

使用实例：

ps -ef | grep sshd  查找指定ssh服务进程 
ps -ef | grep sshd | grep -v grep 查找指定服务进程，排除gerp身 
ps -ef | grep sshd -c 查找指定进程个数 
```

- find
```
find命令在目录结构中搜索文件，并对搜索结果执行指定的操作。 

find 默认搜索当前目录及其子目录，并且不过滤任何结果（也就是返回所有文件），将它们全都显示在屏幕上。

使用实例：

find . -name "*.log" -ls  在当前目录查找以.log结尾的文件，并显示详细信息。 
find /root/ -perm 600   查找/root/目录下权限为600的文件 
find . -type f -name "*.log"  查找当目录，以.log结尾的普通文件 
find . -type d | sort   查找当前所有目录并排序 
find . -size +100M  查找当前目录大于100M的文件
```

- locate
```
locate 让使用者可以很快速的搜寻某个路径。默认每天自动更新一次，所以使用locate 命令查不到最新变动过的文件。为了避免这种情况，可以在使用locate之前，先使用updatedb命令，手动更新数据库。如果数据库中没有查询的数据，则会报出locate: can not stat () `/var/lib/mlocate/mlocate.db': No such file or directory该错误！updatedb即可！

yum -y install mlocate 如果是精简版CentOS系统需要安装locate命令

使用实例：

updatedb
locate /etc/sh 搜索etc目录下所有以sh开头的文件 
locate pwd 查找和pwd相关的所有文件
```

- whereis
```
whereis命令是定位可执行文件、源代码文件、帮助文件在文件系统中的位置。这些文件的属性应属于原始代码，二进制文件，或是帮助文件。

使用实例：

whereis ls    将和ls文件相关的文件都查找出来
```

- which
```
which命令的作用是在PATH变量指定的路径中，搜索某个系统命令的位置，并且返回第一个搜索结果。

使用实例：

which pwd  查找pwd命令所在路径 
which java  查找path中java的路径 
```

- su、sudo
- su
```
su用于用户之间的切换。但是切换前的用户依然保持登录状态。如果是root 向普通或虚拟用户切换不需要密码，反之普通用户切换到其它任何用户都需要密码验证。

su test:切换到test用户，但是路径还是/root目录
su - test : 切换到test用户，路径变成了/home/test
su : 切换到root用户，但是路径还是原来的路径
su - : 切换到root用户，并且路径是/root
su不足：如果某个用户需要使用root权限、则必须要把root密码告诉此用户。

退出返回之前的用户：exit
```

- sudo
```
sudo是为所有想使用root权限的普通用户设计的。可以让普通用户具有临时使用root权限的权利。只需输入自己账户的密码即可。

进入sudo配置文件命令：

vi /etc/sudoer或者visudo
案例：
允许hadoop用户以root身份执行各种应用命令，需要输入hadoop用户的密码。
hadoop  ALL=(ALL)   ALL 
 
案例：
只允许hadoop用户以root身份执行ls 、cat命令，并且执行时候免输入密码。 
配置文件中： 
hadoop  ALL=NOPASSWD:  /bin/ls, /bin/cat 
```

### 系统服务
```
service iptables status  --查看iptables服务的状态
service iptables start  --开启iptables服务
service iptables stop  --停止iptables服务
service iptables restart  --重启iptables服务
 
chkconfig iptables off  --关闭iptables服务的开机自启动
chkconfig iptables on  --开启iptables服务的开机自启动
```

### 网络管理
- 主机名配置
```
[root@node1 ~]# vi /etc/sysconfig/network
NETWORKING=yes
HOSTNAME=node1
```
- IP 地址配置
[root@node1 ~]# vi /etc/sysconfig/network-scripts/ifcfg-eth0
- 域名映射
/etc/hosts文件用于在通过主机名进行访问时做ip地址解析之用。所以，你想访问一个什么样的主机名，就需要把这个主机名和它对应的ip地址。
```
[root@node1 ~]# vi /etc/hosts
#### 在最后加上
192.168.52.201  node1
192.168.52.202  node2
192.168.52.203  node3
```

### 定时任务指令crontab 配置
crontab是Unix和Linux用于设置定时任务的指令。通过crontab命令，可以在固定间隔时间,执行指定的系统指令或shell脚本。时间间隔的单位可以是分钟、小时、日、月、周及以上的任意组合。

```
crontab安装：

yum install crontabs
服务操作说明：

service crond start   ## 启动服务 
service crond stop    ## 关闭服务 
service crond restart ## 重启服务


-命令格式
crontab [-u user] file

crontab [-u user] [ -e | -l | -r ]

参数说明：

-u user：用来设定某个用户的crontab服务  

file：file是命令文件的名字,表示将file做为crontab的任务列表文件

并载入crontab。

-e：编辑某个用户的crontab文件内容。如果不指定用户，则表示编辑当前

用户的crontab文件。

-l：显示某个用户的crontab文件内容。如果不指定用户，则表示显示当前

用户的crontab文件内容。

-r：删除定时任务配置，从/var/spool/cron目录中删除某个用户的crontab

文件，如果不指定用户，则默认删除当前用户的crontab文件。

命令示例：

crontab file [-u user] ## 用指定的文件替代目前的crontab
crontab -l [-u user]  ## 列出用户目前的crontab
crontab -e [-u user]  ## 编辑用户目前的crontab
9.2 配置说明、实例
命令：*   *    *   *   *   command  

解释：分  时  日  月  周  命令

第1列表示分钟1～59 每分钟用*或者 */1表示    

第2列表示小时0～23（0表示0点）

第3列表示日期1～31  

第4列表示月份1～12  

第5列标识号星期0～6（0表示星期天）  

第6列要运行的命令

配置实例：

先打开定时任务所在的文件：
crontab -e
 
每分钟执行一次date命令 
*/1 * * * * date >> /root/date.txt
 
每晚的21:30重启apache。 
30 21 * * * service httpd restart
 
每月1、10、22日的4 : 45重启apache。  
45 4 1,10,22 * * service httpd restart
 
每周六、周日的1 : 10重启apache。 
10 1 * * 6,0 service httpd restart
 
每天18 : 00至23 : 00之间每隔30分钟重启apache。
0,30   18-23    *   *   *   service httpd restart
晚上11点到早上7点之间，每隔一小时重启apache
*  23-7/1    *   *   *   service httpd restart
```

### 其他命令

```
查看当前目录：pwd
命令：pwd     查看当前目录路径

查看进程：ps -ef
命令：ps -ef    查看所有正在运行的进程

结束进程：kill
命令：kill pid 或者 kill -9 pid(强制杀死进程)           pid:进程号

网络通信命令：
ifconfig：查看网卡信息

命令：ifconfig 或 ifconfig | more

ping：查看与某台机器的连接情况

命令：ping ip

netstat -an：查看当前系统端口

命令：netstat -an

搜索指定端口
命令：netstat -an | grep 8080

配置网络
命令：setup

重启网络
命令：service network restart

切换用户
命令：su - 用户名

关闭防火墙
命令：chkconfig iptables off

或者：

 iptables -L;
 iptables -F;
 service iptables stop
修改文件权限
命令：chmod 777

清屏
命令：ctrl + l

```

## Vim

6种基本模式中有

- 普通模式：此为vim默认启动的模式，按a或者i键进入插入模式，其他操作：

```
x       　删除游标所在的字符
X       　删除游标所在前一个字符
Delete  　与x相同
dd　　　　　删除整行
dw       　删除一个单词（不适用中文）
d$或D    　删除至行尾
普通模式下.（小数点）表示重复进行上一次命令操作

1. 删除操作：按x可以删除第一个字符，输入小数点  .  会再次删除一个字符，
        输入 dd 后会删除一行，
        ndd向下删除n行（n为数字），
        dw或者daw删除一个单词，dnw（n替换为相应数字）表示删除n个单词。
        其实前面讲得 dd 删除命令就是剪切，你每次 dd 删除文档内容后，便可以使用 p 来粘贴，
        也这一点可以实现一个交换上下行：ddp ,就这么简单，即实现了快速交换光标所在行与它下面的行

2. 跳转操作：在命令行模式下，输入：set nu可以显示文本的行号，
        输入  gg  游标会移动到第一行；
        输入n+shift+g（n为行号）便可以跳转到第n行，输入shift+g或者G可以调到最后一行。
        在跳转后，可以使用ctrl+o快速回到上一次跳转前光标所在位置。

3. 复制及粘贴文本

普通模式中使用y复制

普通模式中，yy复制游标所在的整行（3yy表示复制3行）
普通模式中，y^ 复制至行首，或y0。不含光标所在处字符。
普通模式中，y$ 复制至行尾。含光标所在处字符。
普通模式中，yw 复制一个单词。
普通模式中，y2w 复制两个单词。
普通模式中，yG 复制至文本末。
普通模式中，y1G 复制至文本开头。
普通模式中使用 p 粘贴

普通模式中，p(小写)代表粘贴至光标后（下）
普通模式中，P(大写)代表粘贴至光标前（上）

4. 替换和撤销(Undo)命令

替换和Undo命令都是针对普通模式下的操作

命令	                      说明
r+<待替换字母>	               将游标所在字母替换为指定字母
R	                         连续替换，直到按下Esc
cc	                         替换整行，即删除游标所在行，并进入插入模式
cw	                         替换一个单词，即删除一个单词，并进入插入模式
C(大写)	                      替换游标以后至行末
~	                         反转游标所在字母大小写
u{n}	                     撤销一次或n次操作
U(大写)	                      撤销当前行的所有修改
Ctrl+r	                     redo，即撤销undo的操作

5. 缩进操作

普通模式下输入>> 整行将向右缩进（使用，用于格式化代码超爽）
普通模式下输入<< 整行向左回退
普通模式下输入:进入命令行模式下对shiftwidth值进行设置可以控制缩进和回退的字符数
6. 文本位置调整

命令行模式下输入:ce(center)命令使本行内容居中

:ce
命令行模式下输入:ri(right)命令使本行文本靠右

:ri
命令行模式下输入:le(left)命令使本行内容靠左

:le

7. 快速查找

普通模式下输入 / 然后键入需要查找的字符串 按回车后就会进行查找。 
？ 与/ 功能相同，只不过 ？ 是向上而 / 是向下查找。 
进入查找之后，输入n 和 N 可以继续查找。 
n是查找下一个内容,N查找上一个内容。

8. 高级查找

普通模式下输入\*寻找游标所在处的单词
普通模式下输入\#同上，但 \# 是向前（上）找，\*则是向后（下）找
普通模式下输入g\*同\* ，但部分符合该单词即可
普通模式下输入g\#同\# ，但部分符合该单词即可
以上查找n,N 的继续查找命令依然可以用

```

- 插入模式：用户就可以进行文档编辑，按ESC可以退出到普通模式。

```
i         在当前光标处进行编辑
I         在行首插入
A         在行末插入
a         在光标后插入编辑
o　　　　　在当前行后插入一个新行
O         在当前行前插入一个新行
cw　　　　　替换从光标所在位置后到一个单词结尾的字符

```

- 可视模式\选择模式\Ex模式（不做介绍）

命令行模式：执行命令：键，搜索（/和？键）或者过滤命令键（！）

```
:q!         强制退出，不保存
:q          退出
:wq!        强制保存并退出
:w          另存为
:saveas     另存为
:x          保存并退出
:wq         保存并退出

```

- 使用vim编辑

```
编辑多个文件有两种形式，一种是在进入vim前使用的参数就是多个文件。另一种就是进入vim后再编辑其他的文件。 同时创建两个新文件并编辑

$ vim 1.txt 2.txt
默认进入1.txt文件的编辑界面

命令行模式下输入 :n 编辑 2.txt 文件，可以加 ! 即 :n! 强制切换，之前一个文件的输入没有保存，仅仅切换到另一个文件
命令行模式下输入 :N 编辑 1.txt 文件，可以加 ! 即 :N! 强制切换，之前文件内的输入没有保存，仅仅是切换到另一个文件
进入vim后打开新文件

命令行模式下输入:e 3.txt 打开新文件3.txt
命令行模式下输入:e# 回到前一个文件
命令行模式下输入:ls可以列出以前编辑过的文档
命令行模式下输入:b 2.txt（或者编号）可以直接进入文件2.txt编辑
命令行模式下输入:bd 2.txt（或者编号）可以删除以前编辑过的列表中的文件项目
命令行模式下输入:e! 4.txt，新打开文件4.txt，放弃正在编辑的文件
命令行模式下输入:f 显示正在编辑的文件名
命令行模式下输入:f new.txt，改变正在编辑的文件名字为new.txt
```

- 恢复文件
```
如果因为断电等原因造成文档没有保存，可以采用恢复方式，vim -r进入文档后，输入:ewcover 1.txt来恢复

$ vim -r 1.txt
```

- 视模式命令简介

```
在普通模式下输入 v（小写），进入字符选择模式，就可以移动光标，光标走过的地方就会选取。再次按下v后就会取消选取。
在普通模式下输入 Shift+v（小写），进入行选择模式，按下V之后就会把整行选取，您可以上下移动光标选更多的行，同样，再按一次 Shift+v 就可以取消选取。
在普通模式下输入 Ctrl+v（小写），这是区域选择模式，可以进行矩形区域选择，再按一次 Ctrl+v 取消选取。
在可视模式下输入 d 删除选取区域内容
在可视模式下输入y复制选取区域内容
```

- 视窗操作简介

```
vim 可以在一个界面里打开多个窗口进行编辑，这些编辑窗口称为 vim 的视窗。 打开方法有很多种，例如可以使用在命令行模式下输入 :new 打开一个新的 vim 视窗，并进入视窗编辑一个新文件（普通模式下输入 Ctrl+w也可以），除了 :new 命令，下述列举的多种方法也可以在命令模式或普通模式下打开新的视窗：

命令行模式下输入:sp 1.txt 打开新的水平分屏视窗来编辑1.txt
命令行模式下输入:vsp 2.txt 打开新的垂直分屏视窗来编辑2.txt
普通模式下Ctrl+w s 将当前窗口分割成两个水平的窗口
普通模式下Ctrl+w v 将当前窗口分割成两个垂直的窗口
普通模式下Ctrl+w q 即 :q 结束分割出来的视窗。如果在新视窗中有输入需要使用强制符！即:q!
普通模式下Ctrl+w o 打开一个视窗并且隐藏之前的所有视窗
普通模式下Ctrl+w j 移至下面视窗
普通模式下Ctrl+w k 移至上面视窗
普通模式下Ctrl+w h 移至左边视窗
普通模式下Ctrl+w l 移至右边视窗
普通模式下Ctrl+w J 将当前视窗移至下面
普通模式下Ctrl+w K 将当前视窗移至上面
普通模式下Ctrl+w H 将当前视窗移至左边
普通模式下Ctrl+w L 将当前视窗移至右边
普通模式下Ctrl+w - 减小视窗的高度
普通模式下Ctrl+w + 增加视窗的高度
```

- 创建加密文档

```
$ vim -x file1
输入您的密码 确认密码 这样在下一次打开时，vim就会要求你输入密码
```

- 在命令行模式中输入!可以执行外部的shell命令
```
:!ls 用于显示当前目录的内容
:!rm FILENAME用于删除名为 FILENAME 的文件
:w FILENAME可将当前 VIM 中正在编辑的文件另存为 FILENAME 文件
```

- vim中的查看帮助

```
普通模式下按F1打开vim自己预设的帮助文档
命令行模式下输入:h shiftwidth 打开名为shiftwidth的帮助文件
命令行模式下输入:ver 显示版本及参数
```

- vim的功能设定

```
可以在编辑文件的时候进行功能设定，如命令行模式下输入:set nu（显示行数），设定值退出vim后不会保存。要永久保存配置需要修改vim配置文件。 
vim的配置文件~/.vimrc(配置文件在/etc/vim/vimrc)，可以打开文件进行修改，不过务必小心不要影响vim正常使用

获取目前的设定

命令行模式下输入:set或者:se显示所有修改过的配置
命令行模式下输入:set all 显示所有的设定值
命令行模式下输入:set option? 显示option的设定值
命令行模式下输入:set nooption 取消当前设定值
set功能的说明

命令行模式下输入:set autoindent(ai) 设置自动缩进
命令行模式下输入:set autowrite(aw) 设置自动存档，默认未打开
命令行模式下输入:set background=dark或light，设置背景风格
命令行模式下输入:set backup(bk) 设置自动备份，默认未打开
命令行模式下输入: set cindent(cin) 设置C语言风格缩进
```
