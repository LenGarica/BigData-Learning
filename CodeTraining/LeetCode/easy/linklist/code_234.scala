package LeetCode.easy.linklist

import javax.management.ListenerNotFoundException

object code_234 {

  private class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }

  private var frontpointer = new ListNode()

  def isPalindrome(head: ListNode): Boolean = {
    frontpointer = head
    recursivelyCheck(head)
  }

  def recursivelyCheck(node: ListNode): Boolean ={
    if(node != null){
      if(!recursivelyCheck(node.next)) return false
      if(node.x != frontpointer.x) return false
      frontpointer = frontpointer.next
    }
    true
  }

}
