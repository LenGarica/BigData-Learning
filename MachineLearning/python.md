` 目录 `:

- [一、 Deepin下Jyputer与Pycharm连接使用](#一-deepin下jyputer与pycharm连接使用)
- [二、 NumPy中的数据结构](#二-numpy中的数据结构)
    - [2.1 数组Ndarray](#21-数组ndarray)
        - [1. 初识](#1-初识)
        - [2. 数组Ndarray的查询操作](#2-数组ndarray的查询操作)
        - [3. 数组Ndarray的增加操作](#3-数组ndarray的增加操作)
        - [4. 数组Ndarray的删除操作](#4-数组ndarray的删除操作)
        - [5. 数组Ndarray的修改操作](#5-数组ndarray的修改操作)
        - [6. 数组Ndarray转换成其他的数据结构](#6-数组ndarray转换成其他的数据结构)
    - [2.2 矩阵Matrix](#22-矩阵matrix)
        - [1. 初识](#1-初识-1)
        - [2. 矩阵查询操作](#2-矩阵查询操作)
        - [3. 矩阵增加操作](#3-矩阵增加操作)
        - [4. 矩阵删除操作](#4-矩阵删除操作)
        - [5. 矩阵特殊操作](#5-矩阵特殊操作)
- [三、 Pandas的数据结构](#三-pandas的数据结构)
    - [3.1 序列Series](#31-序列series)
        - [1. 创建Series](#1-创建series)
        - [2. Series查询操作](#2-series查询操作)
        - [3. Series增添操作](#3-series增添操作)
        - [4. Series删除操作](#4-series删除操作)
        - [5. Series更改操作](#5-series更改操作)
        - [6. Series特殊操作](#6-series特殊操作)
    - [3.2 DataFrame](#32-dataframe)
        - [1. 初识](#1-初识-2)
        - [2. 查询](#2-查询)
        - [3. 增加操作](#3-增加操作)
        - [4. 删除操作](#4-删除操作)
        - [5. 修改操作](#5-修改操作)


## 一、 Deepin下Jyputer与Pycharm连接使用

1. 首先在Pycharm中创建一个ML的项目，将其python环境设置为anaconda的python环境。

2. 其次在anaconda/etc/jupyter目录中更改jupyter_notebook_config.json，如果后面不想使用固定的工程，则可以删除下面的内容，只需要保留{}即可。

```json
{
        "NotebookApp":{
                "nbserver_extensions":{
                        "jupyterlab":true
                },
                "notebook_dir":"JetBrain/DevFile/ML"
        }
}

```

3. 在终端中，根目录下输入 : `jupyter notebook`即可进入到web端。会发现，我们在web中进入了与pycharm一样的工程中。

## 二、 NumPy中的数据结构

### 2.1 数组Ndarray

#### 1. 初识
```py

# 首先引入numpy包，为了方便调用，起个别名np
import numpy as np

# 使用array()将列表转换为数组
a1 = np.array([1,2,3,4,5,6,7,8,9,10,11,12])

# 使用arange()产生一个从0-n-1的数组
a2 = np.arange(5)

# 使用ones()创建全为1的数组
a3 = np.ones((2,2))

# 使用ones()创建全为1的数组
a4 = np.empty((2,2))

# 使用random()创建全为4行2列的随机小数数组
a5 = np.random.rand(4,2)

# 使用linspace()创建区域跳跃的数组
a6 = np.linspace(10,30,5)
print('\n数组1\n',a1,'\n数组2\n',a2,'\n数组3\n',a3,'\n数组4\n',a4,'\n数组5\n',a5,'\n数组6\n',a6)
# 运行结果
数组1
[ 1  2  3  4  5  6  7  8  9 10 11 12] 
数组2
[0 1 2 3 4] 
数组3
[[1. 1.]
[1. 1.]] 
数组4
[[1. 1.]
[1. 1.]] 
数组5
[[0.46890625 0.48249348]
[0.79719388 0.61681122]
[0.86763925 0.98439419]
[0.08920272 0.88893181]] 
数组6
[10. 15. 20. 25. 30.]

```

#### 2. 数组Ndarray的查询操作

```py

# 将列表转换为2维数组，并且取值。取值规则，左开右闭。两组取值时，前者为行数，后者为列数，同样遵循左开右闭。
arr01 = np.array([[1,2,3,4],[5,6,7,8],[9,10,11,12]])
print(arr01[1:3,2:4])
# 运行结果
[[ 7  8]
[11 12]]


# 对a1数组结构进行重新构造，构造成3行，任意列
arr02 = a1.reshape(3,-1)
print(arr02)
# 查看arr02数组的维度
print(arr02.shape)
# 查看arr02数组的数据类型
print(arr02.dtype)
# 查看arr02数组的个数
print(arr02.size)
# 查看arr02数组的数据类型字节数
print(arr02.itemsize)
# 查看arr02数组的维度
print(arr02.ndim)
# 运行结果
[[ 1  2  3  4]
[ 5  6  7  8]
[ 9 10 11 12]]
(3, 4)
int64
12
8
2

```

#### 3. 数组Ndarray的增加操作

```py
# 对arr02数组的列添加元素，axis=1表示添加列
arr03 = np.append(arr02,[[1],[2],[3]],axis=1)
print(arr03)

# 结果
[[ 1  2  3  4  1]
 [ 5  6  7  8  2]
 [ 9 10 11 12  3]]

```

#### 4. 数组Ndarray的删除操作

```py

# 数组的转置操作
arr04 = arr03.T
print(arr04)
# 结果
[[ 1  5  9]
 [ 2  6 10]
 [ 3  7 11]
 [ 4  8 12]
 [ 1  2  3]]


# 删除arr04中的第二行，axis=0表示水平删除
print(np.delete(arr04,1,axis=0))
# 结果
[[ 1  5  9]
 [ 3  7 11]
 [ 4  8 12]
 [ 1  2  3]]
# 删除arr04中的第二行和第四行，0表示水平删除
print(np.delete(arr04,(1,3),0))
# 结果
[[ 1  5  9]
 [ 3  7 11]
 [ 1  2  3]]
# 删除arr04中的第二列，axis=1表示列删除
print(np.delete(arr04,1,1))
# 结果
[[ 1  9]
 [ 2 10]
 [ 3 11]
 [ 4 12]
 [ 1  3]]

```

#### 5. 数组Ndarray的修改操作

```py
# 将数组中第二行重置为0，等同于arr02[1] = 0
arr02[1:2] = 0

# 更改某一个具体位置
arr02[1][1] = 100

# 矩阵堆叠 
arr001 = np.array([1,2,3])
arr002 = np.array([4,5,6])
# 两个数组纵向堆叠
print(np.vstack((arr001,arr002)))
# 输出结果
[[1 2 3]
 [4 5 6]]
# 两个数组的横向堆叠
print(np.hstack((arr001,arr002)))
# 输出结果
[1 2 3 4 5 6]

```

#### 6. 数组Ndarray转换成其他的数据结构

```py

arr003 = np.array([[1,2,3],[4,5,6]])
# 转换成dataframe格式，dataframe格式可以将数组的schema输出
import pandas as pd
dfarray = pd.DataFrame(arr003)
print(dfarray)
# 仅仅输出Dataframe的数字
print(dfarray.values)
```

### 2.2 矩阵Matrix

#### 1. 初识

```py
# 将三个数组组合成矩阵
arr1 = [1,2,3]
arr2 = [6,7,8]
arr3 = [11,12,13]
matrix = np.mat([arr1,arr2,arr3])
print(matrix)
# 输出
[[ 1  2  3]
 [ 6  7  8]
 [11 12 13]]


# 创建3*3的矩阵
matrix2 = np.empty((3,3)) 
# 结果，矩阵中的数是随机的
[[4.64138230e-310 6.91287326e-310 0.00000000e+000]
 [0.00000000e+000 3.95252517e-322 3.95252517e-322]
 [4.64138230e-310 6.91288816e-310 4.64138090e-310]]

```

#### 2. 矩阵查询操作

```py
# 查看矩阵的每个维度的大小
print(matrix2.shape)
# 查看矩阵所有数据的个数
print(matrix2.size)
# 查看矩阵每个数据的类型
print(matrix2.dtype)

```

#### 3. 矩阵增加操作

```py

mat1 = np.mat([[1,2],[3,4]])
mat2 = np.mat([4,5])

# 将mat2矩阵添加到原矩阵的右侧
mat3_r = np.c_[mat1,mat2.T]
print(mat3_r)
# 结果
[[1 2 4]
 [3 4 5]]

# 将mat2矩阵添加到原矩阵的左侧
mat3_l = np.c_[mat2.T,mat1]
print(mat3_l)
# 结果
[[4 1 2]
 [5 3 4]]

# 在原矩阵上方连接矩阵
mat3_u = np.r_[np.mat([arr001]),matrix]
print(mat3_u)
# 结果
[[ 1  2  3]
 [ 1  2  3]
 [ 6  7  8]
 [11 12 13]]

```

#### 4. 矩阵删除操作

```py
# 删除第一行后的结果
matrix2 = np.delete(matrix,1,axis = 1)
print(matrix2)

# 删除第一列后的结果
matrix3 = np.delete(matrix,1,axis = 0)
print(matrix3)

```

#### 5. 矩阵特殊操作

```py

# 两个矩阵相乘
matrix4 = np.mat([[5,6],[7,8]])
matrix5 = mat1 * matrix4
print(matrix5)

# 两个矩阵点乘
matrix6 = mat1.dot(matrix4)
print(matrix6)

# 矩阵转置
matrix7 = matrix6.T

# 矩阵求逆
matrix8 = np.linalg.inv(mat1)

# 矩阵的特征值
matrix9 = np.linalg.eig(mat1)

```

## 三、 Pandas的数据结构

### 3.1 序列Series

#### 1. 创建Series

```py

import pandas as pd

sdata = {'a':3500,'b':4500,'c':5500,'d':6500}
# 将字典生成序列对象
s0 = pd.Series(sdata)
print(s0)
# 显示该结构的数据类型
print(type(s0))
# 结果
a    3500
b    4500
c    5500
d    6500
dtype: int64
<class 'pandas.core.series.Series'>


# 将列表生成序列对象
s1 = pd.Series([6,1,3,9])
print(s1)
# 结果
0    6
1    1
2    3
3    9
dtype: int64

# 对s1添加对应的索引
s2 = pd.Series([6,1,3,9],index=['a','b','c','d'])
print(s2)
# 结果
a    6
b    1
c    3
d    9
dtype: int64

```

#### 2. Series查询操作

```py
# 输出序列的值
print(s1.values)
# 结果
[6 1 3 9]


# 输出序列索引
print(s1.index)
# 结果
RangeIndex(start=0, stop=4, step=1)

# 按序列下标查找
print(s1[2])

# 按序列索引值查找
print(s1['a'])

# 按下标批量查找
print(s1[:2])

# 按照索引值批量查找元素
print(s1[['a','b']])
```


#### 3. Series增添操作

```py

s2 = s1.append(pd.Series([12],index=['e']))
s2

```

#### 4. Series删除操作

```py

s3 = s1.drop('a')

```

#### 5. Series更改操作


```py

s1['a'] = 4

```
#### 6. Series特殊操作

```py
# 按值排序
s1.sort_values()

# 求中位数
s1.median()

# 求大于中位数的值
print(s1[s1 > s1.median()])

# 两个序列相加
s1 + s2

# 时间序列
s4  = pd.Series([100,150,200])
idx = pd.date_range(start='2019-9',freq='M',periods=3)
s4.index = idx
print(s4)
# 结果
2019-09-30    100
2019-10-31    150
2019-11-30    200
Freq: M, dtype: int64

# Series转换为Dataframe
dfSeries = s2.to_frame()
print(dfSeries)
print(type(dfSeries))
# 结果
   0
a  6
b  1
c  3
d  9
<class 'pandas.core.frame.DataFrame'>

# Series转换为字典
dictSeries = s2.to_dict()
print(dictSeries)
print(type(dictSeries))

```

### 3.2 DataFrame

#### 1. 初识

```py

# 将字典转为Dataframe
dict1 = {'name':['lily','cindy','petter'],'no':['001','002','003','004'],'age':[16,16,15,16],'gender':['m','f','f','m']}
df1 = pd.DataFrame(dict1)
print(type(df1))

df1.index.name='id'
df1.to_csv('students.csv')
df1.to_excel('students.xls')
df1
```

#### 2. 查询

```py

column = df1.no
row = df1.loc[3]
print(cloumn,row)

```
#### 3. 增加操作

```py

df2 = df1.append([{'name':'Stack','no':'605','age':'15','gender':'m'}],ignore_index=True)
df2['new_Col'] = [1,2,3,4,5]
```
#### 4. 删除操作

```py

df3 = df1.copy()

df3b = df3.drop(['name'],axis=1)

df3c = df3.drop([2])
```
#### 5. 修改操作

```py

df4 = pd.DataFrame({'address':['school','home','school','school','home']})
# 合并操作，增加一列，如果要使用水平拼接，则设置axis=0
df5 = pd.contact([df2,df4],axis=1)

```

