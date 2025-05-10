package Queue.implementation;

import java.util.Arrays;


//It contains circular array to avoid shifting while remove front
public class CircularQueue {

    protected int[] data;

    protected static int front = 0;  //point to first element
    protected static int end = 0;    //add the element then increment

    private int size = 0;

    private static final int DEFAULT_SIZE = 10;

    public CircularQueue() {
        this(DEFAULT_SIZE);
    }

    public CircularQueue(int size) {
        this.data = new int[size];
    }

    protected boolean isFull() {
        return size == data.length;
    }

    protected boolean isEmpty() {
        return size == 0;
    }

    //O(1)
    public boolean insert(int element) {
        if (isFull()) {
            return false;
        }
        data[end] = element;    //assign the element first
        end = ++end % data.length;  //then increment last so that new element can be added at that pos
        size++;
        return true;

    }


    //O(1)
    public int remove() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        int removedEl = data[front];
        front = ++front % data.length;
        size--;
        return removedEl;
    }

    //O(1)
    public void front() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        System.out.println(data[front]);
    }


    //O(n)
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        //debug it to get understanding
        int i = front;
        do {
            System.out.print("->" + data[i]);
            i++;
            i = i % data.length;
        } while (i != end);
        System.out.println();
    }

    @Override
    public String toString() {
        return "MyQueue{" + "data=" + Arrays.toString(data) + '}';
    }

}
