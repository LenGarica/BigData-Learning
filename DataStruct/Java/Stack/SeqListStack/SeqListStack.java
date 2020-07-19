package DataStruct.Stack.SeqListStack;

import DataStruct.Array.MyArray;
import DataStruct.Stack.Stack;

/**
 * 使用以前完成的动态数组实现顺序栈
 * @param <E> 泛型
 */
public class SeqListStack<E> implements Stack<E> {

    private MyArray<E> myarr;

    public SeqListStack(int capacity){
        myarr = new MyArray<>(capacity);
    }

    public SeqListStack(){
        myarr = new MyArray<>();
    }

    @Override
    public int getSize() {
        return myarr.getSize();
    }

    @Override
    public boolean isEmpty() {
        return myarr.isEmpty();
    }

    //因为底层使用的是静态数组，因此提供一个可以查看容量大小的函数
    public int getCapacity(){
        return myarr.getCapacity();
    }

    @Override
    public void push(E e) {
        myarr.addLast(e);
    }

    @Override
    public E pop() {
        return myarr.removeLast();
    }

    @Override
    public E peek() {
        return myarr.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack:");
        res.append('[');

        //遍历栈中的元素
        for (int i = 0; i < myarr.getSize(); i++) {
            res.append(myarr.get(i));
            if(i != myarr.getSize()-1){
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }


    public static void main(String[] args) {
        SeqListStack<Integer> mystack = new SeqListStack<>();

        for (int i = 0; i < 5; i++) {
            mystack.push(i);
            System.out.println(mystack);
        }

        mystack.pop();
        System.out.println(mystack);

    }

}
