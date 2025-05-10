package Queue.implementation;


// It is a dynamic circular queue which increases its size when get full
//It inherits the properties of Circular queue
public class DynamicQueue extends CircularQueue {

    public DynamicQueue() {
        super();
    }

    public DynamicQueue(int size) {
        super(size);
    }


    @Override
    public boolean insert(int element) {
        if (isFull()) {   //O(n)
            int[] arr = new int[2 * data.length];
            data = copyData(arr, data);
        }
        return super.insert(element);

    }

    private int[] copyData(int[] arr, int[] data) {
//        int j = 0;
//        int i = front;
//        do {
//            arr[j] = data[i];
//            i++;
//            i = i % data.length;
//            j++;
//        } while (i != end);

        for (int i = 0; i < data.length; i++) {
            arr[i] = data[(front + i) % data.length];
        }
        front = 0;
        end = data.length;
        return arr;
    }

}
