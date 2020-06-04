#### 一、查找最晚入职员工的所有信息

表结构信息：

```sql
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
```

输出描述:

|emp_no  |birth_date  |first_name  |last_name   |gender   |hire_date  |
|--------|--------|--------|--------|--------|--------|--------|
|10008   |1958-02-19|Saniya|Kalloufi|M|1994-09-15|

解题：

```sql

法一：20ms，3312k，将入职时间按照降序排列，然后返回第一条即可
select * from employees order by hire_date desc limit 1;

法二：18ms,3280k，使用条件嵌套查询，选取最大的时间
SELECT * FROM employees WHERE hire_date = (SELECT MAX(hire_date) FROM employees);
```

#### 二、查找入职员工时间排名倒数第三的员工所有信息

表结构信息：

```sql
CREATE TABLE `employees` (
`emp_no` int(11) NOT NULL,
`birth_date` date NOT NULL,
`first_name` varchar(14) NOT NULL,
`last_name` varchar(16) NOT NULL,
`gender` char(1) NOT NULL,
`hire_date` date NOT NULL,
PRIMARY KEY (`emp_no`));
```

输出描述:

|emp_no  |birth_date  |first_name  |last_name   |gender   |hire_date  |
|--------|--------|--------|--------|--------|--------|--------|
|10005   |1955-01-21|Kyoichi|Maliniak|M|1989-09-12|

解题：

```sql

法一：23ms，4672k，将入职时间按照降序排列，然后返回从第二行开始后的一行
select * from employees order by hire_date desc limit 2,1;

法二：17ms,3300k
select * from employees where hire_date = (select hire_date from employees group by hire_date order by hire_date desc limit 2,1);
```