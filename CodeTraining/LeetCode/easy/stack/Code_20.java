package LeetCode.easy.stack;

import java.util.Stack;

public class Code_20 {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }

                char topStack = stack.pop();

                if(c == ')' && topStack != '('){
                    return false;
                }
                if(c == ']' && topStack != '['){
                    return false;
                }
                if(c == '}' && topStack != '{'){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid(char[] c){
        Stack<Character> stack = new Stack<>();
        for(char ch : c){
            if(ch == '(' || ch == '[' || ch == '{'){
                stack.push(ch);
            }else{
                if(stack.isEmpty()){
                    return false;
                }

                char topStack = stack.pop();

                if(ch == ')' && topStack != '('){
                    return false;
                }
                if(ch == ']' && topStack != '['){
                    return false;
                }
                if(ch == '}' && topStack != '{'){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {

        char[] c = {'(',')','[',']'};
        System.out.println(new Code_20().isValid(c));
        System.out.println(new Code_20().isValid("{}"));
    }


}
