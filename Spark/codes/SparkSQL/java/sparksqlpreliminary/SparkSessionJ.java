package sparksqlpreliminary;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkSessionJ {


    public static void main(String[] args) {


        // 1. 设置文件路径
        String inputPath = "/home/willhope/app/data/people.json";

        // 2. 创建SparkSession
        SparkSession sparkSession = SparkSession.builder().master("local[2]").appName("SparkSessionJ").getOrCreate();


        // 3. 进行相关的处理
        Dataset<Row> people = sparkSession.read().json(inputPath);
        people.printSchema();
        people.show();

        sparkSession.stop();
    }



}
