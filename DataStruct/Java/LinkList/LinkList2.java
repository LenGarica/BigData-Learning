package DataStruct.LinkList;


/**
 * 带头结点的链表
 * @param <E> 泛型
 */
public class LinkList2<E> {

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

    private Node dummyhead; // 定义链表头
    int size; // 用来记录链表中的元素个数

    //链表初始化
    public LinkList2(){
        dummyhead = new Node(null , null);
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

    // 在链表中间添加一个新的元素index(0 - based)位置添加新的元素e
    // 在链表中不是一个常用的操作
    public void add(int index , E e){

        if(index < 0 || index > size){
            throw new IllegalArgumentException("index is error");
        }

        Node prev = dummyhead;
        for (int i = 0; i < index ; i++) {
            prev = prev.next;
        }

        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;
//            prev.next = new Node(e , prev.next);
        size ++;
    }

    //在链表头添加元素
    public void addFirst(E e){
        add(0,e);
    }

    //在链表末尾添加新的元素e
    public void addLast(E e){
        add(size ,e);
    }

    // 获取链表中第index位置的元素
    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. Illegal index .");
        }

        Node cur = dummyhead;
        for (int i = 0; i < index ; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    // 获取链表的第一个元素
    public E getFirst(){
        return get(0);
    }

    // 获取链表的最后一个元素
    public E getLast(){
        return get(size - 1);
    }

    // 修改链表中的第index个位置的元素
    public void set(int index , E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed. Illegal index .");
        }

        Node cur = dummyhead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    // 查找链表中是否有元素e
    public boolean contains(E e){
        Node cur = dummyhead.next;
        while (cur != null){
            if(cur.e == e){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // 删除链表中index位置上的元素
    public E remove(int index){

        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. Illegal index .");
        }

        Node prev = dummyhead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size --;

        return retNode.e;
    }

    // 删除链表的第一个元素
    public E removeFirst(){
        return remove(0);
    }

    // 删除链表中的最后一个元素
    public E removeLast(){
        return remove(size - 1);
    }

    // 从链表中删除元素e
    public void removeElement(E e){

        Node prev = dummyhead;
        while(prev.next != null){
            if(prev.next.e.equals(e))
                break;
            prev = prev.next;
        }

        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        Node cur = dummyhead.next;
        while(cur != null){
            res.append(cur+"->");
            cur = cur.next;
        }
//        可以写成下面这种
//        for (Node cur = dummyhead.next ; cur != null ; cur = cur.next){
//            res.append(cur+"->");
//        }

        res.append("Null");
        return res.toString();
    }
}
