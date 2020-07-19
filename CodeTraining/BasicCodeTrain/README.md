## 目录
 - [code1：无重复数字的三位数](#code1)
 - [code2：完全平方数](#code2)
 - [code3：与运算判断奇偶数](#code3)
 - [code4：三边计算三角形的面积](#code4)
 - [code5：判断这一天是这一年的第几天](#code5)
 - [code6：9*9乘法口诀](#code6)
 - [code7：国际象棋棋盘](#code7)
 - [code8：斐波那契数列第40个数（兔子生孩子）](#code8)
 - [code9：输入一个大于3的数，判断是否是素数](#code9)
 - [code10：输出100~200之间的素数](#code10)
 - [code11：水仙花数](#code11)
 - [code12：分解质因数](#code12)
 - [code13：最大公约数和最小公倍数](#code13)
 - [code14：统计输入的字符数](#code14)
 - [code15：求 s=a+aa+aaa+aaaa+aa...a 的值](#code15)
 - [code16：1！+2！+3！+4！+...+20！](#code16)
 - [code17：多项求和](#code17)
 - [code18：完数](#code18)
 - [code19：计算2/1+3/2+5/3+8/5+13/8+...](#code19)
 - [code20：一个球从100m高度下落反弹高度](#code20)
 - [code21：猴子吃桃问题](#code21)
 - [code22：约瑟夫环问题](#code22)
 - [code23：计算num各位数字之和](#code23)
 - [code24：c在字符串s中出现的此数](#code24)
 - [code25：三天打鱼两天晒网](#code25)
 - [code26：实现在字符串str1的r位置上插入字符串str2](#code26)
 - [code27：输入n个数，将这个n个数的每位正整数求和放进数组a中](#code27)
 - [code28：输入一个10进制数，将其转换为2进制数，并存放在32位的数组中](#code28)
 - [code29:百钱买百鸡](#code29) 
 - [code30:反转字符串](#code30) 
 - [code31:输入一段英文，将每个英文词语的首字母大写](#code31) 
 - [code32:逆序数，输入一个数字，将其逆序输出](#code32) 
 - [code33:将数组逆序存放](#code33) 
 - [code34:判断是否为回文字符串](#code34) 
 - [code35:输入一个字符串，将其小写更改为大写后，存放到文件中](#code35) 
 - [code36:从磁盘上读入两个文件A和B，将两个文件合并为C文件，并将所有的字符进行排序存入](#code36) 
 - [code37:输入学生信息，存放到磁盘上](#code37) 
 - [code38:翻转字符串里的单词](#code38) 
 - [code39:３n+1问题](#code39) 
 - [code40:寻找小于Ｎ的最大素数](#code40) 
 - [code41:完美立方](#code41) 
 - [code42:最大质因数](#code42) 
 - [code43:最大回文积](#code43) 
 - [code44:称硬币](#code44) 
 - [code45:能被1-20所有数整除的最小数](#code45) 
 - [code46:第10001个素数](#code46) 
 - [code46:毕达哥拉斯三元组](#code47) 


##### code1：
题目： 有 1、 2、 3、 4 个数字， 能组成多少个互不相同且无重复数字的三位数？ 都是多少？

c语言
```c
void noRepeatNum(){
    int count = 0 ;
    for (int i = 1; i < 5; ++i) {
        for (int j = 1; j < 5; ++j) {
            for (int k = 1; k < 5; ++k) {
                if(i!=j&&i!=k&&j!=k){
                    ++count;
                    printf("%d%d%d\n",i,j,k);
                }
            }
        }
    }
    printf("一共：%d个数",count);
}
```

java语言
```Java
public static void noRepeatNum(){
    int c;
    int count = 0 ;
    for (int i = 1; i < 5; i++) {
        for (int j = 1; j < 5; j++) {
            for (int k = 1; k < 5; k++) {
                if (i!=j&&i!=k&&j!=k) {
                    count++;
                    c = i * 100 + j * 10 + k;
                    System.out.println(c);
                }
            }
        }
    }
    System.out.println(count);
}
```

js语言
```js
const arr = [1, 2, 3, 4];
let resultArr = [];
for (let i = 0; i < arr.length; i++) {
    for (let j = 0; j < arr.length; j++) {
        for (let k = 0; k < arr.length; k++) {
            if (i !== j && j !== k && i !== k) {
                let ele = ''+arr[i] + arr[j] + arr[k]; 
                resultArr.push(ele);
            }
        }
    }
}
console.log(`输出结果：${resultArr}`, `一共：${resultArr.length}个`);
```

```scala
  def noRepeatNum(): Unit ={
    var count = 0
    for (i<- 1 to 5){
      for (j <- 1 to 5){
        for (k <- 1 to 5 ){
          if(i!=j && i!= k && j!= k){
            count = count + 1
            printf("%d%d%d\n",i,j,k)
          }
        }
      }
    }
  }

```


##### code2：
题目： 一个整数， 它加上 100 后是一个完全平方数， 再加上 168 又是一个完全平方数， 请问该数是多少？

c语言
```c
void completeSquare(){
    long int i , a, b, x , y;
    for (i = 0; i <= 100000; ++i) {
        a = i + 100;
        b = i + 268;
        x = sqrt(a);
        y = sqrt(b);
        if(x*x == a&& y*y == b){
            printf("%ld\n",i);
        }
    }
}
```

java语言
```Java
public static void completeSquare(){
    int i , a, b, x, y;
    System.out.println("符合要求的数为：");
    for (i = 0; i <= 100000; ++i) {
        a = i + 100;
        b = i + 268;
        x = (int) Math.sqrt(a);
        y = (int) Math.sqrt(b);
        if(x*x == a&& y*y == b){
            System.out.println(i);
        }
    }
}
```

```scala
 def completeSquare(): Unit ={
    for (i <- 0 to 100000){
      var a = i + 100
      var b = i + 268
      var x = (Math.sqrt(a)).toInt
      var y = (Math.sqrt(b)).toInt
      if(x*x == a && y*y == b){
        println(i+"\n")
      }
    }
  }

```

##### code3：
题目：输入一个数，使用与运算判断奇偶数

c语言
```c
void oddEven(int n) {
    if ((n & 1) == 0) {
        printf("这个数是偶数");
    } else {
        printf("这个数是奇数");
    }
}

```

java语言
```Java
public static void oddEven(int n){
    if((n & 1) == 0){ 
        System.out.println("这个数是偶数");
    } else{
        System.out.println("这个数是奇数");
    }
}
```

```scala
  def oddEven(): Unit ={
    var num = StdIn.readInt()
    if((num & 1)==0){
      println("偶数")
    }else{
      println("奇数")
    }
  }

```

##### code4：
题目：输入三角形的三个边长（默认为合法），由三角形的三个边算出三角形的面积

c语言
```c
void angleArea(double a , double b , double  c){
    double s = 0.5 * (a+b+c);
    double area = sqrt(s*(s-a)*(s-b)*(s-c));
    printf("%.2f",area);
}
```

java语言
```Java
public static void angleArea(double a , double b , double c){
    double s = 0.5*(a+b+c);
    double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
    System.out.println(area);
}   
```

scala语言
```scala
  def angleArea(a:Double,b:Double,c:Double): Unit ={
    var s = 0.5*(a+b+c)
    var area = Math.sqrt(s*(s-a)*(s-b)*(s-c))
    println(area.formatted("%.2f"))
  }
```


##### code5:
题目：输入某年某月某日， 判断这一天是这一年的第几天？

c语言
```c
void code5(int year, int month, int day) {
    int countDay;
    int a[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    //闰年的判断
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
        a[1] = 29;
    }
    for (int i = 0; i < month - 1; i++) {
        countDay += a[i];
    }
    countDay = countDay + day;
    printf("%d", countDay);
}
```

c语言使用结构体
```c
struct Data{
    int year;
    int month;
    int day;
}date;
void code5(int year, int month, int day) {
    int countDay;
    int a[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    //闰年的判断
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
        a[1] = 29;
    }
    for (int i = 0; i < month - 1; i++) {
        countDay += a[i];
    }
    countDay = countDay + day;
    printf("%d", countDay);
}
```

```scala
  def jdugeDay(year:Int,month:Int,day:Int): Unit ={
    //    var monthday = new Array[Int](12)
    var monthday = Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    if((year %4 ==0 && year%100!=0)||(year % 400 ==0)){
      monthday(1) = 29
    }
    var countDay = 0
    for(i <- 0 until (month - 1)){
      countDay = countDay + monthday(i)
    }
    countDay = countDay + day
    printf("%d",countDay)
  }
```

##### code6:
题目：输出9*9乘法口诀

c语言
```c
void code6(){
    for (int i = 1; i < 10; ++i) {
        for (int j = 1; j <= i; ++j) {
            printf("%d*%d=%-3d",i,j,i*j);
        }
        printf("\n");
    }
}
```

java语言
```Java
public static void code6(){
    for (int i = 1; i < 10; ++i) {
        for (int j = 1; j <= i; ++j) {
            System.out.println("i"+i+"j"+j+"="+(i*j));
        }
        printf("\n");
    }
}   
```


##### code7:
题目：输出国际象棋棋盘： 8*8 黑白格相间。

c语言
```c
void code7(){
    int a;
    for (int i = 0; i < 8; ++i) {
        for (int j = 0; j < 8; ++j) {
            a = i + j;
            if((a & 1 )== 0){
                printf("白");
            }else{
                printf("黑");
            }
        }
        printf("\n");
    }
}
```


##### code8:
题目：输出斐波那契数列第40个数

c语言
```c
int code8(int num) {
    if(num == 1 || num== 2) {
        return 1;
    }
    return code8(num - 1) + code8(num - 2);
}

```

##### code9:
题目：输入一个大于3的数，判断是否是素数

c语言
```c
void code9() {
    int n, i, k;
    scanf("%d", &n);
    while (n < 3) {
        printf("please re-enter");
        scanf("%d", &n);
    }
    k = sqrt(n);
    for (i = 2; i <= k; i++) {
        if (n % i == 0) {
            printf("此数不是素数");
            return;
        }
    }
    printf("此数是素数");
}

```

##### code10:
题目：输入100~200里面的素数

c语言
```c
void code10(){
    int n , i ,k;
    for(n = 101 ; n <= 200 ; n=n+2){
        k = sqrt(n);
        for (i = 2; i <= k ; ++i) {
            if(n % i == 0) break;
        }
        if(i >= k+1){
            printf("%d\t",n);
        }
    }
}
```

##### code11:
题目：打印出所有的水仙花数。所谓“水仙花数” 是指一个三位数， 其各位数字立方和等于该数本身。 例如： 153 是一个“水仙花数” ， 因为 153=1 的三次方＋5 的三次方＋3 的三次方。

c语言
```c
void code11(){
    int a , indiv , ten , hundred;
    for (int i = 100; i < 1000; ++i) {
        hundred = i / 100;
        ten = i / 10 % 10;
        indiv = i % 10;
        a = pow(hundred,3)+pow(ten,3)+pow(indiv,3);
        if(i == a){
            printf("%d\n",i);
        }
    }

}
```

##### code12:
题目：分解质因数，输入 90,打印出 90=2 * 3 * 3 * 5 

c语言
```c
void code12(){
    int i , n ;
    printf("please input a number");
    scanf("%d",&n);
    for (i = 2; i <= n; ++i) {
        while (n != i) {
            if (n % i == 0) {
                printf("%d * ",  i);
                n = n / i ;
            } else {
                break;
            }
        }
    }
    printf("%d\n",n);
}
```

##### code13:
题目：求最大公约数和最小公倍数

c语言
```c
void code13(){
    int m , n , p , temp , r;
    scanf("%d,%d",&m,&n);
    if(m<n){
        temp = m ;
        m = n;
        n = temp;
    }
    p = m*n;
    while(n != 0){
        r = m % n ;
        m = n;
        n = r;
    }
    printf("最大公约数：%d\n",m);
    printf("最小公倍数：%d\n",p/m);
}
```

##### code14:
题目：统计输入的字符数，输入一行字符， 分别统计出其中英文字母、 空格、 数字和其它字符的个数。

c语言
```c
void code14(){
    int letters, spaces , digit , other;
    letters=spaces=digit=other=0;
    char c ;
    while ((c=getchar()) != '\n'){
        if((c>='A'&&c<='Z')||(c>='a'&&c<='z')){
            letters++;
        } else if(c == ' '){
            spaces++;
        } else if (c>='0'&&c<='9'){
            digit++;
        } else{
            other++;
        }
    }
    printf("字母=%d,空格=%d,数字=%d,其他=%d",letters,spaces,digit,other);
}
```

##### code15:
题目：求 s=a+aa+aaa+aaaa+aa...a 的值，例如 2+22+222+2222+22222(此时共有 5 个数相加)， 用户可以输入 a 和项数 n。

c语言
```c
void code15(int a , int n){
    int sn , tn ;
    sn = tn = 0;
    for (int i = 1; i <= n; ++i) {
        tn += a;
        sn += tn;
        a *= 10;
    }
    printf("%d",sn);
}
```

##### code16:
题目：计算1！+2！+3！+4！+...+20！

c语言
```c
void code16(int a , int n){
    int sn , tn ;
    sn = tn = 0;
    for (int i = 1; i <= n; ++i) {
        tn += a;
        sn += tn;
        a *= 10;
    }
    printf("%d",sn);
}
```

##### code17:
题目：计算 $ \sum_{i=1}^nk $ + $ \sum_{i=1}^nk^2 $ + $ \sum_{i=1}^n(1/k) $，其中第一个n为100，第二个n为50，第三个n为10 

c语言
```c
void code17(){
    int n1 = 100 , n2 = 50 , n3 = 10;
    double k , s1 = 0 , s2 = 0 , s3 = 0;
    for (k = 1; k <= n1; k++) {
        s1 += k;
    }
    for (k = 1; k <= n2; k++) {
        s2 += pow(k,2);
    }
    for (k = 1; k <= n3; k++) {
        s3 += (1/k);
    }
    printf("sum=%15.6f",s1+s2+s3);
}
```

##### code18:
题目：计算1000以内的完数，例如：6=1+2+3。完数：一个数恰好等于它的因子之和

c语言
```c
void code18(int a , int n){
    int sn , tn ;
    sn = tn = 0;
    for (int i = 1; i <= n; ++i) {
        tn += a;
        sn += tn;
        a *= 10;
    }
    printf("%d",sn);
}
```

##### code19:
题目：计算2/1+3/2+5/3+8/5+13/8+......一共20项。

c语言
```c
void code19(){
    double deno = 1 , mole = 2 , sum = 0 , t;
    for (int i = 1; i <= 20; ++i) {
        sum = sum + mole / deno ;
        t = mole ;
        mole = deno + mole;
        deno = t;
    }
    printf("sum = % 16.10f\n",sum);
}
```  

##### code20:
题目：一个球从100m高度下落，每次落地后反跳回原来的一半，再下落，在反弹，求在第10次落地时，共经过多少米，第10次反弹多高。

c语言
```c
void code20(){
    double sn = 100 , hn = sn / 2;
    for (int i = 2; i <= 10; ++i) {
        sn = sn + 2 * hn;
        hn = hn / 2;
    }
}
```  

##### code21:
题目：猴子吃桃问题。有一堆桃子，猴子第一天吃一半多一个，第二天吃剩下一半多一个，以此类推，第10天时，只剩下一个，求第一天时，有多少桃子？

c语言
```c
void code21(){
    int day = 9;
    int a , b = 1;
    while(day > 0){
        a = (b+1)*2;
        b = a;
        day--;
    }
    printf("total = %d ",a);
}
```  

##### code22:
题目：约瑟夫环问题，本题默认为以3开始报数

c语言
```c
void code22(){
    int i , k , m , n , num[50] , *p;
    scanf("%d",&n);
    p = num;
    for (i = 0; i < n; i++) {
        *(p+i) = i+1;
    }
    i = k = m = 0;
    while(m < n-1){
        if(*(p+i)!=0) k++;
        if(k == 3){
            *(p+i) = 0;
            k = 0;
            m++;
        }
        i++;
        if(i == n) i = 0;
    }

    while(*p == 0) p++;
    printf("%d",*p);
}


方法二：
/*
 * n为总人数，m为报数
 */
int f(int n , int m){
    return n == 1 ? n : ((f(n-1,m)+m-1)%n+1);
}

```  

Java语言
```java
/**
 *
 * @param personnum 总人数
 * @param n 报数
 */
public static void Josephus(int personnum , int n){
    Queue<Integer> person = new LinkedList<Integer>();
    //给每个人顺序编号
    for (int i = 0 ; i < personnum ; i++){
        person.add(i+1);
    }

    int counts = 0 ; //充当计数器
    while (!person.isEmpty()){
        Integer per = person.poll();
        counts++;
        if(counts % n == 0){
            System.out.println(per);
        }else{
            person.add(per);
        }
    }
}
```

##### code23:
题目：计算num各位数字之和

c语言
```c
void code23(){
    int n,s=0;
    scanf("%d",&n);
    do{
        s += (n%10);
        n /= 10;
    }while(n!=0);
    printf("%d",s);
}
```  

##### code24:
题目：测试字符c在字符串s中出现的此数

c语言
```c
void code24(){
    char st[20],c;
    int count = 0;
    gets(st);
    scanf("%c",&c);
    for (int i = 0; st[i]!='\0'; ++i) {
        if(st[i]==c){
            count ++;
        }
    }
    printf("%c在%s中出现的此数为%d\n",c,st,count);
}
```  

##### code25:
题目：三天打鱼两天晒网。某人从1990年1月1日起开始三天打鱼两天晒网，问这个人在某年某月某日是打鱼还是晒网？

c语言
```c
void code25(int year, int month, int day) {
    int countDay = 0;
    int a[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    //闰年的判断
    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
        a[1] = 29;
    }
    for (int i = 0; i < month - 1; i++) {
        countDay += a[i];
    }
    countDay = countDay + day;
    if (countDay % 5 == 1 || countDay % 5 == 2 || countDay % 5 == 3) {
        printf("今天打鱼");
    } else if (countDay % 5 == 0 || countDay % 5 == 4) {
        printf("今天晒网");
    }
}
```  

##### code26:
题目：实现在字符串str1的r位置上插入字符串str2

c语言
```c
void code26(int n){
    char str1[100] , str2[50];
    gets(str1);
    gets(str2);
    int i , j , k ,l;
    k = strlen(str1);
    l = strlen(str2);

    for(i = k ; i>=(n-1) ; i--){
        str1[l+i] = str1[i];
    }
    for (j = 0; j < l; j++) {
        str1[i+1] = str2[j];
        i++;
    }
    puts(str1);
}

``` 

##### code27:
题目：输入n个数，将这个n个数的每位正整数求和放进数组a中

c语言
```c
//输入n个数，将这个n个数的每位正整数求和放进数组a中
void code27(){
    int n , num ,a[100];
    int sum(int *p);
    scanf("%d",&n);
    for (int i = 0; i < n; i++) {
        scanf("%d",&num);
        a[i] = sum(num);
    }
}
int sum(int *p){
    int sum = 0;
    do{
        sum += (*p % 10);
        *p /= 10;
    }while (*p!=0);
    return sum;
}
``` 

##### code28:
题目：输入一个10进制数，将其转换为2进制数，并存放在32位的数组中

c语言
```c
//输入一个10进制数，将其转换为2进制数，并存放在32位的数组中
void code28(){
    int a[32] , n , i = 0;
    scanf("%d",&n);
    while(n!=0){
        a[i] = n % 2;
        i++;
        n /= 2;
    }
    for(;i>0;i--){
        printf("%d",a[i-1]);
    }
}
``` 

##### code29:
题目：百钱买百鸡。公鸡5元一个，母鸡3元一个，小鸡1元3个。问公鸡，母鸡，小鸡各有多少个？

c语言
```c
//百钱买百鸡
void code29() {
    int i , j , k;
    for (i = 1; i < 20; i++) {
        for (j = 1; j < 33; j++) {
            k = 100 - i - j;
            if(k%3==0&&5*i+3*j+k/3==100){
                printf("公鸡=%d只,母鸡=%d只,小鸡=%d只\n",i,j,k);
            }
        }
    }
}
``` 

Java语言
```java
    int i , j , k;
    for (i = 1; i < 20; i++) {
        for (j = 1; j < 33; j++) {
            k = 100 - i - j;
            if(k%3==0&&5*i+3*j+k/3==100){
                System.out.println("公鸡："+i+"只，母鸡："+j+"只，：小鸡："+k+"只");
            }
        }
    }
```

##### code30:
题目：反转字符串

c语言
```c
//反转字符串
void code30() {
    char a[20],temp;
    gets(a);
    int n = strlen(a);
    for(int i = 0; i < n/2; i++){
        temp = *(a+i);
        *(a+i) = *(a+n-i-1);
        *(a+n-i-1) = temp;
    }
    puts(a);
}
```

##### code31:
题目：输入一段英文，将每个英文词语的首字母大写

c语言
```c
#include <stdio.h>

void count(char str[]);

int main() {

    char string[50];
    gets(string);
    count(string);
    puts(string);
    return 0;
}

void count(char str[]) {
    char c;
    int word = 0, index = -1;
    for (int i = 0; (c = str[i]) != '\0'; ++i) {
        if (c == ' ') {
            word = 0;
            index = i;
        } else if (word == 0) {
            word = 1;
            if (str[index + 1]>='a'&&str[index+1]<='z') {
                str[index + 1] -= 32;
            }
        }
    }
}

```

##### code32:
题目：逆序数，输入一个数，将其逆序输出

c语言
```c
#include <stdio.h>
int main() {
    int number;
    int i , k , sum = 0;
    scanf("%d",&number);
    do{
        i = number % 10;
        sum = sum * 10 + i;
        number /= 10;
    }while (number>0);
    printf("%d",sum);
    return 0;
}

```


##### code33:
题目：将数组逆序存放

c语言
```c
void code33(int a[] , int n){
    int i , temp;
    for (i = 0; i < n/2 ; i++) {
        temp = a[i];
        a[i] = a[n-i-1];
        a[n-i-1] = temp;
    }
}
 
```

##### code34:
题目：判断字符串是否为回文字符串

c语言
```c
void code34(char *p){
    char *start = p;
    char *end = p+strlen(p)-1;
    int flag = 1;
    while(start <= end && flag){
        flag = (*start == *end);
        start ++;
        end --;
    }
    return flag;
}
 
```

##### code35:
题目：从键盘中输入一个字符串，将其中的小写字母全部转换成大写字母，然后输出到一个test文件中保存。输入的字符以！结束。

c语言
```c

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/**
 * 从键盘中输入一个字符串，将其中的小写字母全部转换成大写字母，然后输出到一个test文件中保存。
 * 输入的字符以！结束。
 */
void code35(){
    FILE *fp;//定义文件指针
    if((fp=fopen("test","w"))==NULL){
        printf("cannot open this file");
        exit(0);
    }
    char str[N];//定义接收字符串的数组
    gets(str);
    int i = 0;
    while (str[i]!='!'){ //以感叹号结束
        if(str[i] >= 'a' && str[i] <= 'z'){
            str[i] -= 32;
        }
        fputc(str[i] , fp);//将字符str[i]，写入到fp指向的文件中去
        i++;
    }
    fclose(fp);

    //查看一下文件中的东西
    fp = fopen("test","r");
    fgets(str,strlen(str)+1,fp); //把fp指向的文件数据读出到str中，长度为n-1
    puts(str);
    fclose(fp);
}
```

##### code36:
题目：从键盘中输入一个字符串，将其中的小写字母全部转换成大写字母，然后输出到一个test文件中保存。输入的字符以！结束。

c语言
```c

#include <stdio.h>
#include <stdlib.h>

/**
 * 从磁盘上读入两个文件A和B，将两个文件合并为C文件，并将所有的字符进行排序存入。
 */
void code2(){
    char str[N] , ch;
    FILE *fp;
    if((fp = fopen("A","r"))==NULL){
        printf("cannot open this file");
        exit(0);
    }
    //将文件内容存入到字符数组中，同时展示一下文件中的内容
    int i ;
    for (i = 0; (ch = fgetc(fp))!=EOF ; ++i) {
        str[i] = ch;
        putchar(str[i]);
    }
    fclose(fp);

    printf("\n");

    if((fp = fopen("B","r"))==NULL){
        printf("cannnot open this file");
        exit(0);
    }
    //将文件内容存入到字符数组中，同时展示一下文件中的内容
    int j;
    for (j = i; (ch = fgetc(fp))!=EOF ; ++j) {
        str[j] = ch;
        putchar(str[j]);
    }
    fclose(fp);

    //冒泡排序，将文件中的字符进行排序
    char temp;
    int n = j;
    for (int k = 0; k < n; ++k) {
        for (int l = k+1; l < n; ++l) {
            if(str[k] > str[l]){
                temp = str[k];
                str[k] = str[l];
                str[l] = temp;
            }
        }
    }

    printf("\n");

    //将排好序的字符串，写入到文件C中
    fp = fopen("C","w");
    for (int m = 0; m < n; ++m) {
        fputc(str[m] , fp);
        putchar(str[m]);
    }
    fclose(fp);
}
```


##### code37:
题目：从键盘中输入5个学生的信息，每个学生有3门课程，信息包括学号，姓名，3门课程成绩，计算出平均成绩，将原有数据和计算出的平均值都存放在磁盘文件上。

c语言
```c

void code37(){
    FILE *fp;

    for (int i = 0; i < 5; ++i) {
        printf("第%d个学生的信息：\n",i+1);
        printf("学号：");
        scanf("%s",stu[i].num);
        printf("姓名：");
        scanf("%s",stu[i].name);
        printf("三科成绩：\n");
        float sum = 0;
        for (int j = 0; j < 3; ++j) {
            printf("第%d门成绩:",j+1);
            scanf("%f",&stu[i].score[j]);
            sum +=stu[i].score[j];
        }
        stu[i].ave = sum / 3.0;
    }

    if((fp=fopen("studFile","w"))==NULL){
        printf("cannot open this file");
        exit(0);
    }
    for (int k = 0; k < 5; ++k) {
        if(fwrite(&stu[k], sizeof(struct student),1,fp)!=1){
            printf("erro");
        }
    }
    fclose(fp);
    fp = fopen("studFile","r");
    for (int l = 0; l < 5; ++l) {
        fread(&stu[l], sizeof(struct student),1,fp);
        printf("\n%s,%s,%d,%d,%d,%6.2f\n",stu[l].num,stu[l].name,stu[l].score[0]
        ,stu[l].score[1],stu[l].score[2],stu[l].ave);
    }
}
```


##### code38:
题目：反转字符串中的单词

c语言
```c
char * reverseWords(char * s){
    int size = strlen(s);
    //申请新的空间
    char* new_s = (char*)malloc(sizeof(int) * (size + 1));
    //对新的空间进行初始化
    memset(new_s, 0, sizeof(int) * (size + 1));
    //i从后到前遍历s，j指向new_s新增字符
    int i = size - 1, j = 0;
    //遍历s过程中，rear指向一个word的最后一位，front指向同个word的第一位
    int front = 0, rear = 0;
    while (i >= 0) {
        if (s[i] != ' ') {
            //“前”一个字符是空格
            if (rear == 0) {
                //还没有单词入new_s时，不能插入空格，否则每隔一个单词插入一个空格
                if (new_s[0]) new_s[j++] = ' ';
                //rear赋值，暂时不变，指向word的最后一位
                rear = i;
            }
            //特殊情况：s前面没有空格，此时需单独回头遍历直至空格或结束
            if (i == 0) {
                while (i < size && s[i] != ' ') {
                    new_s[j++] = s[i++];
                }
                break;
            }
        }
        else {
            //此时rear还保留着，说明“前”一个字符非空格
            if (rear != 0) {
                front = i + 1;  //front赋值，指向word第一位
                while (front <= rear) {  //回头遍历到rear停止
                    new_s[j++] = s[front++];
                }
                rear = 0;  //rear置零
            }
        }
        i--;
    }
    return new_s;
}

```

Java语言
```java
public static String reverseWords(String s) {
    // 除去开头和末尾的空格字符
    s = s.trim();
    /**
        * public static <T> List<T> asList(T... a)返回由指定数组支持的固定大小的列表。
        * （将返回的列表更改为“写入数组”。）
        * 该方法作为基于数组和基于集合的API之间的桥梁，与Collection.toArray()相结合 。
        * 返回的列表是可序列化的，并实现RandomAccess 。
        * 此方法还提供了一种方便的方式来创建一个初始化为包含几个元素的固定大小的列表.
        *   List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
        */
    // 正则匹配连续的空格字符作为分隔符分割 \s+ 可以匹配多个空格
    List<String> wordList = Arrays.asList(s.split("\\s+"));
    //使用集合辅助类Collections将链表中的所有字符串位置进行反转
    Collections.reverse(wordList);
    return String.join(" ", wordList);
}
```

##### code39:
题目：3n + 1 问题，对于任意大于1的自然数n,若n为奇数,则将n变为3n+1,否则变为n的一半。经过若干次这样的变换,一定会使n变为1。例如,3→10→5→16→8→4→2→1。输入n,输出变换的次数。n≤10^9 。

```java

public static void code_39(){
    int count = 0;
    Scanner sc = new Scanner(System.in);
    long n = sc.nextLong();
    while(n > 1){
        if((n & 1) == 1){
            n = n * 3 + 1;
        }else{
            n = n / 2;
        }
        count ++;
    }
    System.out.println(count);
}

```

##### code40：
题目：寻找小于N的最大素数

 ```java

public int getMaxPrime(int N){

        if(N <= 3){
            if(N > 1){
                return N;
            }else{
                throw new IllegalArgumentException("没有最大素数");
            }
        }else{
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 3; i < N; i += 2){
                boolean z = isPrime(i);
                if(z){
                    arrayList.add(i);
                }
            }
            return Collections.max(arrayList);
        }

    }


    public boolean isPrime(int n) {

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

 ```

##### code41:
题目：形如a^3 = b^3 + c^3 + d^3的等式被称为完美立方等式。例如，12^3 = 6^3 + 8^3 + 10^3，编写一个程序，对任意给的一个正整数N（N<=100）,寻找所有的四元组（a，b，c，d），其中a，b，c，d大于1，小于等于N，且b<=c<=d。

```java

    public void perfectCube(int N){

        for (int a = 2; a <= N; a++){
            for(int b = 2; b < a; b++){
                for(int c = b; c < a; c++){
                    for (int d = c; d < a; d++){
                        if(a* a* a == b* b* b + c* c* c + d* d* d){
                            System.out.printf("Cube = %d, Triple = (%d,%d,%d)\n", a, b, c, d);
                        }
                    }
                }
            }
        }

    }
```

##### code42:
题目：13195的所有质因数为5、7、13和29。600851475143最大的质因数是多少？

```java

    public long primeFactors(long n){
        if(n == 1){
            return 1;
        }
        for (int i = 2; i < n; i++) {
            if(n % i == 0){
                return primeFactors(n / i);
            }
        }
        return n;
    }

```

##### code43:
题目：回文数就是从前往后和从后往前读都一样的数。由两个2位数相乘得到的最大回文乘积是 9009 = 91 × 99。找出由两个3位数相乘得到的最大回文乘积。

```java

    public int bigPalindromeNumber(){
        int bigPal = 0;
        for (int i = 100; i <= 999; i++) {
            for (int j = 100; j <= 999; j++) {
                int num = i * j;
                if(num > bigPal && isPalindromeNumber(num)){
                    bigPal = num;
                }
            }
        }
        return bigPal;
    }

    public boolean isPalindromeNumber(int num){

        int s = num;
        int y = 0;
        while (s > 0){
            y = y * 10 + s % 10;
            s = s / 10;
        }

        if(y == num){
            return true;
        }

        return false;

    }

```

##### code44:
题目：称硬币问题，有12枚硬币，其中11枚是真的，1枚是假的。假币和真币质量不同，但是不知道假币比真币轻还是重。现在，用一架天平，分别在左右两边放置四枚币，称三次，告诉你称的结果，请你找出假币并且确定假币是轻还是重。even表示平，up/down代表右边翘起来/降下去。

```
输入：
ABCD  EFGH  even
ABCI  EFJK  up
ABIJ  EFGH  even

输出：

K is the counterfeit coin and it is light.

```

解题思路：先对每一枚硬币假设是轻的，看这样是否符合称量结果，如果符合，问题解决。如果不符合，就假设它是中的，看是否符合称量结果。把所有硬币都试一遍，一定能找到特殊硬币。

```java

import java.util.Scanner;

public class Code_44 {

    private static final String[][] Left = null;

    public static boolean Isfake(String[][] Left, String c, boolean light) {
        for (int i = 1; i <= 3; i++) {
            String pleft = null;
            String pright = null;
            if (light == true) {
                pleft = Left[i][1];
                pright = Left[i][2];
            } else {
                pleft = Left[i][2];
                pright = Left[i][1];

            }
            switch (Left[i][3]) {
                case "up":
                    if (!pright.contains(c))
                        return false;
                    break;
                case "even":
                    if (pright.contains(c) || pleft.contains(c))
                        return false;
                    break;
                case "down":
                    if (!pleft.contains(c)) {
                        return false;
                    }
                    break;
            }

        }
        return true;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        while (N-- > 0) {
            String[][] Left = new String[4][4];
            for (int i = 1; i <= 3; i++)
                for (int j = 1; j <= 3; j++)
                    Left[i][j] = sc.next();
            for (char c = 'A'; c <= 'L'; c++) {
                String s = String.valueOf(c);
                if (Isfake(Left, s, true)) {
                    System.out.println(s + " is the counterfeit coin and it is light");
                    break;
                } else if (Isfake(Left, s, false)) {
                    System.out.println(s + " is the counterfeit coin and it is heavy");
                    break;
                }
            }

        }

    }

}


```


##### code45:
题目：2520是最小的能够被1-10整除的数，求最小的能被1-20整除的正数是多少？

本质：就是求1-20的最小公倍数，1到n的最小公倍数也是n*(n-1)的倍数。被1-10整除的最小数为2520，那么被1-20整除的最小数肯定比2520大。从2520开始逐步递增，乘上n*(n-1)，然后除以2->n，看是否能够都除尽，若除尽，则此数就是最小的数。

```java

public class Code_45 {


    public static int smallestMultiple(int num){

        int product = num * (num - 1);

        int m = 0;

        for(int i = 2520; i< Integer.MAX_VALUE; i++){
            m = product * i;
            for(int j = 2; j<= num; j++){
                if(m % j != 0){
                    break;
                }
                if(j == num){
                    return m;
                }
            }
        }

        return m;
    }


    public static void main(String[] args) {
        System.out.println(smallestMultiple(20));
    }

}


```

##### code46:
题目：求出第10001个素数是什么？

```java

public class Code_46 {

    public static int N_10001_prime(int num){
        int count = 0;
        for (int i = 2; i < Integer.MAX_VALUE; i++) {
            if(isPrime(i)){
                count++;
                if(count == num){
                    return i;
                }
            }
        }
        return -1; // 没有则返回-1
    }


    public static boolean isPrime(int x){

        if(x < 2){
            return false;
        }else if(x == 2){
            return true;
        }else if((x & 1) == 0){
            return false;
        }else{
            for(int i = 3 ; i< Math.sqrt(x); i++){
                if(x % i == 0){
                    return false;
                }
            }
            return true;
        }

    }


    public static void main(String[] args) {
        System.out.println(N_10001_prime(10001));
    }

}


```

##### code47:
题目：毕达哥拉斯三元组是三个自然数a < b < c组成的集合，并满足a^2 + b^2 = c2。例如，3^2 + 4^2 = 9 + 16 = 25 = 5^2。有且只有一个毕达哥拉斯三元组满足 a + b + c = 1000。求这个三元组的乘积abc。

```java

public class Code_47 {

    /**
     * 将三个数a，b，c看作是三角形的三个边，则a，b是直角边，c是斜边。
     * a不能等于b，因为a若等于b，则c边不是整数，a + b + c不满足=1000
      * @param maxnum
     * @return
     */

    public static int pythaGorean(int maxnum){
        for(int a = 1; a < maxnum / 3; a++){
            for (int b = a + 1; b < maxnum >> 1; b++){
                int c = maxnum - a - b;
                if((b < c) && (a * a + b * b == c * c)){
                    return a * b * c;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(pythaGorean(1000));
    }

}


```
