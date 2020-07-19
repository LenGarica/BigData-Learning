package DataStruct.Set;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        /**
         * BSTSET的使用
         */
        System.out.println("Pride and Prejudice");

        ArrayList<String> word1 = new ArrayList<>();

        if (FileOperation.readFile("pride-and-prejudice.txt", word1)) {
            System.out.println("Total words:" + word1.size());

            BSTSet<String> set1 = new BSTSet<>();
            for (String word : word1) {
                set1.add(word);
            }
            System.out.println("Total different words:" + set1.getSize());
        }

        System.out.println();

        System.out.println("a-tale-of-two-cities");

        ArrayList<String> word2 = new ArrayList<>();

        if (FileOperation.readFile("a-tale-of-two-cities.txt", word2)) {
            System.out.println("Total words:" + word2.size());

            BSTSet<String> set2 = new BSTSet<>();
            for (String word : word2) {
                set2.add(word);
            }
            System.out.println("Total different words:" + set2.getSize());
        }

        System.out.println();

        /**
         * LinkListSET的使用，运行会非常慢
         */
        ArrayList<String> word3 = new ArrayList<>();

        if (FileOperation.readFile("a-tale-of-two-cities.txt", word3)) {
            System.out.println("Total words:" + word3.size());

            LinkListSet<String> set3 = new LinkListSet<>();
            for (String word : word3) {
                set3.add(word);
            }
            System.out.println("Total different words:" + set3.getSize());
        }

    }
}
