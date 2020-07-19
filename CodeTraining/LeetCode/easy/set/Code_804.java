package LeetCode.easy.set;

import java.util.TreeSet;

public class Code_804 {

    public static void main(String[] args) {
        String[]  words = {"gin", "zen", "gig", "msg"};
        int a = new Code_804().uniqueMorseRepresentations(words);
        System.out.println(a);
    }


    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

        TreeSet<String> set = new TreeSet<>();
        for (String word : words){
            StringBuilder res = new StringBuilder();;
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i)-'a']);
            }
            set.add(res.toString());
        }
        return set.size();
    }


}
