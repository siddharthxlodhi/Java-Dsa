package LinkedList;

import java.util.NoSuchElementException;

public class MyDoublyLL<E> {

    Node<E> first;
    Node<E> last;
    int size;

    public MyDoublyLL() {
        this.size = 0;
    }

    public static class Node<E> {

        E value;
        Node<E> prev;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public void addFirst(E val) {
        Node<E> newNode = new Node<>(val, null, first);
        if (last != null) {
            first.prev = newNode;
        }
        first = newNode;
        if (last == null) {
            last = newNode;
        }
        size++;
    }

    public void addLast(E val) {
        if (size == 0) {
            addFirst(val);
            return;
        }
        Node<E> newNode = new Node<>(val, last, null);
        last.next = newNode;
        last = newNode;
        size++;
    }


    public void addAt(E val, int index) {
        if (index == 0) {
            addFirst(val);
            return;
        } else if (index == size) {
            addLast(val);
            return;
        }
        Node<E> temp = first;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }

        Node<E> newNode = new Node<>(val, temp, temp.next);
        temp.next.prev = newNode;
        temp.next = newNode;
        size++;
    }

    public void addAfter(E after, E val) {
        Node<E> afterThis = getNode(after);
        if (afterThis == null) {
            System.out.println("doest not exist ");
            return;
        }
        Node<E> newNode = new Node<>(val, afterThis, afterThis.next);
        if (afterThis.next != null) {
            afterThis.next.prev = newNode;
        } else {
            last = newNode;    //that means the element is added at last index
        }
        afterThis.next = newNode;
    }

    public E deleteFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E val = first.value;
        first = first.next;
        if (first == null) {
            last = null;
            size--;
            return val;
        }
        first.prev = null;
        size--;
        return val;
    }

    public E deleteLast() {
        if (size <= 1) {
            return deleteFirst();
        }
        E val = last.value;
        last = last.prev;
        last.next = null;
        size--;
        return val;
    }

    public E deleteAt(int index) {
        if (index == 0) {
            return deleteFirst();
        } else if (index == size - 1) {
            return deleteLast();
        }
        Node<E> temp = first;
        Node<E> prev = getNode(index - 1);
        E val = prev.next.value;
        prev.next = prev.next.next;
        prev.next.prev = prev;
        size--;
        return val;
    }

    //get node at a particular index
    private Node<E> getNode(int index) {
        Node<E> temp = first;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }


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


    public void display() {
        Node<E> temp = first;
        while (temp != null) {
            System.out.print(temp.value + "-->");
            temp = temp.next;
        }
        System.out.println("End");
    }

    public void displayReverse() {
        Node<E> temp = last;
        while (temp != null) {
            System.out.print(temp.value + "-->");
            temp = temp.prev;
        }
        System.out.println("Start");
    }


}
