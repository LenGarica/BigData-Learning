package org.example.project.log

import java.sql.{Connection, DriverManager, PreparedStatement}

/**
 * 操作MySQL类
 */
object MySQLUtils {

  def getConnection() = {
    //一定要设置编码，否则后面sql中文插入会出错误
    DriverManager.getConnection("jdbc:mysql://localhost:3306/access?useUnicode=true&characterEncoding=utf8&user=root&password=123456")

  }

  /**
   * 释放数据库等资源
   * @param connection
   * @param pstmt
   */
  def release(connection:Connection,pstmt:PreparedStatement)={
    try{
      if(pstmt != null){
        pstmt.close()
      }
    }catch {
      case e : Exception => e.printStackTrace()
    }finally {
      if(connection != null){
        connection.close()
      }
    }
  }


  def main(args: Array[String]): Unit = {
    println(getConnection())
  }

}
