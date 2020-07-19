package DataStruct.Heap.MaxHeap;

import DataStruct.Queue.Queue;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxheap;

    public PriorityQueue(){
        maxheap = new MaxHeap<>();
    }

    @Override
    public void enqueue(E e) {
        maxheap.add(e);
    }

    @Override
    public E dequeue() {
        return maxheap.extractMax();
    }

    @Override
    public E getFront() {
        return maxheap.findMax();
    }

    @Override
    public int getSize() {
        return maxheap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxheap.isEmpty();
    }


}
