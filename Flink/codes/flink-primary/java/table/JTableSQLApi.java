package flinkprimary.table;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.types.Row;

public class JTableSQLApi {

    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment tableEnv = BatchTableEnvironment.getTableEnvironment(env);

        String filePath = "file:///home/willhope/data/sales.csv";
        //获取dataset
        DataSet<Sales> csv = env.readCsvFile(filePath)
                .ignoreFirstLine()
                .pojoType(Sales.class,"transactionId","customerId","itemId","amountPaid");

        //将dataset转换成table
        Table sales = tableEnv.fromDataSet(csv);
        //注册成表
        tableEnv.registerTable("sales",sales);
        //查询
        Table resultTable = tableEnv.sqlQuery("select customerId , sum(amountPaid) money from sales group by customerId");
        //将结果转换为dataset
        DataSet<Row> result = tableEnv.toDataSet(resultTable, Row.class);
        result.print();

    }


    public static class Sales{
        public String transactionId;
        public String customerId;
        public String itemId;
        public Double amountPaid;
    }

}
