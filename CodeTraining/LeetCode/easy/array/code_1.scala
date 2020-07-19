package LeetCode.easy.array

import scala.collection.mutable

object code_1 {


  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
    val res = new Array[Int](2)
    val map = new mutable.HashMap[Int , Int]()

    for (i <- 0 until nums.length){
      val temp = target - nums(i);
      if(map.contains(temp)){
        res.update(0,i)
        res.update(1,map.getOrElse(temp , 0))
        res
      }else{
        map.put(nums(i) , i)
      }
    }
    res
  }


  def main(args: Array[String]): Unit = {
    val a =  Array(2, 7, 11, 15)
    val c = twoSum(a , 9)
    println(c.mkString(","))
  }

}
