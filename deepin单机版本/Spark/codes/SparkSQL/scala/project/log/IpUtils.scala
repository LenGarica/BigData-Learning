package org.example.project.log

import com.ggstar.util.ip.IpHelper

/**
 * IP解析工具类
 */
object IpUtils {

  def getCity(ip:String)={
    IpHelper.findRegionByIp(ip)
  }

  //测试是否能输出北京市
//  def main(args: Array[String]): Unit = {
//    println(getCity("58.30.15.255"))
//  }
}
