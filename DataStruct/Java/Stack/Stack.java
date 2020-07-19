package DataStruct.Stack;

public interface Stack<E> {

    // 返回当前栈的大小
    int getSize();

    // 判断当前栈是否为空
    boolean isEmpty();

    // 进行压栈操作
    void push(E e);

    // 出栈操作
    E pop();

    // 查看栈顶元素
    E peek();
}
