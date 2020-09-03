package org.example.sparksqloperatedata

import java.sql.DriverManager

/**
 *  jdbc编程访问
 */
object SparkSQLThriftServerApp {

  def main(args: Array[String]): Unit = {

    Class.forName("org.apache.hive.jdbc.HiveDriver")

    val connection = DriverManager.getConnection("jdbc:hive2://localhost:10000","willhope","") //密码不写
    val pstmt = connection.prepareStatement("select empno,ename from emp")
    val res = pstmt.executeQuery()
    while (res.next()){
      println("empno:"+res.getInt("empno") + ",ename:" + res.getString("ename"))
    }

    res.close()
    pstmt.close()
    connection.close()
  }


}
