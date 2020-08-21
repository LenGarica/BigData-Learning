package project

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, SourceFunction}

import scala.collection.mutable

class MySQLSource extends RichParallelSourceFunction[mutable.HashMap[String,String]]{

  var connection : Connection = null
  var pstm : PreparedStatement = null

  override def open(parameters: Configuration): Unit = {

    Class.forName("com.mysql.jdbc.Driver")
    val url = "jdbc:mysql://localhost:3306/flink?useUnicode=true&characterEncoding=utf8&useSSL=false"
    connection = DriverManager.getConnection(url,"root","123456")

    val sql = "select user_id , domain from user_domain_config "
    pstm = connection.prepareStatement(sql)
  }

  override def close(): Unit = {

    if(pstm != null){
      pstm.close()
    }

    if(connection != null){
      connection.close()
    }

  }

  override def cancel(): Unit = {
  }


  override def run(ctx: SourceFunction.SourceContext[mutable.HashMap[String, String]]): Unit = {

  }


}
