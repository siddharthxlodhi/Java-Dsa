package Stack.implementation;

import java.util.Arrays;


//Its sizes grows dynamically
//It inherits the properties of Stack
public class DynamicStack extends MyStack {

    public DynamicStack() {
        super();
    }

    public DynamicStack(int size) {
        super(size);
    }


    @Override
    public int push(int element) {
        if (isFull()) {  //if the stack is full it creates a new array and copy elements in new array
            data = Arrays.copyOf(data, 2 * data.length);
        }
        super.push(element);  //then pushing same as it is
        return element;
    }


}
