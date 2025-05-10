package Stack;

import LinkedList.MySinglyLL;

import java.util.*;

public class Problems {

    //1)Implementing Stack using array
    //2)Implementing Queues using two Array
    //(we have already done in the implementation classes)

    //3)Implementing Queues using two stack (IMP)

    //Approach-1 (insertion are expensive)
    private Stack<Integer> stack;

    public void MyQueue() {
        stack = new Stack<>();

    }

    public void push(int x) {
        Stack<Integer> tempStack = new Stack<>();
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }
        tempStack.push(x);
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    public int pop() {
        return stack.pop();
    }

    public int peek() {
        return stack.peek();
    }

    public boolean empty() {
        return stack.isEmpty();
    }


    //4)Implementing Stack using 2 Queue
    //Implementing Stack using single queue(get on leetcode)  (IMP)

    private Queue<Integer> queue;

    public void MyStack() {
        queue = new PriorityQueue<>();
    }

    public void pushStack(int x) {
        queue.add(x);
    }

    public int popStack() {
        Queue<Integer> temp = new ArrayDeque<>();
        int n = queue.size();
        for (int i = 1; i < n; i++) {
            temp.add(queue.remove());
        }
        int popped = queue.remove();
        while (!temp.isEmpty()) {
            queue.add(temp.remove());
        }
        return popped;

    }

    public int topStack() {
        Queue<Integer> temp = new ArrayDeque<>();
        int n = queue.size();
        for (int i = 1; i < n; i++) {
            temp.add(queue.remove());
        }
        int top = queue.remove();
        temp.add(top);
        while (!temp.isEmpty()) {
            queue.add(temp.remove());
        }

        return top;
    }

    public boolean emptyStack() {

        return queue.isEmpty();
    }


    //5)Implementing Stack using Linked list

    MySinglyLL.Node<Integer> top;


    //whenever we insert any el its next will point to top and then it is pointed by top
    //O(1)
    public void pushStackUsingList(int x) {
        MySinglyLL.Node<Integer> element = new MySinglyLL.Node<>(x);
        element.next = top;
        top = element;
    }

    //O(1)
    public int popStackUsingList() {
        if (top == null) {
            return -1;
        }
        int popped = top.value;
        top = top.next;
        return pop();

    }

    //O(1)
    public int peekStackUsingList() {
        if (top == null) {
            return -1;
        }
        return top.value;
    }

    //O(1)
    public boolean isEmptyStackUsingList() {
        return top == null;

    }


    //6)Implementing Queue using Linked list

    MySinglyLL.Node<Integer> front;
    MySinglyLL.Node<Integer> last;


    public void pushQueueUsingList(int x) {
        MySinglyLL.Node<Integer> newNode = new MySinglyLL.Node<Integer>(x);
        if (last == null) {
            front = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = last.next;
        }
    }


    //O(1)
    public int popQueueUsingList() {
        if (last == null) {
            return -1;
        }
        int removed = front.value;
        front = front.next;
        return removed;
    }

    //O(1)
    public int peekQueueUsingList() {
        if (last == null) {
            return -1;
        }
        return front.value;
    }

    //O(1)
    public boolean isEmptyQueueUsingList() {
        return last == null;

    }


    //Valid parentheses
    //T.C:- (O(n))
    //-->we check the last opening encountered ,thats why stack comes in

    //whenever we encounter an opening bracket we push it into the stack
    //and whenever we encounter a closing bracket we compare it with the top pushed bracket of the stack(if stack is empty return false)
    //Eg if the encountered bracket is } and the top bracket is not { means there is no closing in correct way return false)
    //At the last if the stack is empty means for every opening ,we got a corresponding closing
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {  //if it is opening one preserve it
                stack.push(c);
            } else {                                  //if it is closing one we compare it with the top preserved bracket if it matches or not
                if (stack.isEmpty()) {                //if no one for compare means no closing
                    return false;
                }
                char pop = stack.pop();
                if (c == '}' && !(pop == '{')) return false;  //if not of same type means no proper closing
                if (c == ']' && !(pop == '[')) return false;
                if (c == ')' && !(pop == '(')) return false;
            }

        }
        return stack.isEmpty();  // stack is empty means for every opening ,we got a corresponding closing
    }

    //Min stack -leetcode


    private Stack<Integer> stack1;
    int count = -1;
    ArrayList<Integer> min = new ArrayList<>();

    public void MinStack() {
        stack1 = new Stack<>();
    }

    public void pushs(int val) {
        if (stack.isEmpty()) {
            min.add(val);
            count++;
        } else {
            int mini = Math.min(val,min.get(count));
            min.set(++count, mini);
        }
        stack.push(val);
    }


    public void pops() {
        stack1.pop();
        min.remove(count);
        count--;
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return min.get(count);
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        System.out.println(stack.peek());
    }

}