package Stack;

import java.util.HashMap;
import java.util.Stack;

public class Conversion {

    //Precedence
    static int prec(char ch) {
        return switch (ch) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    //Infix to Postfix conversion
    //T.C:- O(n)+O(n)+O(n) --> O(n)
    //S.C:- O(n)    -->at worst the stack might end up storing n elements
    //"a+b*(c^d-e)^(f+g*h)-i"

    public static String infixToPostfix(String str) {
        StringBuilder newStr = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if ((ch >= '1' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {   //If any operand put in the stack
                newStr.append(ch);
            } else {                                          //If there is an operator
                if (stack.isEmpty() || ch == '(') {           //if stack is empty push the operator or if there is an opening bracket push it
                    stack.push(ch);
                } else if (ch == ')') {                       //whenever there is a closing bracket we pop the chars from stack and append it on the new expression till we encounter that opening bracket
                    while (!stack.isEmpty() && !(stack.peek() == '(')) {
                        newStr.append(stack.pop());
                    }
                    stack.pop();                               //Popping opening bracket also

                } else {                                       //we compare the operator that is present on the stack
                    while (!stack.isEmpty() && prec(ch) <= prec(stack.peek())) {   //if the curr operator's prec is less than equal to the top operators prec we pop the top and append to the string
                        newStr.append(stack.pop());
                    }
                    stack.push(ch);           //at last push the curr operator
                }
            }
        }
        while (!stack.isEmpty()) {       //At last any remaining operator is appended to the string
            newStr.append(stack.pop());
        }
        return newStr.toString();
    }


    //infix to prefix
    public static String infixToPrefix(String string) {
        String s = reverse(string);

        Stack<Character> stack = new Stack<>();
        StringBuilder newStr = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if ((ch >= '1' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {   //If any operand put in the stack
                newStr.append(ch);
            } else {                                          //If there is an operator
                if (ch == '(') {           //if stack is empty push the operator or if there is an opening bracket push it
                    stack.push(ch);
                } else if (ch == ')') {                       //whenever there is a closing bracket we pop the chars from stack and append it on the new expression till we encounter that opening bracket
                    while (!stack.isEmpty() && !(stack.peek() == '(')) {
                        newStr.append(stack.pop());
                    }
                    stack.pop();                               //Popping opening bracket also
                } else {
                    if (ch == '^') {
                        while (!stack.isEmpty() && prec(ch) <= prec(stack.peek())) {   //if the curr operator's prec is less than equal to the top operators prec we pop the top and append to the string
                            newStr.append(stack.pop());
                        }
                        //at last push the curr operator
                    } else {
                        while (!stack.isEmpty() && prec(ch) < prec(stack.peek())) {   //if the curr operator's prec is less than equal to the top operators prec we pop the top and append to the string
                            newStr.append(stack.pop());
                        }
                        //at last push the curr operator
                        stack.push(ch);
                    }
                }
            }
        }
        while (!stack.isEmpty()) {       //At last any remaining operator is appended to the string
            newStr.append(stack.pop());
        }


        return newStr.reverse().toString();
    }


    //Reversing Infix
    private static String reverse(String string) {
        StringBuilder str = new StringBuilder(string);
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) == '(') {
                str.setCharAt(left, ')');
            } else if (str.charAt(left) == ')') {
                str.setCharAt(left, '(');
            }
            if (str.charAt(right) == ')') {
                str.setCharAt(right, '(');
            } else if (str.charAt(right) == '(') {
                str.setCharAt(right, ')');
            }

            char temp = str.charAt(left);
            str.setCharAt(left, str.charAt(right));
            str.setCharAt(right, temp);
            left++;
            right--;
        }
        return str.toString();
    }


    //Postfix to Infix
    //whenever we found a operand put it in stack
    //if an operator is there pop the top 2 strings and concat them using operator and push them
    //At last the top of stack will contain the resultant expression
    //T.C:-O(n) + O(n) for string concat
    //S.C:-O(n)

    public static String PostfixToInfix(String string) {
        Stack<String> stack = new Stack<>();

        for (char ch : string.toCharArray()) {
            if ((ch >= '1' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {   //If any operand put in the stack
                stack.push(String.valueOf(ch));
            } else {
                String t2 = stack.pop();
                String t1 = stack.pop();
                String temp = "(" + t1 +
                        ch +
                        t2 +
                        ")";
                stack.push(temp);
            }

        }

        return stack.pop();
    }


    //T.C:-O(n) + O(n) for string concat
    //S.C:-O(n)
    public static String PrefixToInfix(String string) {
        Stack<String> stack = new Stack<>();
        int n = string.length() - 1;

        for (int i = n; i >= 0; i--) {
            char ch = string.charAt(i);
            if ((ch >= '1' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {   //If any operand put in the stack
                stack.push(String.valueOf(ch));
            } else {
                String t1 = stack.pop();
                String t2 = stack.pop();
                String temp = "(" + t1 +
                        ch +
                        t2 +
                        ")";
                stack.push(temp);
            }
        }
        return stack.pop();
    }


    /*
    //Understand this relation

    Infix:-  (P+Q) * (M-N)
    Postfix:- PQ+MN-*
    Prefix:-  *+PQ-MN
    */

    //T.C:-O(n) + O(n) for string concat
    //S.C:-O(n)
    public static String PostifxToPrefix(String str) {
        Stack<String> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if ((ch >= '1' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {   //If any operand put in the stack
                stack.push(String.valueOf(ch));
            } else {
                String t2 = stack.pop();
                String t1 = stack.pop();
                String temp = ch + t1 + t2;
                stack.push(temp);
            }
        }
        return stack.pop();
    }

    //T.C:-O(n) + O(n) for string concat
    //S.C:-O(n)
    public static String PrefixToPostfix(String string) {
        Stack<String> stack = new Stack<>();
        int n = string.length() - 1;

        for (int i = n; i >= 0; i--) {
            char ch = string.charAt(i);
            if ((ch >= '1' && ch <= '9') || (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {   //If any operand put in the stack
                stack.push(String.valueOf(ch));
            } else {
                String t1 = stack.pop();
                String t2 = stack.pop();
                String temp = t1 + t2 + ch;
                stack.push(temp);
            }
        }
        return stack.pop();
    }


    public static void main(String[] args) {
        StringBuilder s = new StringBuilder("(a-b/c)*(a/k-l)");

        System.out.println(PrefixToPostfix("*+PQ-MN"));
    }

}
