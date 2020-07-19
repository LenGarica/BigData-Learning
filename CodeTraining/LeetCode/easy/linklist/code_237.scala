package LeetCode.easy.linklist

object code_237 {

  private class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }

  def deleteNode(node: ListNode): Unit = {
    node.x = node.next.x
    node.next = node.next.next
  }

}
