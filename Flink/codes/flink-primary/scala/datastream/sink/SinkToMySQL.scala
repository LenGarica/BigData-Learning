package flinkprimary.datastream.sink

import java.sql.{Connection, DriverManager, PreparedStatement}

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}

class SinkToMySQL extends RichSinkFunction[Student]{

  var connection : Connection = null
  var pstmt : PreparedStatement = null

  def getConnection = {
    Class.forName("com.mysql.jdbc.Driver")
    val url = "jdbc:mysql://localhost:3306/flink?useUnicode=true&characterEncoding=utf8&useSSL=false"
    connection = DriverManager.getConnection(url,"root","123456")
    connection
  }

  /**
   * 在open 方法中创建连接
   *
   * @param parameters
   */
  override def open(parameters: Configuration): Unit = {

    val conn = getConnection
    val sql = "insert into student(id,name,age) values (?,?,?) ON DUPLICATE KEY UPDATE name=?,age=?"

    pstmt = conn.prepareStatement(sql)
    println("open~~~~~~~")
  }

  /**
   * 每条记录插入时调用一次
   *
   * @param value
   * @param context
   */
  override def invoke(value: Student, context: SinkFunction.Context[_]): Unit = {

    pstmt.setInt(1, value.id)
    pstmt.setString(2, value.name)
    pstmt.setInt(3, value.age)
    pstmt.setString(4, value.name)
    pstmt.setInt(5, value.age)

    pstmt.executeUpdate()
    println("invoke~~~~~~~~~~~")
  }
  /**
   * 关闭资源
   */
  override def close(): Unit = {

    if (pstmt != null) {
      pstmt.close()
    }
    if (connection != null) {
      connection.close()
    }
  }
}
