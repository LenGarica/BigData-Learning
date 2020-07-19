package LeetCode.easy.linklist;

public class Code_206 {

    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode tempNode = cur.next;
            cur.next = pre;
            pre = cur;
            cur =tempNode;
        }
        return pre;
    }

}
