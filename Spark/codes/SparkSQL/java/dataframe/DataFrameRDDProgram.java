package dataframe;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.ArrayList;
import java.util.List;

public class DataFrameRDDProgram {



    public static void main(String[] args) {
        String inputPath = "file:///home/willhope/app/data/infos.txt";

        //2. 设置SparkSession，master
        SparkSession sparkSession = SparkSession.builder().master("local[2]").appName("DataFrameRDDProgram").getOrCreate();

        //3. 读取文件，生成RDD
        JavaRDD<String> javaRDD = sparkSession.sparkContext().textFile(inputPath,1).toJavaRDD();

        //4. 处理原始数据RDD，生成新的RDD
        JavaRDD<Row> rowJavaRDD = javaRDD.map(
                line -> {
                    String[] infos = line.split(",");
                    Integer id = Integer.parseInt(infos[0]);
                    String name = infos[1];
                    Integer age = Integer.parseInt(infos[2]);
                    Row row = RowFactory.create(id, name, age);
                    return row;
                }
        );

        //5. 创建自定义数据类型
        List<StructField> fields = new ArrayList<>();
        StructField field_id = DataTypes.createStructField("id",DataTypes.IntegerType,true);
        StructField field_name = DataTypes.createStructField("name", DataTypes.StringType, true);
        StructField field_age = DataTypes.createStructField("age", DataTypes.IntegerType, true);
        fields.add(field_id);
        fields.add(field_name);
        fields.add(field_age);
        //6. 将自定义的数据类型StructType化，生成schema
        StructType schema = DataTypes.createStructType(fields);

        //7. 把RDD和schema关联起来并查询
        Dataset<Row> personDataFrame = sparkSession.createDataFrame(rowJavaRDD, schema);
        personDataFrame.printSchema();
        personDataFrame.show();
        personDataFrame.filter(personDataFrame.col("age").$greater(20)).show();

        //创建临时表，进行sql操作
//        personDF.createOrReplaceGlobalTempView("t_person");
//        sparkSession.sql("select * from t_person order by age desc").show();

        //关闭资源
        sparkSession.stop();

    }


}
