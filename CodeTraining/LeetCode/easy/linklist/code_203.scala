package LeetCode.easy.linklist

object code_203 {

  class ListNode(var _x: Int = 0) {
    var next: ListNode = null
    var x: Int = _x
  }

  def removeElements(head: ListNode, `val`: Int): ListNode = {
    var pre = new ListNode
    var root = pre
    // 在scala中不能使用head = head.next，因此重新定义结点来代替
    pre.next = head
    var cur = head
    while (cur != null) {
      if (cur.x == `val`) {
        cur = cur.next
        pre.next = cur
      }
      else {
        pre = cur
        cur = cur.next
      }
    }
    root.next
  }
}

