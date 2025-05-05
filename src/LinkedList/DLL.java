package LinkedList;


import java.util.*;

//Questions on Doubly linked list
public class DLL {


    //Reverse a doubly linked list(given Head)
    //Here we are swapping data
    // T.C:- O(2n)
    //S.C: - o(n)
    public static MyDoublyLL.Node<Integer> reverseDLL(MyDoublyLL.Node<Integer> head) {
        Stack<Integer> stack = new Stack<>();
        MyDoublyLL.Node<Integer> temp = head;

        while (temp != null) {
            stack.add(temp.value);
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            temp.value = stack.pop();
            temp = temp.next;
        }

        return head;
    }


    //Here we are swapping pointers
    //In this we just swap the next and previous pointers swap(next,prev)
    //T.C:-O(n)
    //S.C:-O(1)
    //visualize
    public static MyDoublyLL.Node<Integer> reverseDLLBetter(MyDoublyLL<Integer> LL) {

        MyDoublyLL.Node<Integer> curr = LL.first;
        while (curr != null) {
            //Swapping the prev and next pointers
            MyDoublyLL.Node<Integer> prev = curr.prev;
            curr.prev = curr.next;
            curr.next = prev;
            if (curr.prev == null) {
                LL.first = curr;  //This will be the head now,because we can not go further
            }
            curr = curr.prev;
        }
        return LL.first;
    }


    //deleting the occurrences  of x
    //T.C:- O(n)
    static MyDoublyLL.Node<Integer> deleteAllOccurOfX(MyDoublyLL.Node<Integer> head, int x) {

        MyDoublyLL.Node<Integer> temp = head;

        while (temp != null) {
            if (temp.value == x) {
                if (temp.prev == null) {
                    head = head.next;
                    head.prev = null;
                } else {
                    temp.prev.next = temp.next;
                    if (temp.next != null) {
                        temp.next.prev = temp.prev;
                    }

                }
            }
            temp = temp.next;
        }
        return head;
    }

    //approx O(n*n)
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, MyDoublyLL.Node<Integer> head) {

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        MyDoublyLL.Node<Integer> temp = head;


        while (temp != null) {
            MyDoublyLL.Node<Integer> temp1 = temp.next;

            while (temp1 != null && (temp.value + temp1.value) <= target) {
                if ((temp.value + temp1.value) == target) {
                    ArrayList<Integer> list1 = new ArrayList<>();
                    list1.add(temp.value);
                    list1.add(temp1.value);
                    list.add(list1);
                }

                temp1 = temp1.next;
            }

            temp = temp.next;
        }
        return list;
    }


    //O(2n)
    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSumbetter(int target, MyDoublyLL.Node<Integer> head) {

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        MyDoublyLL.Node<Integer> tail = head;
        MyDoublyLL.Node<Integer> temp = head;
        while (tail.next != null) {
            tail = tail.next;
        }

        while (temp.value < tail.value) {
            int sum = (temp.value + tail.value);
            if (sum == target) {
                ArrayList<Integer> list1 = new ArrayList<>();
                list1.add(temp.value);
                list1.add(tail.value);
                list.add(list1);
                temp = temp.next;
                tail = tail.prev;
            } else if (sum > target) {
                tail = tail.prev;
            } else {
                temp = temp.next;
            }
        }
        return list;
    }

    //O(n)
    public static MyDoublyLL.Node<Integer> removeDuplicates(MyDoublyLL.Node<Integer> head) {

        MyDoublyLL.Node<Integer> temp = head;

        while (temp != null && temp.next != null) {
            while (temp.next != null && Objects.equals(temp.value, temp.next.value)) {
                temp.next = temp.next.next;
                if (temp.next != null) {
                    temp.next.prev = temp;
                }
            }
            temp = temp.next;
        }

        return head;
    }


    //Better understandable
    //O(n)
    public static MyDoublyLL.Node<Integer> removeDuplicates2(MyDoublyLL.Node<Integer> head) {

        MyDoublyLL.Node<Integer> temp = head;

        while (temp != null && temp.next != null) {
            if (temp.value.equals(temp.next.value)) {
                temp.next = temp.next.next;
                if (temp.next != null) {
                    temp.next.prev = temp;
                }
            } else {
                temp = temp.next;
            }
        }

        return head;
    }

}
