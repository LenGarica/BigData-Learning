package flinkprimary.dataset.source.datasetcsv

import org.apache.flink.api.scala._

object DataSetDataSourceApp {

  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    csvFile(env)

  }

  def csvFile(environment: ExecutionEnvironment):Unit={
    val inputPath = "file:///home/willhope/data/people.csv"
    //使用tuple形式定义csv文件中的字段属性
    //使用ignoreFirstLine=true，忽略第一行的解析
    environment.readCsvFile[(String,Int,String)](inputPath,ignoreFirstLine = true).print()
    //也可以读取两列
    environment.readCsvFile[(String,Int)](inputPath,ignoreFirstLine = true).print()

    //使用case class形式解析文件
    case class MyCaseClass(name:String,age:Int)
    environment.readCsvFile[MyCaseClass](inputPath,ignoreFirstLine = true,includedFields = Array(0,1)).print()

    //pojo形式，使用Java定义一个Person类，使用Java更加直观一些，当然使用scala也可以，scala会自动生成getter和setter方法。
    environment.readCsvFile[Person](inputPath,ignoreFirstLine = true,pojoFields = Array("name","age","work")).print()

  }

}
