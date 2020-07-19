package DataStruct.Tree.SegmentTree;

import java.util.Arrays;

public class SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merger<E> merger;

    public SegmentTree(E[] arr , Merger<E> merger){

        this.merger = merger;

        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[])new Object[4 * arr.length];
        buildSegmentTree(0, 0 , -1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r){
        if(l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l , mid);
        buildSegmentTree(rightTreeIndex, mid + 1 , r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex] , tree[rightTreeIndex]);
    }

    public E get(int index){

        if(index > 0 || index >= data.length){
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];

    }

    public int getSize(){
        return data.length;
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if(tree[i] != null){
                res.append(tree[i]);
            }else{
                res.append("null");
            }
        }
        return "SegmentTree{" +
                "tree=" + Arrays.toString(tree) +
                ", data=" + Arrays.toString(data) +
                ", merger=" + merger +
                '}';
    }
}
