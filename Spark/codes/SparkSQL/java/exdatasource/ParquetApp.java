package exdatasource;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class ParquetApp {


    public static void main(String[] args) {

        //1. 设置路径
        String inputPath = "file:///home/willhope/app/data/users.parquet";
        String outputPath = "file:///home/willhope/app/data/userjsonout";

        //2. 获取SparkSession
        SparkSession sparkSession = SparkSession.builder().master("local[2]").appName("ParquetApp").getOrCreate();

        //3. 相关处理
        Dataset<Row> users = sparkSession.read().parquet(inputPath);
        users.printSchema();
        users.show();
        users.select("name", "favorite_color").show();

        //4. 导出数据为json格式
        users.select("name", "favorite_color").write().json(outputPath);

        // 关闭资源
        sparkSession.stop();
    }
}
