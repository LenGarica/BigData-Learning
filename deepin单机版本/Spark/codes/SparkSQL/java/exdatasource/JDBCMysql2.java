package exdatasource;

import java.util.Properties;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class JDBCMysql2 {

    public static void main(String[] args) {

        Properties connectionProperties = new Properties();
        connectionProperties.put("user", "root");
        connectionProperties.put("password", "123456");
        connectionProperties.put("driver", "com.mysql.jdbc.Driver");

        // 创建相关的context
        SparkSession sparkSession = SparkSession.builder()
                .appName("JDBCMysql2")
                .master("local[2]")
                .getOrCreate();

        // 操作mysql的数据
        Dataset<Row> mysqlDF = sparkSession.read()
                .jdbc("jdbc:mysql://localhost:3306", "hadoop_hive.TBLS", connectionProperties);

        mysqlDF.printSchema();

        mysqlDF.show();

        mysqlDF.select("TBL_ID","TBL_NAME").show();

        // 关闭资源
        sparkSession.stop();

    }

}
