package sparksqlpreliminary;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class SQLConetextJ {

    public static void main(String[] args) {

        //1. 设置文件路径
        String path = "/home/willhope/app/data/people.json";

        //2. 设置sparkconf
        SparkConf sparkConf = new SparkConf().setAppName("SQLConetextJ").setMaster("local[2]");

        //3. 设置spark环境，加载sparkConf
        SparkContext sparkContext = new SparkContext(sparkConf);

        //4. 设置sparksql
        SQLContext sqlContext = new SQLContext(sparkContext);

        //5. 相关处理，按行获取文件内容
        Dataset<Row> people = sqlContext.read().format("json").load(path);
        people.printSchema();
        people.show();

        //6. 关闭资源
        sparkContext.stop();

    }

}
