package LinkedList;


public class Practice {

    public static void main(String[] args) {
        MySinglyLL<Integer> list = new MySinglyLL<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addLast(7);
        list.addLast(8);
        MySinglyLL.Node<Integer> node = LL.reverseKGroup(list.first,3);
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }

    }
}
