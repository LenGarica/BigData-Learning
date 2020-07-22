package dataframe;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class DataFrameJ {

    public static void main(String[] args) {


        String inputPath = "/home/willhope/app/data/people.json";

        // 1. SparkSession设置主类，master
        SparkSession sparkSession = SparkSession.builder().master("local[2]").appName("DataFrameJ").getOrCreate();

        // 2. 读取文件
        Dataset<Row> peopleDs = sparkSession.read().format("json").load(inputPath);

        // 3. 相关操作
        //输出dataframe对应的schema信息
        peopleDs.printSchema();

        //显示记录，括号里面可以填写想要输出的行数
        peopleDs.show();

        //查询name列的数据：select name from table
        peopleDs.select("name").show();

        //查询某几列所有数据，并对列进行计算：select name,age + 10 as age2 from table;
        //注意Java中不能写+10，写成plus()，在scala中+是方法
        peopleDs.select(peopleDs.col("name"), (peopleDs.col("age").plus(10)).as("age2")).show();

        //过滤年龄大于19岁的人
        //注意Java中不能写>19，写成$greater()，在scala中>是方法
        peopleDs.filter(peopleDs.col("age").$greater(19)).show();

        //根据某一列分组，然后再聚合操作select age,count(1) from table group by age
        peopleDs.groupBy("age").count().show();

        sparkSession.stop();

    }

}
