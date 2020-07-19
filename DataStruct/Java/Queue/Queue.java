package DataStruct.Queue;

public interface Queue<E> {

    //进队
    void enqueue(E e);

    //出队
    E dequeue();

    //返回当前队头元素
    E getFront();

    //获取当前队列大小
    int getSize();

    //判断队列是否为空
    boolean isEmpty();

}
