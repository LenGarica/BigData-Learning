package flinkprimary.table

import org.apache.flink.api.scala._
import org.apache.flink.table.api.TableEnvironment
import org.apache.flink.types.Row

object TableSQLApi {

  def main(args: Array[String]): Unit = {

    val env = ExecutionEnvironment.getExecutionEnvironment
    val tableEnv = TableEnvironment.getTableEnvironment(env)
    val filePath = "file:///home/willhope/data/sales.csv"

    //拿到dataset
    val csv = env.readCsvFile[SalesLog](filePath,ignoreFirstLine = true)

    //转换成table
    val salesTable = tableEnv.fromDataSet(csv)
    //注册成表
    tableEnv.registerTable("sales",salesTable)

    //执行sql语句
    val resultTable = tableEnv.sqlQuery("select customerId , sum(amountPaid) money from sales group by customerId")

    //在转换为dataset
    tableEnv.toDataSet[Row](resultTable).print()

  }

  case class SalesLog(transactionId:String,customerId:String,itemId:String,amountPaid:Double)
}
