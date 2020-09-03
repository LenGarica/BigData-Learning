package exdatasource;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class JDBCMysql {

    public static void main(String[] args) {

        // 创建相关的context
        SparkSession sparkSession = SparkSession.builder()
                .appName("JDBCMysql")
                .master("local[2]")
                .getOrCreate();

        // 操作mysql的数据
        Dataset<Row> mysqlDF = sparkSession.read()
                .format("jdbc")
                .option("url", "jdbc:mysql://localhost:3306/hadoop_hive")
                .option("dbtable",  "hadoop_hive.TBLS")
                .option("user", "root")
                .option("password", "123456")
                .option("driver", "com.mysql.jdbc.Driver")
                .load();
        mysqlDF.printSchema();

        mysqlDF.show();

        mysqlDF.select("TBL_ID","TBL_NAME").show();

        // 关闭资源
        sparkSession.stop();

    }

}
