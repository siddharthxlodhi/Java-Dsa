package Queue.implementation;

import java.util.Arrays;


//it uses array internally
//FIFO - first in first out
public class MyQueue {

    private int[] data;

    protected static int pointer = -1;

    private static final int DEFAULT_SIZE = 10;

    public MyQueue() {
        this(DEFAULT_SIZE);
    }

    public MyQueue(int size) {
        this.data = new int[size];
    }

    private boolean isFull() {
        return pointer == data.length - 1;
    }

    private boolean isEmpty() {
        return pointer == -1;
    }

    //O(1)
    public boolean insert(int element) {
        if (isFull()) {
            return false;
        }
        data[++pointer] = element;
        return true;
    }

    //O(n)
    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        int el = data[0];
        //shifting the elements
        for (int i = 0; i < pointer; i++) {
            data[i] = data[i + 1];
        }
        data[pointer] = 0;
        pointer--;
        return el;
    }

    public void front() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        System.out.println(data[0]);
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "data=" + Arrays.toString(data) +
                '}';
    }

}
