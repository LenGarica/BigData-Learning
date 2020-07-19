package DataStruct.LinkList;

/**
 * 不带头结点的链表
 * @param <E> 泛型
 */
public class LinkList<E> {


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

    private Node head; // 定义链表头
    int size; // 用来记录链表中的元素个数

    //链表初始化
    public LinkList(){
        head = null;
        size = 0;
    }

    //获取链表中的元素的个数
    public int getSize(){
        return size;
    }

    //判断链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //在链表头添加元素
    public void addFirst(E e){
        Node node = new Node(e);
        node.next = head;
        head = node;
        size ++;
//        可以用下面的语句替换
//        head = new Node(e , head);
//        size++;
    }

    // 在链表中间添加一个新的元素index(0 - based)位置添加新的元素e
    // 在链表中不是一个常用的操作
    public void add(int index , E e){

        if(index < 0 || index > size){
            throw new IllegalArgumentException("index is error");
        }
        if(index == 0){
            addFirst(e);
        }else{
            Node prev = head;
            for (int i = 0; i < index - 1; i++) {
                prev = prev.next;
            }

            Node node = new Node(e);
            node.next = prev.next;
            prev.next = node;
//            prev.next = new Node(e , prev.next);
            size ++;
        }

    }

    //在链表末尾添加新的元素e
    public void addLast(E e){
        add(size ,e);
    }



}
