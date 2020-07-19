package DataStruct.Queue.SeqListQueue;

import DataStruct.Array.MyArray;
import DataStruct.Queue.Queue;

public class SeqListQueue<E> implements Queue<E> {

    private MyArray<E> myarr;

    public SeqListQueue(int capacity){
        myarr = new MyArray<>(capacity);
    }

    public SeqListQueue(){
        myarr = new MyArray<>();
    }

    @Override
    public void enqueue(E e) {
        myarr.addLast(e);
    }

    @Override
    public E dequeue() {
        return myarr.removeFirst();
    }

    @Override
    public E getFront() {
        return myarr.getFirst();
    }

    @Override
    public int getSize() {
        return myarr.getSize();
    }

    @Override
    public boolean isEmpty() {
        return myarr.isEmpty();
    }

    public int getCapacity(){
        return myarr.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue:\n");
        res.append("front [");

        //遍历队列中的元素
        for (int i = 0; i < myarr.getSize(); i++) {
            res.append(myarr.get(i));
            if(i != myarr.getSize()-1){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        SeqListQueue<Integer> myqueue = new SeqListQueue<>();
        for (int i = 0; i < 10; i++) {
            myqueue.enqueue(i);
            System.out.println(myqueue);

            if(i % 3 ==2){
                myqueue.dequeue();
                System.out.println(myqueue);
            }
        }
    }

}
