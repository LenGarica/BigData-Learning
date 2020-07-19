package DataStruct.Queue.LinkListQueue;

import DataStruct.Queue.Queue;


public class LinkListQueue<E> implements Queue<E> {


    //定义链表的结点
    private class Node{
        public E e; // 结点元素的定义
        public Node next; // 指针域的定义

        public Node(E e , Node next){ //用户传入一个元素和所指向的下一个元素
            this.e = e;
            this.next = next;
        }

        public Node(E e){ //用户仅传入一个元素
            this(e,null);
        }

        public Node(){ // 用户什么都不传
            this(null , null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node head , tail;
    private int size;

    public LinkListQueue(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void enqueue(E e) {
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }else{
            tail.next = new Node(e);
            tail = tail.next;
        }

        size ++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }

        Node retNode = head;
        head = head.next;
        retNode.next = null;

        if(head == null){
            tail = null;
        }

        size --;

        return retNode.e;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("Cannot dequeue from an empty queue");
        }
        return head.e;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue : front ");

        Node cur = head;
        while(cur != null){
            res.append(cur+"->");
            cur = cur.next;
        }
//        可以写成下面这种
//        for (Node cur = head ; cur != null ; cur = cur.next){
//            res.append(cur+"->");
//        }

        res.append("Null tail");
        return res.toString();
    }


    public static void main(String[] args) {
        LinkListQueue<Integer> queue = new LinkListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 ==2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

}
