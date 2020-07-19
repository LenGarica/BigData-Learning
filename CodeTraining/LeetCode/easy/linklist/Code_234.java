package LeetCode.easy.linklist;

public class Code_234 {
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private ListNode frontPointer;

    public boolean isPalindrome(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }

    private boolean recursivelyCheck(ListNode head) {
        if(head != null){
            if (!recursivelyCheck(head.next)) return false;
            if(head.val != frontPointer.val) return false;
            frontPointer = frontPointer.next;
        }
        return true;
    }
}
