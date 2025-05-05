package LinkedList;

import java.util.*;

public class LL {

    //Medium Problems of Singly Linked list

    //T.C:- O(n+ n/2)
    public static MySinglyLL.Node<Integer> middleNode(MySinglyLL.Node<Integer> head) {
        int size = 0;
        MySinglyLL.Node<Integer> temp = head;
        //Calculating the size of Linked list
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        int midAt = (size / 2) + 1;

        temp = head;    //Again placing temp to head

        for (int i = 1; i < midAt; i++) {
            temp = temp.next;
        }
        return temp;

    }

    //By tortoiseHare method
    //O(n/2)  --AS when the fast pointer reach end slow pointer cover half
    //in case of even length the fast pointer will reach to null
    //in case of odd length the fast pointer will reach last node
    public static MySinglyLL.Node<Integer> middleNodeOptimal(MySinglyLL.Node<Integer> head) {
        MySinglyLL.Node<Integer> fast = head;
        MySinglyLL.Node<Integer> slow = head;

        while (fast != null && fast.next != null) {   // if any one of case is false return slow
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    //Here we are swapping data
    //T.C -O(2n)
    //S.C -O(n)
    public MySinglyLL.Node<Integer> reverseList(MySinglyLL.Node<Integer> head) {
        MySinglyLL.Node<Integer> temp = head;
        Stack<Integer> stack = new Stack<>();
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

    //Here we are swapping the pointers
    //T>C:- O(n)
    //O(1)
    public static MySinglyLL.Node<Integer> reverseListBetter(MySinglyLL.Node<Integer> head) {
        MySinglyLL.Node<Integer> pointer = head;
        MySinglyLL.Node<Integer> prevRef = null;

        while (pointer != null) {
            MySinglyLL.Node<Integer> temp = pointer;
            pointer = pointer.next;
            temp.next = prevRef;
            prevRef = temp;
        }
        head = prevRef;
        return head;
    }


    public static MySinglyLL.Node<Integer> reverseListRecursive(MySinglyLL.Node<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        MySinglyLL.Node<Integer> newHead = reverseListRecursive(head.next);
        MySinglyLL.Node<Integer> front = head.next;
        front.next = head;
        head.next = null;
        return newHead;

    }

    //S.C:- O(n)
    //T.C: O(n*logn) log n for set add operation
    public boolean hasCycle(MySinglyLL.Node<Integer> head) {
        Set<MySinglyLL.Node<Integer>> set = new HashSet<>();
        while (head != null) {
            if (!set.add(head)) {    //added complexity
                return true;
            }
            head = head.next;
        }
        return false;
    }

    //By tortoise and fare
    //O(n)

    //here we just use move fast and slow pointer and if the (slow==fast) that means ,there is a loop
    public boolean hasCycleOptimal(MySinglyLL.Node<Integer> head) {
        MySinglyLL.Node<Integer> slow = head;
        MySinglyLL.Node<Integer> fast = head;

        while (fast != null && !(fast.next == null)) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    //Return Node where a cycle starts
    //Tc:- O(n*logn)
    //S.C:- O(n)
    public MySinglyLL.Node<Integer> detectCycle2(MySinglyLL.Node<Integer> head) {

        MySinglyLL.Node<Integer> temp = head;
        Map<MySinglyLL.Node<Integer>, Integer> map = new HashMap();
        while (temp != null) {
            if (map.containsKey(temp)) {
                return temp;
            }
            map.put(temp, 1);
            temp = temp.next;
        }
        return null;
    }

    //Using tortoise and fare
    //here we just use move fast and slow pointer and if the (slow==fast) that means ,there is a loop
    //from where the fast and slow get equal and from the head we traverse and when get equal its the start point
    public MySinglyLL.Node<Integer> detectCycle2ptimal(MySinglyLL.Node<Integer> head) {

        MySinglyLL.Node<Integer> fast = head;
        MySinglyLL.Node<Integer> slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            if (slow == fast) {
                slow = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;//starting point
            }

        }
        return null;
    }

    //T.C:- O(2n)
    //S.C:- O(n)
    //Putting the elements into the stack , and then compare the elements with the linked list by popping from stack
    public static boolean isPalindrome(MySinglyLL.Node<Integer> head) {
        Stack<Integer> stack = new Stack<>();
        MySinglyLL.Node<Integer> temp = head;
        while (temp != null) {
            stack.add(temp.value);
            temp = temp.next;
        }
        System.out.println(stack);
        temp = head;

        while (!stack.isEmpty()) {
            if (!Objects.equals(stack.pop(), temp.value)) return false;

            temp = temp.next;
        }
        return true;
    }

    //Here we reverse one half of the linked list and then compare with the other half with it
    //if some value is not equal means the list is not palindrome
    //T.C:- (n/2 +n/2+n/2)
    //S.C:- O(1)
    public static boolean isPalindromeOptimal(MySinglyLL.Node<Integer> head) {
        MySinglyLL.Node<Integer> slow = head;
        MySinglyLL.Node<Integer> fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        MySinglyLL.Node<Integer> curr = slow.next;
        MySinglyLL.Node<Integer> prevref = null;

        while (curr != null) {
            MySinglyLL.Node<Integer> front = curr.next;
            curr.next = prevref;
            prevref = curr;
            curr = front;
        }

        while (prevref != null) {
            if (!Objects.equals(head.value, prevref.value)) return false;
            head = head.next;
            prevref = prevref.next;
        }
        return true;

    }


    // T.C:-O(2n)
    //S.C:- O(n)
    //Here we just add the odd values first in the List and then even values
    //And then putting that values back in the list
    public MySinglyLL.Node<Integer> oddEvenList(MySinglyLL.Node<Integer> head) {

        if (head == null || head.next == null) {
            return head;
        }

        List<Integer> integers = new ArrayList<>();
        MySinglyLL.Node<Integer> odd = head;

        while (odd != null && odd.next != null) {   //O(n/2)
            integers.add(odd.value);
            odd = odd.next.next;
        }
        if (odd != null) {
            integers.add(odd.value);
        }
        MySinglyLL.Node<Integer> even = head.next;
        while (even != null && even.next != null) {    //O(n/2)
            integers.add(even.value);
            even = even.next.next;
        }
        if (even != null) {
            integers.add(even.value);
        }
        MySinglyLL.Node<Integer> temp = head;

        for (int i : integers) {    //O(n)
            temp.value = i;
            temp = temp.next;
        }
        return head;
    }


    //here we are swapping the pointers
    //T.C :- O(n/2)*2  2-- for unit operations;
    //S.C:- O(1)
    public MySinglyLL.Node<Integer> oddEvenList2(MySinglyLL.Node<Integer> head) {
        if (head == null) {
            return null;
        }
        MySinglyLL.Node<Integer> odd = head;

        MySinglyLL.Node<Integer> evenHead = head.next;
        MySinglyLL.Node<Integer> even = evenHead;

        while (odd.next != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            if (odd.next != null) {
                odd = odd.next;
            }
            if (even.next != null) {
                even = even.next;
            }
        }
        odd.next = evenHead;

        return head;

    }

    //T.C:- O(n) + O(n-l) -->where l is number of node to delete from the back
    //O(1)
    public MySinglyLL.Node<Integer> removeNthFromEnd(MySinglyLL.Node<Integer> head, int n) {
        if (head.next == null) {   //that means a single element is there so removing it
            return null;
        }

        int size = 0;
        MySinglyLL.Node<Integer> temp = head;

        while (temp != null) {         //Calculating the size of the linked list
            size++;
            temp = temp.next;
        }

        if (size == n) {     //that means ( eg- there are 5 nodes and we have given to remove 5th node from back)
            head = head.next;
            return head;
        }

        temp = head;

        int toGO = size - n;

        for (int i = 1; i <= toGO; i++) {    //traversing the node just before the node we have to delete
            temp = temp.next;
        }
        temp.next = temp.next.next;   //moving the pointer to one next

        return head;
    }


    //Here we find the node before mid node
    //O(n/2)
    public MySinglyLL.Node<Integer> deleteMiddle(MySinglyLL.Node<Integer> head) {
        if (head.next == null) {
            return null;
        }
        MySinglyLL.Node<Integer> slow = head;
        MySinglyLL.Node<Integer> fast = head;
        MySinglyLL.Node<Integer> prev = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            prev = slow;   //it contains the previous of mid element
            slow = slow.next;
        }
        prev.next = prev.next.next;
        return head;
    }


    //O(n + nlogn +n)
    //O(n)
    public MySinglyLL.Node<Integer> sortListBrute(MySinglyLL.Node<Integer> head) {
        List<Integer> list = new ArrayList<>();
        MySinglyLL.Node<Integer> temp = head;
        while (temp != null) {
            list.add(temp.value);
            temp = temp.next;
        }
        Collections.sort(list);
        temp = head;

        for (int i : list) {
            temp.value = i;
            temp = temp.next;
        }

        return head;
    }


    //T.C: -- O(n1+n2)
    public static MySinglyLL.Node<Integer> mergeTwoLists(MySinglyLL.Node<Integer> list1, MySinglyLL.Node<Integer> list2) {

        MySinglyLL.Node<Integer> temp1 = list1;
        MySinglyLL.Node<Integer> temp2 = list2;
        MySinglyLL.Node<Integer> newHead = new MySinglyLL.Node<>(-1);
        MySinglyLL.Node<Integer> pointer = newHead;
        while (temp1 != null && temp2 != null) {
            if (temp1.value <= temp2.value) {
                pointer.next = temp1;
                temp1 = temp1.next;
            } else {
                pointer.next = temp2;
                temp2 = temp2.next;
            }
            pointer = pointer.next;
        }

        while (temp1 != null) {
            pointer.next = temp1;
            pointer = temp1;
            temp1 = temp1.next;
        }

        while (temp2 != null) {
            pointer.next = temp2;
            pointer = temp2;
            temp2 = temp2.next;
        }
        return newHead.next;
    }

    //Test case - {1,2,3,4}
    //modify the tortoise and fare so that it will work use ->(fast=head.next)
    //T.C:- logn*(n+n/2)   n-- for merge n/2-- for mid finding

    //S.C:- O(log n) for recursive stack space
    public MySinglyLL.Node<Integer> sortListOptimal(MySinglyLL.Node<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        MySinglyLL.Node<Integer> mid = middleNodeOptimal(head);  //modify the tortoise and fare algo a bit to find correct first mid
        MySinglyLL.Node<Integer> leftHead = head;
        MySinglyLL.Node<Integer> rightHead = mid.next;
        mid.next = null;
        MySinglyLL.Node<Integer> list1 = sortListOptimal(leftHead);
        MySinglyLL.Node<Integer> list2 = sortListOptimal(rightHead);
        return mergeTwoLists(list1, list2);
    }

    //Here we connect all the node of 0 and 1 and 2 together in diff list
    //and merge them
    //T.C:- O(n)
    public static MySinglyLL.Node<Integer> sort012(MySinglyLL.Node<Integer> head) {
        MySinglyLL.Node<Integer> temp = head;
        MySinglyLL.Node<Integer> zeroH = new MySinglyLL.Node<>(-1);
        MySinglyLL.Node<Integer> zero = zeroH;
        MySinglyLL.Node<Integer> oneH = new MySinglyLL.Node<>(-1);
        MySinglyLL.Node<Integer> one = oneH;
        MySinglyLL.Node<Integer> twoH = new MySinglyLL.Node<>(-1);
        MySinglyLL.Node<Integer> two = twoH;

        while (temp != null) {
            if (temp.value == 0) {

                zero.next = temp;
                zero = temp;
            } else if (temp.value == 1) {
                one.next = temp;
                one = temp;
            } else {
                two.next = temp;
                two = temp;
            }
            temp = temp.next;
        }

        zero.next = (oneH.next != null) ? oneH.next : twoH.next;
        one.next = twoH.next;
        two.next = null;   //doubt

        return zeroH.next;
    }

    //O(list1+list2)

    public MySinglyLL.Node<Integer> getIntersectionNode(MySinglyLL.Node<Integer> headA, MySinglyLL.Node<Integer> headB) {

        MySinglyLL.Node<Integer> temp1 = headA;
        MySinglyLL.Node<Integer> temp2 = headB;

        Set<MySinglyLL.Node<Integer>> nodes = new HashSet<>();

        while (temp1 != null) {
            nodes.add(temp1);
            temp1 = temp1.next;
        }
        while (temp2 != null) {
            if (!nodes.add(temp2)) {
                return temp2;
            }
            temp2 = temp2.next;
        }


        return null;

    }

    //T.C:- O(3n)
    //T.C:- O(1)

    public MySinglyLL.Node<Integer> addOne(MySinglyLL.Node<Integer> head) {

        int carry = 1;

        MySinglyLL.Node<Integer> tempHead = reverseList(head);
        MySinglyLL.Node<Integer> temp = tempHead;

        while (temp != null) {
            temp.value = temp.value + 1;
            if (temp.value < 10) {
                carry = 0;
                break;
            } else {
                temp.value = 0;
            }
            temp = temp.next;
        }

        if (carry == 1) {
            MySinglyLL.Node<Integer> newNode = new MySinglyLL.Node<>(1);
            newNode.next = reverseListBetter(tempHead);
            return newNode;
        }

        return reverseListBetter(tempHead);
    }

    //By Recursion
    //with the help of backtracking we are going back
    //T.C:- O(n)
    //S.C:- O(N) recursion stack
    public MySinglyLL.Node<Integer> addOneOptimal(MySinglyLL.Node<Integer> head) {
        int carry = addOneRecursive(head);   //returns final carry
        if (carry == 1) {         //there is addition of new Node(1)
            MySinglyLL.Node<Integer> newNode = new MySinglyLL.Node<>(1);
            newNode.next = head;
            return newNode;
        }
        return head;
    }

    public int addOneRecursive(MySinglyLL.Node<Integer> head) {
        if (head == null) {
            return 1;
        }
        int carry = addOneRecursive(head.next);
        if (carry == 1) {
            head.value = head.value + carry;
            if (head.value < 10) {
                carry = 0;
            } else {
                head.value = 0;
            }
        }
        return carry;
    }


    //Note:- whenever we want to create new list use dummy node

    //T.C:- O( max(l1,l2))
    //S.C:- O(max(l1,l2)) --> this is used to store the result
    public MySinglyLL.Node<Integer> addTwoNumbers(MySinglyLL.Node<Integer> l1, MySinglyLL.Node<Integer> l2) {
        int carry = 0;
        MySinglyLL.Node<Integer> temp1 = l1;
        MySinglyLL.Node<Integer> temp2 = l2;

        MySinglyLL.Node<Integer> dummyNode = new MySinglyLL.Node<>(-1);
        MySinglyLL.Node<Integer> temp = dummyNode;


        while (temp1 != null && temp2 != null) {
            int val = temp1.value + temp2.value + carry;

            temp.next = new MySinglyLL.Node<>(val % 10);  // val%10 will give the number to be added;
            carry = val / 10;         //val/10 will give the carry

            temp = temp.next;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        while (temp1 != null) {
            int val = temp1.value + carry;

            temp.next = new MySinglyLL.Node<>(val % 10);  // val%10 will give the number to be added;
            carry = val / 10;    //carry to be added next

            temp = temp.next;
            temp1 = temp1.next;
        }
        while (temp2 != null) {
            int val = temp2.value + carry;

            temp.next = new MySinglyLL.Node<>(val % 10);  // val%10 will give the number to be added;

            carry = val / 10;
            temp = temp.next;
            temp2 = temp2.next;
        }

        if (carry == 1) {
            temp.next = new MySinglyLL.Node<>(1);
        }

        return dummyNode.next;
    }


    //Hard Problems of Singly linked list


    //Reverse Nodes in K groups(hard)
    //Do dry run(1,2,3,4,5,6,7,8,9,10) k=3
    //T.C:- O(2n)   because every time we check k elements by counting it ,and reversing if k elements are there---- (k+k)
    //even if there are no k  elements still we are counting it and after that adding those elements
    //So T.C:- O(2n)

    //S.C:- O(1)

    public static MySinglyLL.Node<Integer> reverseKGroup(MySinglyLL.Node<Integer> head, int k) {

        MySinglyLL.Node<Integer> temp = head;                                 //this temp will point to the first node of every k nodes
        MySinglyLL.Node<Integer> dummyH = new MySinglyLL.Node<>(-1);      //Using dummy node to connect all the reversals
        MySinglyLL.Node<Integer> dummy = dummyH;

        while (temp != null) {
            //reversing K nodes if exist
            if (!kExist(temp, k)) {        //if no k nodes are left break the while loop
                break;
            }
            MySinglyLL.Node<Integer> preRef = null;
            MySinglyLL.Node<Integer> prev = temp;             //this will help to  point the first node of (reversed k nodes)  ---> logic
            for (int i = 1; i <= k; i++) {
                MySinglyLL.Node<Integer> nextNode = temp.next;
                temp.next = preRef;
                preRef = temp;
                temp = nextNode;
            }
            dummy.next = preRef;                  //for the first time the (-1) dummy node will point to the first reversal
            dummy = prev;                         //After that this will take care

        }

        //linking the other remaining nodes
        while (temp != null) {
            dummy.next = temp;
            dummy = temp;
            temp = temp.next;
        }

        return dummyH.next;  //returning the dummy's next
    }

    //This function will check for reversal if k elements exist
    private static boolean kExist(MySinglyLL.Node<Integer> temp, int k) {
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
            if (count >= k) return true;
        }
        return false;
    }


    //Rotate linked list by k places
    //T.C:- O(length of list) + O(length of list - (length of list % k))

    public MySinglyLL.Node<Integer> rotateRight(MySinglyLL.Node<Integer> head, int k) {

        if (k == 0 || head == null || head.next == null) {
            return head;
        }

        MySinglyLL.Node<Integer> temp = head;

        //Calculating size and finding last element
        int size = 1;
        while (temp.next != null) {
            size++;
            temp = temp.next;
        }
        MySinglyLL.Node<Integer> last = temp;
        last.next = head;    //pointing the last to head

        k = k % size;

        if (k == 0 || size == k) {
            return head;
        }

        int goTo = size - k;
        temp = head;
        for (int i = 1; i < goTo; i++) {
            temp = temp.next;
        }

        head = temp.next;  //new head
        temp.next = null;
        return head;

    }

    //T.C:- O(n*m) +O(n*m log n n*m ) + o(n*m)
    //S.C:- O(n*m) + o(n*m)
    public static MySinglyLL.Node<Integer> flatten(MySinglyLL.Node<Integer> root) {
        ArrayList<Integer> list = new ArrayList<>();

        MySinglyLL.Node<Integer> temp = root;

        while (temp != null) {

            MySinglyLL.Node<Integer> down = temp;

//            while (down != null) {
//                list.add(down.value);
//                down = down.bottom;
//            }
            temp = temp.next;
        }

        Collections.sort(list);

        MySinglyLL.Node<Integer> newList = new MySinglyLL.Node<>(-1);
        temp = newList;

//        for (int i : list) {
//            temp.bottom = new MySinglyLL.Node<Integer>(i);
//            temp = temp.bottom;
//
//        }
        return newList.next;
    }

    // Time Complexity: O( N*(2M) ) ~ O(2 N*M)
    // (where N is the length of the linked list along the next pointer and M is the length of the linked list along the child pointers.)
    //O(1)
    public static MySinglyLL.Node<Integer> flattenOptimal(MySinglyLL.Node<Integer> root) {

        if (root.next == null) {
            return root;
        }

        MySinglyLL.Node<Integer> dummyH = new MySinglyLL.Node<>(-1);
        MySinglyLL.Node<Integer> dummy = dummyH;

        MySinglyLL.Node<Integer> temp = root.next;

        while (temp != null) {
//            root = mergeTwoBottomLists(root, temp);
            temp = temp.next;

        }
        return root;


    }

//    public static MySinglyLL.Node<Integer> mergeTwoBottomLists(MySinglyLL.Node<Integer> list1, MySinglyLL.Node<Integer> list2) {
//
//        MySinglyLL.Node<Integer> temp1 = list1;
//        MySinglyLL.Node<Integer> temp2 = list2;
//        MySinglyLL.Node<Integer> newHead = new MySinglyLL.Node<>(-1);
//        MySinglyLL.Node<Integer> pointer = newHead;
//        while (temp1 != null && temp2 != null) {
//            if (temp1.value <= temp2.value) {
//                pointer.bottom = temp1;
//                temp1 = temp1.bottom;
//            } else {
//                pointer.bottom = temp2;
//                temp2 = temp2.bottom;
//            }
//            pointer = pointer.bottom;
//        }
//
//        while (temp1 != null) {
//            pointer.bottom = temp1;
//            pointer = temp1;
//            temp1 = temp1.bottom;
//        }
//
//        while (temp2 != null) {
//            pointer.bottom = temp2;
//            pointer = temp2;
//            temp2 = temp2.bottom;
//        }
//        return newHead.bottom;
//    }


    //here we add the copied nodes as value in hashmap and original nodes as key of that copied nodes
    //S.C:- O(n) +O(n)  -- one is required as to create copy
    //T.C:- O(2n)
    public MySinglyLL.Node<Integer> copyRandomList(MySinglyLL.Node<Integer> head) {
        HashMap<MySinglyLL.Node<Integer>, MySinglyLL.Node<Integer>> map = new HashMap<>();

        MySinglyLL.Node<Integer> temp = head;


        //Putting in map
        //key -- original node
        //value-- copied node (with only data of original node)
        while (temp != null) {          //O(n)
            map.put(temp, new MySinglyLL.Node<Integer>(temp.value));
            temp = temp.next;
        }


        //This will point to the copied list
        MySinglyLL.Node<Integer> dummyH = new MySinglyLL.Node<Integer>(-1);
        MySinglyLL.Node<Integer> dummy = dummyH;

        //reassign the head to temp
        temp = head;

        //
        while (temp != null) {               //O(n)             //this temp will be the original nodes

            MySinglyLL.Node<Integer> copiedNode = map.get(temp);    //taking the copies node to make it point to other copied node
            copiedNode.random = map.get(temp.random);                           //pointing
            dummy.next = copiedNode;
            dummy = dummy.next;
            temp = temp.next;
        }
        return dummyH.next;
    }

    //In this we add the copies nodes to original list itself
    //T.C:-O(3n)
    //O(1)
    public MySinglyLL.Node<Integer> copyRandomList2(MySinglyLL.Node<Integer> head) {

        MySinglyLL.Node<Integer> temp = head;

        //Step:1 copying nodes to list with values
        while (temp != null) {
            MySinglyLL.Node<Integer> newNode = new MySinglyLL.Node<Integer>(temp.value);
            newNode.next = temp.next;
            temp.next = newNode;
            temp = temp.next.next;
        }

        //reassign the head to temp
        temp = head;

        // Step-2 :making the random pointers of copied nodes to point correctly
        while (temp != null) {
            temp.next.random = temp.random != null ? temp.random.next : null;
            temp = temp.next.next;
        }


        // Step 3: Separate the copied list while restoring the original list
        MySinglyLL.Node<Integer> dummyH = new MySinglyLL.Node<Integer>(-1);
        MySinglyLL.Node<Integer> dummy = dummyH;
        temp = head;

        while (temp != null) {
            dummy.next = temp.next; // Extract copied node
            dummy = dummy.next;
            temp.next = temp.next.next; // Restore original list
            temp = temp.next;
        }


        return dummyH.next;
    }


}
