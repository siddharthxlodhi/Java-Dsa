package Stack.implementation;

import java.util.Arrays;

//Stack internally used array
//LIFO( last in first out)
public class MyStack {
    protected int[] data;

    protected static int pointer = -1;

    private static final int DEFAULT_SIZE = 10;

    public MyStack() {
        this(DEFAULT_SIZE);
    }

    public MyStack(int size) {
        this.data = new int[size];
    }

    //O(1)
    public int push(int element) {
        pointer++;
        if (isFull()) {
            pointer--;
            throw new RuntimeException("Stack is full");
        }
        data[pointer] = element;
        return element;
    }

    protected boolean isFull() {
        return pointer == data.length;
    }

    //O(1)
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        int poppedEl = data[pointer];
        data[pointer] = 0;
        pointer--;
        return poppedEl;
    }

    private boolean isEmpty() {
        return pointer == -1;
    }

    //O(1)
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return data[pointer];
    }


    @Override
    public String toString() {
        return "MyStack{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
