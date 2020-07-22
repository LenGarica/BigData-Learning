package sparksqloperatedata;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.hive.HiveContext;

public class HiveContextJ {

    public static void main(String[] args) {

        // 1. 设置spark配置
        SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("HiveContextJ");

        // 2. 创建spark环境
        SparkContext sc = new SparkContext(sparkConf);

        // 3. 设置hive环境
        HiveContext hiveContext = new HiveContext(sc);

        // 4. 相关处理
        hiveContext.table("emp").show();

        // 5. 关闭资源
        sc.stop();

    }

}

