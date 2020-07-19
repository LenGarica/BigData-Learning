package DataStruct.LinkList;

public class Test {

    public static void main(String[] args) {

        System.out.println("头插法");

        LinkList2<Integer> linklist = new LinkList2<>();

        for (int i = 0; i < 5; i++) {
            linklist.addFirst(i);
            System.out.println(linklist);
        }

        linklist.add(2, 666);
        System.out.println(linklist);

        System.out.println("尾插法");

        LinkList2<Integer> linklist2 = new LinkList2<>();

        for (int i = 0; i < 5; i++) {
            linklist2.addLast(i);
            System.out.println(linklist2);
        }

        linklist2.add(2, 666);
        System.out.println(linklist2);

        linklist2.remove(2);
        System.out.println(linklist2);

        linklist2.removeFirst();
        System.out.println(linklist2);

        linklist2.removeLast();
        System.out.println(linklist2);


    }

}
