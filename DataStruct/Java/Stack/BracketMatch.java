package DataStruct.Stack;

import java.util.Stack;

/**
 * 括号匹配算法
 * Bracket matching algorithm
 */
public class BracketMatch {


    public static void main(String[] args) {
        char[] a = {'[','(',')',']'};
        char[] b = {'[','(',')','}'};
        String str = "()[]{}";
        System.out.println((new BracketMatch()).isValid(a));
        System.out.println((new BracketMatch()).isValid(b));
        System.out.println((new BracketMatch()).isValid(str));
    }

    /**
     * 当传入的参数是字符数组时使用
     * @param a 字符数组
     * @return 布尔类型
     */
    public boolean isValid(char[] a){
        Stack<Character> mystack = new Stack<>();
        for (char c : a) {
            if (c == '(' || c == '[' || c == '{') {
                mystack.push(c);
            } else {
                if (mystack.isEmpty()) {
                    return false;
                }
                char topCharacter = mystack.pop();
                if (c == ')' && topCharacter != '(') {
                    return false;
                }
                if (c == ']' && topCharacter != '[') {
                    return false;
                }
                if (c == '}' && topCharacter != '{') {
                    return false;
                }
            }
        }
        return mystack.isEmpty();
    }

    /**
     * 当传入的参数是字符串的时候
     * @param s 字符串
     * @return 布尔类型
     */
    public boolean isValid(String s){
        Stack<Character> mystack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                mystack.push(c);
            } else {
                if (mystack.isEmpty()) {
                    return false;
                }
                char topCharacter = mystack.pop();
                if (c == ')' && topCharacter != '(') {
                    return false;
                }
                if (c == ']' && topCharacter != '[') {
                    return false;
                }
                if (c == '}' && topCharacter != '{') {
                    return false;
                }
            }
        }
        return mystack.isEmpty();
    }
}
