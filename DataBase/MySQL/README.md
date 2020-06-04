## MySQL学习笔记

在学习前，请将两个MySQL脚本导入到MySQL中。先创建一个数据库Test，然后依次将create.sql和populate.sql导入到MySql中。先在MYSQL中创建一个名为test的数据库，使用use 数据库，再输入source 绝对路径/create.sql和source 绝对路径/populate.sql，便可以将数据添加到数据库中。

sql常用表

| 子句     | 说明                 | 是否必须使用             |
| -------- | -------------------- | ------------------------ |
| select   | 要返回的列或者表达式 | 是                       |
| from     | 从中检索数据的表     | 仅在从表中选择数据时使用 |
| where    | 行级过滤             | 否                       |
| group by | 分组说明             | 仅在按组计算聚集时使用   |
| having   | 组级过滤             | 否                       |
| order by | 输出排序顺序         | 否                       |
| limit    | 要检索的行数         | 否                       |

本项目中使用的数据库各个表中的数据

```s

customers表 存储所有顾客信息
+---------+----------------+---------------------+-----------+------------+----------+--------------+--------------+---------------------+
| cust_id | cust_name      | cust_address        | cust_city | cust_state | cust_zip | cust_country | cust_contact | cust_email          |
+---------+----------------+---------------------+-----------+------------+----------+--------------+--------------+---------------------+
|   10001 | Coyote Inc.    | 200 Maple Lane      | Detroit   | MI         | 44444    | USA          | Y Lee        | ylee@coyote.com     |
|   10002 | Mouse House    | 333 Fromage Lane    | Columbus  | OH         | 43333    | USA          | Jerry Mouse  | NULL                |
|   10003 | Wascals        | 1 Sunny Place       | Muncie    | IN         | 42222    | USA          | Jim Jones    | rabbit@wascally.com |
|   10004 | Yosemite Place | 829 Riverside Drive | Phoenix   | AZ         | 88888    | USA          | Y Sam        | sam@yosemite.com    |
|   10005 | E Fudd         | 4545 53rd Street    | Chicago   | IL         | 54545    | USA          | E Fudd       | NULL                |
+---------+----------------+---------------------+-----------+------------+----------+--------------+--------------+---------------------+

orderitems表 每个订单中实际的物品
+-----------+------------+---------+----------+------------+
| order_num | order_item | prod_id | quantity | item_price |
+-----------+------------+---------+----------+------------+
|     20005 |          1 | ANV01   |       10 |       5.99 |
|     20005 |          2 | ANV02   |        3 |       9.99 |
|     20005 |          3 | TNT2    |        5 |      10.00 |
|     20005 |          4 | FB      |        1 |      10.00 |
|     20006 |          1 | JP2000  |        1 |      55.00 |
|     20007 |          1 | TNT2    |      100 |      10.00 |
|     20008 |          1 | FC      |       50 |       2.50 |
|     20009 |          1 | FB      |        1 |      10.00 |
|     20009 |          2 | OL1     |        1 |       8.99 |
|     20009 |          3 | SLING   |        1 |       4.49 |
|     20009 |          4 | ANV03   |        1 |      14.99 |
+-----------+------------+---------+----------+------------+

oders表 顾客订单
+-----------+---------------------+---------+
| order_num | order_date          | cust_id |
+-----------+---------------------+---------+
|     20005 | 2005-09-01 00:00:00 |   10001 |
|     20006 | 2005-09-12 00:00:00 |   10003 |
|     20007 | 2005-09-30 00:00:00 |   10004 |
|     20008 | 2005-10-03 00:00:00 |   10005 |
|     20009 | 2005-10-08 00:00:00 |   10001 |
+-----------+---------------------+---------+

productnotes表 存储与特定产品有关的注释
+---------+---------+---------------------+-----------------------------------------------------------------------------------------------------------------------------------------------------------+
| note_id | prod_id | note_date           | note_text                                                                                                                                                 |
+---------+---------+---------------------+-----------------------------------------------------------------------------------------------------------------------------------------------------------+
|     101 | TNT2    | 2005-08-17 00:00:00 | Customer complaint:Sticks not individually wrapped, too easy to mistakenly detonate all at once.Recommend individual wrapping.                          |
|     102 | OL1     | 2005-08-18 00:00:00 | Can shipped full, refills not available.Need to order new can if refill needed.                                                                          |
|     103 | SAFE    | 2005-08-18 00:00:00 | Safe is combination locked, combination not provided with safe.This is rarely a problem as safes are typically blown up or dropped by customers.         |
|     104 | FC      | 2005-08-19 00:00:00 | Quantity varies, sold by the sack load.All guaranteed to be bright and orange, and suitable for use as rabbit bait.                                      |
|     105 | TNT2    | 2005-08-20 00:00:00 | Included fuses are short and have been known to detonate too quickly for some customers.Longer fuses are available (item FU1) and should be recommended. |
|     106 | TNT2    | 2005-08-22 00:00:00 | Matches not included, recommend purchase of matches or detonator (item DTNTR).                                                                            |
|     107 | SAFE    | 2005-08-23 00:00:00 | Please note that no returns will be accepted if safe opened using explosives.                                                                             |
|     108 | ANV01   | 2005-08-25 00:00:00 | Multiple customer returns, anvils failing to drop fast enough or falling backwards on purchaser. Recommend that customer considers using heavier anvils.  |
|     109 | ANV03   | 2005-09-01 00:00:00 | Item is extremely heavy. Designed for dropping, not recommended for use with slings, ropes, pulleys, or tightropes.                                       |
|     110 | FC      | 2005-09-01 00:00:00 | Customer complaint: rabbit has been able to detect trap, food apparently less effective now.                                                              |
|     111 | SLING   | 2005-09-02 00:00:00 | Shipped unassembled, requires common tools (including oversized hammer).                                                                                  |
|     112 | SAFE    | 2005-09-02 00:00:00 | Customer complaint:Circular hole in safe floor can apparently be easily cut with handsaw.                                                                |
|     113 | ANV01   | 2005-09-05 00:00:00 | Customer complaint:Not heavy enough to generate flying stars around head of victim. If being purchased for dropping, recommend ANV02 or ANV03 instead.   |
|     114 | SAFE    | 2005-09-07 00:00:00 | Call from individual trapped in safe plummeting to the ground, suggests an escape hatch be added.Comment forwarded to vendor.                            |
+---------+---------+---------------------+-----------------------------------------------------------------------------------------------------------------------------------------------------------+

products表 包含产品目录
+---------+---------+----------------+------------+----------------------------------------------------------------+
| prod_id | vend_id | prod_name      | prod_price | prod_desc                                                      |
+---------+---------+----------------+------------+----------------------------------------------------------------+
| ANV01   |    1001 | .5 ton anvil   |       5.99 | .5 ton anvil, black, complete with handy hook                  |
| ANV02   |    1001 | 1 ton anvil    |       9.99 | 1 ton anvil, black, complete with handy hook and carrying case |
| ANV03   |    1001 | 2 ton anvil    |      14.99 | 2 ton anvil, black, complete with handy hook and carrying case |
| DTNTR   |    1003 | Detonator      |      13.00 | Detonator (plunger powered), fuses not included                |
| FB      |    1003 | Bird seed      |      10.00 | Large bag (suitable for road runners)                          |
| FC      |    1003 | Carrots        |       2.50 | Carrots (rabbit hunting season only)                           |
| FU1     |    1002 | Fuses          |       3.42 | 1 dozen, extra long                                            |
| JP1000  |    1005 | JetPack 1000   |      35.00 | JetPack 1000, intended for single use                          |
| JP2000  |    1005 | JetPack 2000   |      55.00 | JetPack 2000, multi-use                                        |
| OL1     |    1002 | Oil can        |       8.99 | Oil can, red                                                   |
| SAFE    |    1003 | Safe           |      50.00 | Safe with combination lock                                     |
| SLING   |    1003 | Sling          |       4.49 | Sling, one size fits all                                       |
| TNT1    |    1003 | TNT (1 stick)  |       2.50 | TNT, red, single stick                                         |
| TNT2    |    1003 | TNT (5 sticks) |      10.00 | TNT, red, pack of 10 sticks                                    |
+---------+---------+----------------+------------+----------------------------------------------------------------+

vendors表 存储销售产品的供应商
+---------+----------------+-----------------+-------------+------------+----------+--------------+
| vend_id | vend_name      | vend_address    | vend_city   | vend_state | vend_zip | vend_country |
+---------+----------------+-----------------+-------------+------------+----------+--------------+
|    1001 | Anvils R Us    | 123 Main Street | Southfield  | MI         | 48075    | USA          |
|    1002 | LT Supplies    | 500 Park Street | Anytown     | OH         | 44333    | USA          |
|    1003 | ACME           | 555 High Street | Los Angeles | CA         | 90046    | USA          |
|    1004 | Furball Inc.   | 1000 5th Avenue | New York    | NY         | 11111    | USA          |
|    1005 | Jet Set        | 42 Galaxy Road  | London      | NULL       | N16 6PS  | England      |
|    1006 | Jouets Et Ours | 1 Rue Amusement | Paris       | NULL       | 45678    | France       |
+---------+----------------+-----------------+-------------+------------+----------+--------------+
```

#### 一、查询数据库名称时

  ```s

    输入　　　show databases;
    输出　　　这里省略(显示出所有的可用的数据库)

  ```

#### 二、选择数据库

  ```s

    输入　　　use Test;   # 要注意最后要打上分号，如果不打分号，回车不会执行
    输出　　　Database changed（已经选取了要使用的数据库）

  ```

#### 三、选择数据库后，显示当前数据库的表

  ```s

    输入　　　show tables;
    输出　　　省略（显示出当前数据库中的所有表）
  
  ```

#### 四、显示当前表中的列

 （即显示当前表的字段名，数据类型，键信息，默认值等）

  ```s
  
    输入     show columns from customers;（这句话可以换成DESCRIBE customers;MySQL提供这种快捷方法）
    输出　　　省略（显示customers表中的所有字段信息）

  ```

#### 五、select检索语句

  必须至少给出两条信息————想选择什么，以及从什么地方选择

  - 检索单个列　　select 列名　from  表名；
    例子：

    ```s
    select prod_name form products;  (多次查询时，每次返回的结果可能不一致，如果没有明确排序查询，则返回数据的顺序可能是数据被添加到表中的顺序，也可能不是)
    ```
  
  - 检索多个列　　select 列名１，列名２　from 表名；
    例子：

    ```s
    select prod_id,prod_name,prod_price from products;
    ```
  
  - 检索所有的列　select * from 表名；
    例子：

    ```s
    select * from products;(通常不要使用*，除非需要某个表中的所有列时。检索所有的列会降低检索和应用程序的性能)
    ```
  
  - 检索某个列后，出现多个相同的行，为了让每个行单一的显示，要添加distinct来标识。select distince 列名　from 表名;
    例子：
  
    ```s
    select distinct ven_id from products;
    如果使用了　　select distinct vend_id ,prod_price from products;　　则vend_id中不会出现单一的行。
    ```
  
  - 检索表中某个指定的行。例如返回某个表中的第一行或者前几行，可使用Limit子句。　　
    select 列名　from 表名 limit 数字；（表示查询某个表中某个列的前几行）
    例子:

    ```s
    select prod_name from products limit 5;
    ```

    检索表中某个指定的行到某行。例如返回某个表中的第５行开始后取５行，使用Limit子句。　　
    select 列名　from 表名 limit 数字１，数字２；（表示查询某个表中某个列的第数字１行开始后取数字２行）
    例子：　　　　

    ```s

    select prod_name from products limit 5 , 5;　　（从第五行开始后显示５行）

    ```

    为了避免混淆，MySQL5之后，添加offset关键字　select 列名　from 表名 limit 数字１　offset 数字2；（表示查询某个表中某个列的第数字２行开始后取数字１行）

  - 使用完全限定的表名称。（某些特定的地方要使用）

    ```s
    select products.prod_name from products;  
    ==等价于==　　select prod_name form products;

    select products.prod_name from crashcourse.products;  
    ==等价于==  　select prod_name from products; 
    ```

#### 六、排序检索数据
  
  - ORDER BY 
  
  此语句用于根据指定的列对结果集进行排序。ORDER BY语句默认按照升序对记录进行排序，也可以声明升序(ASC关键字)。如果希望按照降序对记录进行排序，可以使用 DESC 关键字。
  
  
  ```s

  select prod_name from products order by prod_name;
  select prod_name from products order by prod_name desc;

  ```
  
  
  经常需要按不止一个列进行数据排序。例如，如果要显示雇员清单，可能希望按姓和名排序（首先按姓排序，然后在每个姓中再按名排序）。如果多个雇员具有相同的姓，这样做很有用。为了按多个列排序，只要指定列名，列名之间用逗号分开即可（就像选择多个列时所做的那样）
  
  ```s

  select prod_id , prod_price , prod_name from products order by prod_price , prod_name;

  ```
  
  仅在多个行具有相同的prod_price值时才对产品按prod_name进行排序。如果prod_price列中所有的值都是唯一的，则不会按prod_name排序。

  - order by 和　limit的组合
  
  使用ORDER BY和LIMIT的组合，能够找出一个列中最高或最低的值。下面的例子演示如何找出最昂贵物品的值，在给出ORDER BY子句时，应该保证它位于FROM子句之后。如果使用LIMIT，它必须位于ORDER BY之后。使用子句的次序不对将产生错误消息。
  
  ```s

  select prod_price from products order by prod_pricce desc limit 1;

  ```

#### 七、过滤数据

  - 数据库表一般包含大量的数据，很少需要检索表中所有行。通常只会根据特定操作或报告的需要提取表数据的子集。只检索所需数据需要指定搜索条件，搜索条件也称为过滤条件。

  - where子句（在同时使用ORDER BY和WHERE子句时，应该让ORDER BY位于WHERE之后，否则将会产生错误）

  查询products表中商品价格为2.50的商品名。这条语句从products表中检索两个列，但不返回所有行，只返回prod_price值为2.50的行

  ```s

  select prod_name from products where prod_price = 2.50;
  
  ```
  查询products表中商品名字为fuses的信息

  ```s

  select prod_name , prod_price from products where prod_name = 'fuses';

  ```

  列出价格小于10的所有产品

  ```s

  select prod_name , prod_price from products where prod_price < 10 ;

  ```

  列出价格小于等于10的所有产品

  ```s

  select prod_name , prod_price from products where prod_price <= 10;

  ```

  列出不由供应商１００３制造的所有商品

  ```s

  select vend_id , prod_name from products where vend_id <> 1003;  # <> 相当于　!=

  ```

  用where来进行范围值检查，从商品表中列出价格在５－１０之间的商品名字和价格

  ```s

  select prod_name , prod_price from products where prod_price between 5 and 10;

  ```

  用where进行空值Null的检查，列出商品表中的价格为０的商品名字

  ```s

  select prod_name from products where prod_price is null;

  ```

  - where子句与AND或者OR进行查询组合

  检索由供应商1003制造且价格小于等于10美元的所有产品的名称和价格。这条SELECT语句中的WHERE子句包含两个条件，并且用AND关键字联结它们。AND指示DBMS只返回满足所有给定条件的行。如果某个产品由供应商1003制造，但它的价格高于10美元，则不检索它。类似，如果产品价格小于10美元，但不是由指定供应商制造的也不被检索。

  ```s

  select prod_id , prod_price , prod_name from products where vend_id = 1003 and prod_price <= 10;

  # or的使用
  select prod_price , prod_name from products where vend_id = 1003 or vend_id = 1002;

  ```
  
  但是，组合AND和OR带来了一个有趣的问题。为了说明这个问题，来看一个例子。假如需要列出价格为10美元（含）以上且由1002或1003制造的所有产品。

  ```s

  select prod_name , prod_price from products where vend_id =1002 or vend_id = 1003 and prod_price >= 10;
  # 这句执行后，会发现结果中有小于10的行，这是因为SQL（像多数语言一样）在处理OR操作符前，优先处理AND操作符。当SQL看到上述WHERE子句时，它理解为由供应商1003制造的任何价格为10美元（含）以上的产品，或者由供应商1002制造的任何产品，而不管其价格如何。换句话说，由于AND在计算次序中优先级更高，操作符被错误地组合了

  # 此问题的解决方法是使用圆括号明确地分组相应的操作符。请看下面的SELECT语句及输出：
  select prod_name , prod_price from products where (vend_id =1002 or vend_id = 1003) and prod_price >= 10;
  # 这条语句中，前两个条件用圆括号括了起来。因为圆括号具有较AND或OR操作符高的计算次序，DBMS首先过滤圆括号内的OR条件。这时，SQL语句变成了选择由供应商1002或1003制造的且价格都在10美元（含）以上的任何产品，这正是我们所希望的。

  ```

  - where子句与IN进行查询组合

  圆括号在WHERE子句中还有另外一种用法。IN操作符用来指定条件范围，范围中的每个条件都可以进行匹配。IN取合法值的由逗号分隔的清单，全都括在圆括号中。

  ```s

  select prod_name , prod_price from products where vend_id in (1002,1003) order by prod_name;
  # in在某些方面与or的作用相同
  select prod_name , prod_price from products where vend_id = 1002 or vend_id = 1003  order by prod_name;
  # 为什么要使用IN操作符？其优点具体如下。
  # 在使用长的合法选项清单时，IN操作符的语法更清楚且更直观。
  # 在使用IN时，计算的次序更容易管理（因为使用的操作符更少）。
  # IN操作符一般比OR操作符清单执行更快。
  # IN的最大优点是可以包含其他SELECT语句，使得能够更动态地建立WHERE子句。IN WHERE子句中用来指定要匹配值的清单的关键字，功能与OR相当

  ```

  - where子句与NOT操作符有且只有一个功能，那就是否定它之后所跟的任何条件。

  列出除1002和1003之外的所有供应商制造的产品

  ```s

  select prod_name , prod_price from products where vend_id NOT in (1002 , 1003) order by prod_name;
  # MySQL支持使用NOT对IN、BETWEEN和EXISTS子句取反，这与多数其他DBMS允许使用NOT对各种条件取反有很大的差别。

  ```

#### 八、通配符过滤

  (在where子句中使用，通配符用来匹配值的一部分的特殊字符，由字面值、通配符或两者组合构成的搜索条件)

  - Like操作符，在使用通配符过滤的时候，要加上Like。
  
  - %通配符的使用

    - 使用%通配符进行以某个词开头的匹配

    最常使用的通配符之一，在搜索串中，%表示任何字符出现的任意次数，例如，为了找出所有以词jet起头的产品，可以使用以下语句。加上％后，表示以jet开头的信息都要列出来，无论后面的值是什么。

    ```s

    select prod_id,prod_name from products where prod_name like　'jet%';

    ```

    要匹配的字符会按照MySQL设置的内容，区分大小写。

    - 使用%进行任意位置的匹配

    搜索模式'%anvil'表示匹配任何位置包含文本anvil的值，而不论他之前或者之后出现什么样的字符。但是尾空格将会干扰通配符，如果在anvil的后面出现一个或者多个空格，则where prod_name like '%anvil%'将不会匹配它们。可以使用函数来解决，具体看后面的操作。

    ```s

    select prod_id,prod_name from products where prod_name like '%anvil%';

    ```

    - ％通配符也可以出现在搜索模式的中间，下面的例子找出以s开头，以e结尾的所有产品。不过在实际中，这并没什么用处。

    ```s

    select prod_name from products where prod_name like 's%e';

    ```

    注：％无法匹配到NULL。

  - 下划线(_)通配符，与%通配符的作用是一样的，但是下划线通配符只能匹配单个字符

    ```s

    # 这句话的意思是，匹配到ton anvil前面的任意一个字符
    select prod_id,prod_name from products where prod_name like '_ ton anvil';

    # 而下面这句话，能匹配到ton anvil前面的任意字符
    select prod_id,prod_name from products where prod_name like '% ton anvil';

    ```

#### 九、计算字段

  - 拼接字段(Concat)

  ```s

  #查询vendors表中包含供应商名和位置信息，假如要生成一个供应商报表，需要在供应商的名字中按照name(location)这样的格式列出供应商的信息。则要使用Concat()函数，拼接串，把多个串连接起来形成一个较长的串，各个串之间用逗号分隔。
  select Concat(vend_name ,'　(',vend_country,')') from vendors order by vend_name;

  ```

  - 删除多余空格来整理数据(RTrim()--删掉右侧的多余空格，LTrim()--删除掉左侧的多余空格，Trim()--删除掉多余的空格)

  ```s

  #RTrim()函数去掉值右边的所有空格，通过使用RTrim()，将各个列进行整理
  select Concat(RTrim(vend_name) ,'　(',vend_country,')') from vendors order by vend_name;
  select Concat(Trim(vend_name) ,'　(',vend_country,')') from vendors order by vend_name;
  select Concat(LTrim(vend_name) ,'　(',vend_country,')') from vendors order by vend_name;

  ```

  - 使用别名AS关键字

  ```s

  #查询vendors表中包含供应商名和位置信息，并将查询的结果更名为vend_title。
  select Concat(RTrim(vend_name) ,'　(',RTrim(vend_country),')') AS vend_title from vendors order by vend_name;

  ```
  
  - 使用乘法计算，除此之外，还提供加＋减－除/

  ```s

  #查询订单号为20005中的所有物品，并对所有的物品进行总价计算，将查询的总价计算结果更名为expanded_price
  select prod_id,quantity,item_price,quantity * item_price AS expanded_price from orderitems where order_num = 20005;
  
  ```

#### 十、函数

  - 文本处理函数。例如：删除文字前后空格的Trim()函数，返回串左边的字符Left()函数，返回串右边的字符Right()函数，返回串的长度Length()函数，找出串的一个子串Locate()函数，将所有结果转换为小写Lower()函数，返回子串的字符SubString()函数，返回串的SOUNDEX值Soundex()函数
    
  - Upper()函数
      
  ```s
  #将查询结果中的所有的小写字母全部转换为大写
  select vend_name,Upper(vend_name) AS vend_name_upcase from vendors order by vend_name;　 
      
  ```
  - 日期和时间处理函数，通过日期函数进行过滤。例如：增加一个日期AddDate()函数，增加一个时间AddTime()函数，返回当前日期CurDate()函数，返回当前时间CruTime()函数，返回日期时间的日期部分Date()函数，计算两个日期之差DataDiff()函数，返回一个时间的小时部分Hour()函数，返回一个时间的分钟部分Minute()函数等等。

  ```s

  #查询某年某月某日的订单
  select cust_id,order_num from orders where order_date = '2005-09-01';

  #将上面的进行改进，优先使用下面这种写法
  select cust_id,order_num from orders where Date(order_date) = '2005-09-01';
    
  #查看2005年9月的所有订单
  select cust_id , order_num from orders where Date(order_date) between '2005-09-01' and '2005-09-30';
  #改进，这样就不用在乎一个月有多少天了
  select cust_id , order_num from orders where Year(order_date) = 2005 and Month(order_date) = 9;
    
  ```
  - 数值处理函数，用于代数，三角，或者几何运算。例如：返回一个数的绝对值Abs()，返回一个角度的余弦Cos()，返回一个数的指数值Exp()，返回除操作的余数Mod()，返回圆周率Pi()，生成一个随机数Rand()，返回一个角度的正弦Sin()，返回一个数的平方根Sqrt()，返回一个角度的正切Tan()。

#### 十一、汇总数据

  - MySQL提供了五个聚集函数，AVG()返回某列的平均值，COUNT()返回某列的行数，MAX()返回某列的最大值，MIN()返回某列的最小值，SUM()返回某列值之和。
    
  - AVG()函数：

  ```s

  # 查询products表中的价格平均值
  select avg(prod_price) as avg_price from products;

  # 查询vend_id 为 1003的商品的平均价值
  select avg(prod_price) as avg_price from products where vend_id = 1003;

  ```

  - COUNT()函数

  ```s

  # 统计用户表中的客户的总数，使用count(*)可以对所有行计数，不管行中各列有什么值
  select count(*) as num_cust from customers;

  # 统计有电子邮件地址的客户
  select count(cust_email) as num_cust from customers;

  ```

  - MAX()函数

  ```s

  # Max()返回指定列中的最大值
  select max(prod_price) as max_price from products;

  ```

  - MIN()函数

  ```s

  # Min()功能正好与Max()功能相反，它返回制定列的最小值，与Max()一样，Min()要求制定列名
  select min(prod_price) as min_price from products;

  ```

  - SUM()函数

  ```s
      
  # 返回指定列值的和(总计)，检索所订单号为20005的物品的总数
  select sum(quantity) as item_ordered from orderitems 
  where order_num = 20005;

  # 合计订单号为20005的总的订单金额
  select sum(item_price*quantity) as total_price from orderitems 
  where order_num = 20005;

  ```

  - 聚合函数可以指定Distinct参数，只计算不同的数，将相同的数只保留一个，个数也只计算不同的。
    
  - 组合聚集函数
    
  ```s

  # 返回products表中物品的数目，产品价格的最高、最低以及平均值
  select count(*) as num_items,min(prod_price) as price_min,max(prod_price) as price_max,avg(prod_price) as price_avg from products;

  ```

#### 十二、分组数据

  - 创建分组：Group by，必须出现在where子句后，order by子句之前。


  ```s

  # 显示产品表中的供应商id和产品数量，按照供应商id排序，默认为升序排序
  select vend_id , count(*) as num_prods from products 
  group by vend_id;

  ```

  - 过滤分组：having，说白了，就是应用了group by，又想过滤掉一些不符合要求的数据。

  ```s

  # 检索出数量有两个以上的订单号，按照订单号分组
  select cust_id,count(*) as orders from orders 
  group by cust_id having count(*)>=2;

  ```

  where在这里是不起作用的，where应用在数据分组前进行过滤，having应用在分组后进行过滤。看下面这个例子。

  ```s

  select vend_id ,count(*) as num_prods from products 
  where prod_price >=10 group by vend_id having count(*) >=2;

  ```

  - 分组和排序：在Group by后使用order by将查询结果按照某种顺序排列。

  ```s

  # 查询订单项目表中的订单编号和总价，按照订单编号分组，总价要大于50，且按照总价进行排序
  select order_num,sum(quantity * item_price) as ordertotal from orderitems 
  group by order_num having sum(quantity * item_price)>=50 order by ordertotal;

  ```

#### 十三、使用子查询

  - 订单存储在两个表中。对于包含订单号、客户ID、订单日期的每个订单， orders表存储一行。各订单的物品存储在相关的orderitems表中。 orders表不存储客户信息。它只存储客户的ID。实际的客户信息存储在customers表中。现在，假如需要列出订购物品TNT2的所有客户，应该怎样检索？

  (1) 检索包含物品TNT2的所有订单的编号。

  (2) 检索具有前一步骤列出的订单编号的所有客户的ID。
   
  (3) 检索前一步骤返回的所有客户ID的客户信息。

  上述每个步骤都可以单独作为一个查询来执行。可以把一条SELECT语句返回的结果用于另一条SELECT语句的WHERE子句。

  第一条select语句很明确，将prod_id为TNT2的所有订单物品，检索其order_num

  ```s
  select order_num from orderitems where prod_id = 'TNT2';
  ```

  第二条select语句查询具有订单20005和20007的客户ID。

  ```s
  select cust_id from orders where order_num IN (20005,20007);
  ```

  现在将一二条进行整合，得出下面的语句

  ```s
  select cust_id from orders where order_num 
  in(select order_num from orderitems where prod_id = 'TNT2');
  ```

  在前者的基础上，第三条通过客户ID查询客户姓名和客户所在城市

  ```s
  select cust_name from customers where cust_id IN (10001,10004);
  ```

  现在整合三条查询，得出下面的语句

  ```s
  select cust_name from customers where cust_id 
  IN(select cust_id from orders where order_num 
  in(select order_num from orderitems where prod_id = 'TNT2'));
  ```

  对于能嵌套的子查询的数目没有限制，不过在实际使用时由于性能的限制，不能嵌套太多的子查询。

  - 使用子查询的另一方法是创建计算字段。假如需要显示customers表中每个客户的订单总数。订单与相应的客户ID存储在orders表中。为了执行这个操作，遵循下面的步骤。

  (1) 从customers表中检索客户列表。

  (2) 对于检索出的每个客户，统计其在orders表中的订单数目.

  ```s

  # 注意这里涉及外部查询的子查询，这种类型的子查询称为相关子查询。
  # 任何时候只要列名可能有多义性，就必须使用这种语法（表名和列名由一个句点分隔）。
  # orders.cust_id = customers.cust_id
  select cust_name , cust_state,(select count(*) from orders 
  where orders.cust_id = customers.cust_id) AS orders from customers 
  order by cust_name;
  # 结果
  +----------------+------------+--------+
  | cust_name      | cust_state | orders |
  +----------------+------------+--------+
  | Coyote Inc.    | MI         |      2 |
  | E Fudd         | IL         |      1 |
  | Mouse House    | OH         |      0 |
  | Wascals        | IN         |      1 |
  | Yosemite Place | AZ         |      1 |
  +----------------+------------+--------+

  # 如果不使用这种语法来区别多义性，则有下面的结果，这样的结果显然不正确
  # 有两个cust_id列，一个在customers中，另一个在orders中，需要比较这两个列以正确地把订单与它们相应的顾客匹配。
  # 如果不完全限定列名， MySQL将假定你是对orders表中的cust_id进行自身比较。
  # 而SELECT COUNT(*) FROM orders WHERE cust_id = cust_id;
  # 总是返回orders表中的订单总数（因为MySQL查看每个订单的cust_id是否与本身匹配，当然，它们总是匹配的）
  select cust_name , cust_state,(select count(*) from orders 
  where cust_id = cust_id) AS orders from customers 
  order by cust_name;
  +----------------+------------+--------+
  | cust_name      | cust_state | orders |
  +----------------+------------+--------+
  | Coyote Inc.    | MI         |      5 |
  | E Fudd         | IL         |      5 |
  | Mouse House    | OH         |      5 |
  | Wascals        | IN         |      5 |
  | Yosemite Place | AZ         |      5 |
  +----------------+------------+--------+

  ```

####　十四、联结表

  - SQL最强大的功能之一就是能在数据检索查询的执行中联结（ join）表。联结是利用SQL的SELECT能执行的最重要的操作，很好地理解联结及其语法是学习SQL的一个极为重要的组成部分。在能够有效地使用联结前，必须了解关系表以及关系数据库设计的一些基础知识。

  假如有一个包含产品目录的数据库表，其中每种类别的物品占一行。对于每种物品要存储的信息包括产品描述和价格，以及生产该产品的供应商信息。现在，假如有由同一供应商生产的多种物品，那么在何处存储供应商信息（如，供应商名、地址、联系方法等）呢？将这些数据与产品信息分开存储的理由如下。

  因为同一供应商生产的每个产品的供应商信息都是相同的，对每个产品重复此信息既浪费时间又浪费存储空间。
  
  如果供应商信息改变（例如，供应商搬家或电话号码变动），只需改动一次即可。如果有重复数据（即每种产品都存储供应商信息），很难保证每次输入该数据的方式都相同。不一致的数据在报表中很难利用。关键是，相同数据出现多次决不是一件好事，此因素是关系数据库设计的基础。关系表的设计就是要保证把信息分解成多个表，一类数据一个表。各表通过某些常用的值(即关系设计中的关系(relational))互相关联。
  
  在这个例子中，可建立两个表，一个存储供应商信息，另一个存储产品信息。 vendors表包含所有供应商信息，每个供应商占一行，每个供应商具有唯一的标识。此标识称为主键(primary key)，可以是供应商ID或任何其他唯一值。products表只存储产品信息，它除了存储供应商ID（ vendors表的主键）外不存储其他供应商信息。vendors表的主键又叫作products的外键，它将vendors表与products表关联，利用供应商ID能从vendors表中找出相应供应商的详细信息。外键为某个表中的一列，它包含另一个表的主键值，定义了两个表之间的关系。

  这样做的好处如下：

  供应商信息不重复，从而不浪费时间和空间；

  如果供应商信息变动，可以只更新vendors表中的单个记录，相关表中的数据不用改动；
  
  由于数据无重复，显然数据是一致的，这使得处理数据更简单。

  总之，关系数据可以有效地存储和方便地处理。因此，关系数据库的可伸缩性远比非关系数据库要好。

  - 为什么要使用联结？
  
  正如所述，分解数据为多个表能更有效地存储，更方便地处理，并且具有更大的可伸缩性。但这些好处是有代价的。如果数据存储在多个表中，怎样用单条SELECT语句检索出数据？
  
  答案是使用联结。简单地说，联结是一种机制，用来在一条SELECT语句中关联表，因此称之为联结。使用特殊的语法，可以联结多个表返回一组输出，联结在运行时关联表中正确的行。

  - 联结的使用，在联结两个表时，实际上做的是将第一个表中的每一行与第二个表中的每一行配对。

  从产品目录表和供应商表中找出供应商id和产品供应商id相同的供应商名字，产品名字，产品价格，按照供应商名字和商品名字进行排序

  ```s
  select vend_name , prod_name , prod_price from vendors , products where vendors.vend_id = products.vend_id 
  order by vend_name , prod_name;
  # 结果
  +-------------+----------------+------------+
  | vend_name   | prod_name      | prod_price |
  +-------------+----------------+------------+
  | ACME        | Bird seed      |      10.00 |
  | ACME        | Carrots        |       2.50 |
  | ACME        | Detonator      |      13.00 |
  | ACME        | Safe           |      50.00 |
  | ACME        | Sling          |       4.49 |
  | ACME        | TNT (1 stick)  |       2.50 |
  | ACME        | TNT (5 sticks) |      10.00 |
  | Anvils R Us | .5 ton anvil   |       5.99 |
  | Anvils R Us | 1 ton anvil    |       9.99 |
  | Anvils R Us | 2 ton anvil    |      14.99 |
  | Jet Set     | JetPack 1000   |      35.00 |
  | Jet Set     | JetPack 2000   |      55.00 |
  | LT Supplies | Fuses          |       3.42 |
  | LT Supplies | Oil can        |       8.99 |
  +-------------+----------------+------------+
  # 所指定的两个列（ prod_name和prod_price）在一个表中，而另一个列（ vend_name）在另一个表中。
  # 现在来看FROM子句。与以前的SELECT语句不一样，这条语句的FROM子句列出了两个表，分别是vendors和products。
  # 它们就是这条SELECT语句联结的两个表的名字。这两个表用WHERE子句正确联结， WHERE子句指示MySQL匹配vendors表中的vend_id和products表中的vend_id。
  # 可以看到要匹配的两个列以vendors.vend_id 和 products.vend_id指定。
  # 这里需要这种完全限定列名，因为如果只给出vend_id，则MySQL不知道指的是哪一个（它们有两个，每个表中一个）
  ```

  应该保证所有联结都有WHERE子句，否则MySQL将返回比想要的数据多得多的数据。同理，应该保证WHERE子句的正确性。不正确的过滤条件将导致MySQL返回不正确的数据。

  - 内部联结

  目前为止所用的联结称为等值联结（ equijoin），它基于两个表之间的相等测试。这种联结也称为内部联结。其实，对于这种联结可以使用稍微不同的语法来明确指定联结的类型。下面的SELECT语句返回与前面例子完全相同的数据。

  ```s
  select vend_name , prod_name , prod_price from vendors inner join products on vendors.vend_id = products.vend_id order by vend_name , prod_name;
  ```

  此语句中的SELECT与前面的SELECT语句相同，但FROM子句不同。这里，两个表之间的关系是FROM子句的组成部分，以INNER JOIN指定。在使用这种语法时，联结条件用特定的ON子句而不是WHERE子句给出。传递给ON的实际条件与传递给WHERE的相同。ANSI SQL规范首选INNER JOIN语法。此外，尽管使用WHERE子句定义联结的确比较简单，但是使用明确的联结语法能够确保不会忘记联结条件，有时候这样做也能影响性能。

  - 联结多个表

  显示编号为20005的订单中的物品。订单物品存储在orderitems表中。每个产品按其产品ID存储，它引用products表中的产品。这些产品通过供应商ID联结到vendors表中相应的供应商，供应商ID存储在每个产品的记录中。这里的FROM子句列出了3个表，而WHERE子句定义了这两个联结条件，而第三个联结条件用来过滤出订单20005中的物品。

  ```s
  select prod_name , vend_name , prod_price ,quantity from orderitems , products , vendors where products.vend_id = vendors.vend_id and orderitems.prod_id = products.prod_id and order_num = 20005;

  # 结果
  +----------------+-------------+------------+----------+
  | prod_name      | vend_name   | prod_price | quantity |
  +----------------+-------------+------------+----------+
  | .5 ton anvil   | Anvils R Us |       5.99 |       10 |
  | 1 ton anvil    | Anvils R Us |       9.99 |        3 |
  | TNT (5 sticks) | ACME        |      10.00 |        5 |
  | Bird seed      | ACME        |      10.00 |        1 |
  +----------------+-------------+------------+----------+
  ```

  注意：MySQL在运行时关联指定的每个表以处理联结。这种处理可能是非常耗费资源的，因此应该仔细，不要联结不必要的表。联结的表越多，性能下降越厉害。

#### 十五、创建高级联结

  - 使用表别名，为了缩短sql语句

  ```s
  select cust_name , cust_contact from customers as c , orders as o , orderitems as oi 
  where c.cust_id = o.cust_id and oi.order_num = o.order_num and prod_id = 'TNT2';

  # 结果
  +----------------+--------------+
  | cust_name      | cust_contact |
  +----------------+--------------+
  | Coyote Inc.    | Y Lee        |
  | Yosemite Place | Y Sam        |
  +----------------+--------------+
  ```

  - 自联结，顾名思义，就是自己的表联结自己

  自联结通常作为外部语句用来替代从相同表中检索数据时使用的子查询语句。虽然最终的结果是相同的，但有时候处理联结远比处理子查询快得多。应该试一下两种方法，以确定哪一种的性能更好

  查询生产DTNTR商品的生产商id，根据此id查询此生产商的所有商品名称和id

  ```s
  # 这是第一种解决方案，它使用了子查询。内部的SELECT语句做了一个简单的检索，返回生产ID为DTNTR的物品供应商的vend_id。该ID用于外部查询的WHERE子句中，以便检索出这个供应商生产的所有物品
  select prod_id , prod_name from products where vend_id = (select vend_id from products where prod_id = 'DTNTR');
  # 结果
  +---------+----------------+
  | prod_id | prod_name      |
  +---------+----------------+
  | DTNTR   | Detonator      |
  | FB      | Bird seed      |
  | FC      | Carrots        |
  | SAFE    | Safe           |
  | SLING   | Sling          |
  | TNT1    | TNT (1 stick)  |
  | TNT2    | TNT (5 sticks) |
  +---------+----------------+
  
  # 使用自联结
  # 此查询中需要的两个表实际上是相同的表，因此products表在FROM子句中出现了两次。
  # 虽然这是完全合法的，但对products的引用具有二义性，因为MySQL不知道你引用的是products表中的哪个实例。
  # 为解决此问题，使用了表别名。 products的第一次出现为别名p1，第二次出现为别名p2。
  # 现在可以将这些别名用作表名。例如， SELECT语句使用p1前缀明确地给出所需列的全名。
  # 如果不这样， MySQL将返回错误，因为分别存在两个名为prod_id、 prod_name的列。
  # MySQL不知道想要的是哪一个列（即使它们事实上是同一个列）。 
  # WHERE（通过匹配p1中的vend_id和p2中的vend_id）首先联结两个表，然后按第二个表中的prod_id过滤数据，返回所需的数据。
  select p1.prod_id , p1.prod_name from products as p1 , products as p2 where p1.vend_id = p2.vend_id and p2.prod_id = 'DTNTR';
  # 结果
  +---------+----------------+
  | prod_id | prod_name      |
  +---------+----------------+
  | DTNTR   | Detonator      |
  | FB      | Bird seed      |
  | FC      | Carrots        |
  | SAFE    | Safe           |
  | SLING   | Sling          |
  | TNT1    | TNT (1 stick)  |
  | TNT2    | TNT (5 sticks) |
  +---------+----------------+
  ```

  - 外部联结

  许多联结将一个表中的行与另一个表中的行相关联。但有时候会需要包含没有关联行的那些行。例如，可能需要使用联结来完成以下工作：

    对每个客户下了多少订单进行计数，包括那些至今尚未下订单的客户；

    列出所有产品以及订购数量，包括没有人订购的产品；

    计算平均销售规模，包括那些至今尚未下订单的客户。

  在上述例子中，联结包含了那些在相关表中没有关联行的行。这种类型的联结称为外部联结。

  ```s
  # 对列出每个客户下的订单号，包括那些至今尚未下订单的客户；
  select customers.cust_id , orders.order_num from customers left outer join orders on customers.cust_id = orders.cust_id;
  # 结果
  +---------+-----------+
  | cust_id | order_num |
  +---------+-----------+
  |   10001 |     20005 |
  |   10001 |     20009 |
  |   10002 |      NULL |
  |   10003 |     20006 |
  |   10004 |     20007 |
  |   10005 |     20008 |
  +---------+-----------+

  ```

  使用了关键字OUTER JOIN来指定联结的类型（而不是在WHERE子句中指定）。但是，与内部联结关联两个表中的行不同的是，外部联结还包括没有关联行的行。在使用OUTER JOIN语法时，必须使用RIGHT或LEFT关键字指定包括其所有行的表（ RIGHT指出的是OUTER JOIN右边的表，而LEFT指出的是OUTER JOIN左边的表）。上面的例子使用LEFT OUTER JOIN从FROM子句的左边表（ customers表）中选择所有行。

  - 使用带聚集函数的联结

  ```s
  #  检索所有客户及每个客户所下的订单数
  select customers.cust_name , customers.cust_id , count(orders.order_num) as num_ord 
  from customers 
  inner join orders 
  on customers.cust_id = orders.cust_id 
  group by customers.cust_id;

  # 结果
  +----------------+---------+---------+
  | cust_name      | cust_id | num_ord |
  +----------------+---------+---------+
  | Coyote Inc.    |   10001 |       2 |
  | Wascals        |   10003 |       1 |
  | Yosemite Place |   10004 |       1 |
  | E Fudd         |   10005 |       1 |
  +----------------+---------+---------+

  # 使用左外联结联结检索所有客户及每个客户所下的订单数
  select customers.cust_name , customers.cust_id , count(orders.order_num) as num_ord 
  from customers 
  left outer join orders 
  on customers.cust_id = orders.cust_id 
  group by customers.cust_id;

  # 结果
  +----------------+---------+---------+
  | cust_name      | cust_id | num_ord |
  +----------------+---------+---------+
  | Coyote Inc.    |   10001 |       2 |
  | Mouse House    |   10002 |       0 |
  | Wascals        |   10003 |       1 |
  | Yosemite Place |   10004 |       1 |
  | E Fudd         |   10005 |       1 |
  +----------------+---------+---------+
  ```

#### 十六、组合查询

MySQL也允许执行多个查询（多条SELECT语句），并将结果作为单个查询结果集返回。这些组合查询通常称为并或复合查询。

有两种基本情况，其中需要使用组合查询：
 在单个查询中从不同的表返回类似结构的数据；
 对单个表执行多个查询，按单个查询返回数据。