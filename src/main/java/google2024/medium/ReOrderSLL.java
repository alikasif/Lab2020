package google2024.medium;

import leetcode.Node;

public class ReOrderSLL {
    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);

        // null -> 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null
        // Idea here is just to reverse the pointer reference b/w any 2 node

        // Output 1 -> 5 -> 2 -> 4 -> 3
        /*
        *
        * 1 -> 2 -> 3 -> 4 -> 5
        * 1 -> 2 -> 5 -> 3 -> 4
        *
        * */

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        if(fast.next != null) {
            slow = slow.next;
        }

        fast = fast.next == null? fast: fast.next;

        System.out.println(slow);
        System.out.println(fast);

        Node current = head;
        Node mid = slow;
        int val = slow.value;
        slow.value = fast.value;
        fast.value = val;

        while (mid != null) {
            Node tmp = mid.next;
            Node tmp2 = current.next;
            current.next = mid;
            mid.next = tmp2;
            tmp2.next = tmp;
            mid = mid.next != null? mid.next.next : mid.next;
            current = current.next.next;
        }
        print(head);
    }

    static void print(Node head) {
        System.out.println();
        while (head != null) {
            System.out.print(head + " -> ");
            head = head.next;
        }
    }
}
