package Stack;

import Queue.implementation.CircularQueue;
import Queue.implementation.DynamicQueue;

public class Practice {

    public static void main(String[] args) {
        CircularQueue queue = new DynamicQueue(2);
        queue.insert(1);
        queue.insert(2);
        queue.remove();
        queue.insert(3);
        queue.display();
        queue.insert(4);
        queue.display();
        queue.insert(5);
        queue.remove();
        queue.insert(6);
        queue.display();


    }
}
