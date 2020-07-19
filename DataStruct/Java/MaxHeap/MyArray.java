package DataStruct.Heap.MaxHeap;


public class MyArray<E> {

    private E[] data;   // 定义数组
    private int size;   // 定义已使用长度

    //定义有参数构造函数，用户可以传入数组的最大容量
    public MyArray(int capacity){
        //先使数组可接受任意类型的元素，然后强转为要使用的
        data = (E[]) new Object[capacity];
        size = 0;
    }

    //定义无参构造函数，当用户没有想要传入的数组大小，则默认设置一个
    public MyArray(){
        //此处this用来调用构造方法，如果传入10，则表示调用上面有整型参数的构造方法
        this(10);
    }

    public MyArray(E[] arr){
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    //获取数组的容量
    public int getCapacity(){
        return data.length;
    }

    //获取数组已使用了多少容量
    public int getSize(){
        return size;
    }

    //判断当前数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //向数组中某个位置插入一个元素
    public void add(int index , E element){
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Required index > 0 | index < data.length");
        }

        if(size == data.length){
            resize(2 * data.length);
        }

        for (int i = size - 1; i >= index ; i--) {
            data[i + 1] = data[i];
        }

        data[index] = element;

        size++;
    }

    //数组扩容
    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    //向首位置添加元素
    public void addFirst(E e){
        add(0,e);
    }

    //向末尾添加一个元素
    public void addLast(E e){
        add(size , e);
    }

    //获取index位置上的元素
    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed.Index is illegal");
        }
        return data[index];
    }

    // 修改index索引位置的元素为e
    public void set(int index, E e){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = e;
    }

    //判断某个元素e是否在数组中
    public boolean isContain(E e){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    //判断某元素e是否在数组，若在则返回下标，若不在，则返回-1
    public int findElement(E e){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(e)){
                return i;
            }
        }
        return -1;
    }


    //修改index索引位置上的元素为e
    public void updateElement(int index , E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed.Index is illegal");
        }
        data[index] = e;

    }

    //删除数组中index位置上的元素，返回删除的元素
    public E removeElement(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Add failed. Required index > 0 | index < data.length");
        }
        E ret = data[index];
        for(int i = index + 1 ; i < size ; i++){
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;

        if(size == data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }
        return ret;
    }

    //删除数组红的第一个元素，返回删除的元素
    public E removeFirst(){
        return removeElement(0);
    }

    //删除最后一个元素
    public E removeLast(){
        return removeElement(size-1);
    }

    //删除数组中的元素e
    public void deleteElement(E e){
        int index = findElement(e);
        if(index != -1){
            removeElement(index);
        }
    }

    //方便用户查看最后一个元素
    public E getLast(){
        return get(size-1);
        //注意不能写成data[size-1],因为当size=0时，这个索引不合理
//        return data[size-1];
    }

    //方便用户查看第一个元素
    public E getFirst(){
        return get(0);
    }

    public void swap(int i , int j){
        if(i < 0 || i >= size || j < 0 || j >= size){
            throw new IllegalArgumentException("Index is illegal");
        }
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Array: size = %d , capacity = %d\n",size,data.length));
        result.append('[');
        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if(i != size - 1){
                result.append(", ");
            }
        }
        result.append(']');
        return result.toString();

    }


    public static void main(String[] args) {

        MyArray<Integer> myarr = new MyArray<>();
        for (int i = 0; i < 10; i++) {
            myarr.addLast(i);
        }
        System.out.println(myarr);

        myarr.add(1,100);
        System.out.println(myarr);

        myarr.addFirst(-1);
        System.out.println(myarr);

        myarr.addLast(123);
        System.out.println(myarr);

        myarr.deleteElement(2);
        System.out.println(myarr);

    }

}
