package DataStruct.Set;

import DataStruct.LinkList.LinkList2;


public class LinkListSet<E> implements MySet<E> {

    private LinkList2<E> list;

    public LinkListSet(){
        list = new LinkList2<>();
    }

    @Override
    public void add(E e) {
        if(!list.contains(e)){
            list.addFirst(e);
        }
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
