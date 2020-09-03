package dataframe;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class DataFrameRDDReflection {


    public static void main(String[] args) {

         //1. 设置文件路径
        String inputPath = "file:///home/willhope/app/data/infos.txt";

        //2. 设置SparkSession，master
        SparkSession sparkSession = SparkSession.builder().master("local[2]").appName("DataFrameRDDReflection").getOrCreate();

        //3. 读取文件，生成RDD
        JavaRDD<String> javaRDD = sparkSession.sparkContext().textFile(inputPath,1).toJavaRDD();

        //4. 处理RDD，获取各个字段，并将其返回成对象RDD
        JavaRDD<Person> personJavaRDD = javaRDD.map(line -> {
            String[] infos = line.split(",");
            Integer id = Integer.parseInt(infos[0]);
            String name  = infos[1];
            Integer age = Integer.parseInt(infos[2]);
            return new Person(id ,name, age);
        });

        //5. 将RDD转换为Dataframe
        Dataset<Row> personDF = sparkSession.createDataFrame(personJavaRDD,Person.class);

        //6. Dataframe操作
        personDF.show();
        personDF.printSchema();
        personDF.filter(personDF.col("age").$greater(20)).show();

        //创建临时表，进行sql操作
//        personDF.createOrReplaceGlobalTempView("t_person");
//        sparkSession.sql("select * from t_person order by age desc").show();

        //关闭资源
        sparkSession.stop();
    }


}
