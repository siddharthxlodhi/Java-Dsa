package LinkedList;

//Generic My singly linked list class
public class MySinglyLL<E> {

     Node<E> first;   //they are private
      Node<E> last;
      int size;

    public MySinglyLL() {
        this.size = 0;                   //Initially the size of the LL will be zero
    }

    public static class Node<E> {
        Node<E>  random;
       public    E value;
       public     Node<E> next;

        public Node(E e) {
            this.value = e;
        }

        public Node(E e, Node<E> next) {
            this.value = e;
            this.next = next;
        }
    }

    //Create a new code and point that node to where head is pointing ,and make the head to point new node
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = first;
        first = newNode;
        if (last == null) {    //if the last is null (that means the first element is getting added and both head and last point to the same element)
            last = newNode;
        }
        size++;
    }

    //Create a new node make
    //the node which is pointed by last, make that node point to the new node
    //then make the last to point new node
    public void addLast(E e) {
        if (last == null) {      //if the last is null,means first element is being added
            addFirst(e);
            return;
        }
        Node<E> newNode = new Node<>(e);
        last.next = newNode;
        last = newNode;
        size++;
    }


    public void display() {
        Node<E> temp = first;
        while (temp != null) {
            System.out.print(temp.value + "-->");
            temp = temp.next;
        }
        System.out.println("End");
    }

    public void insertAt(E e, int index) {
        if (index == 0) {    //means to add at the first index
            addFirst(e);
            return;
        } else if (index == size) {   //means to add at the last index
            addLast(e);
            return;
        }
        Node<E> temp = first;   //it will point to the one before the given index
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }
        Node<E> newNode = new Node<>(e, temp.next);
//      newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    public E deleteFirst() {
        E val = first.value;
        first = first.next;
        if (first == null) {
            last = null;
        }
        size--;
        return val;
    }


    public E deleteLast() {
        if (size == 1) {
            return deleteFirst();
        }
        E val = last.value;
        Node<E> lastSecond = getNode(size - 2);
        lastSecond.next = null;
        last = lastSecond;
        return val;
    }

    public E deleteAt(int index) {
        if (index == 0) {
            return deleteFirst();
        } else if (index == size - 1) {
            return deleteLast();
        }
        Node<E> prev = getNode(index - 1);

        E val = prev.next.value;
        prev.next = prev.next.next;
        size--;
        return val;
    }

    //This gives node at a particular index
    private Node<E> getNode(int index) {
        Node<E> temp = first;
        for (int j = 0; j < index; j++) {
            temp = temp.next;
        }
        return temp;
    }

    //returns the node containing given value
    private Node<E> getNode(E value) {
        Node<E> temp = first;
        while (temp != null) {
            if (temp.value == value) {
                return temp;
            }
            temp = temp.next;
        }
        return temp;
    }

}
