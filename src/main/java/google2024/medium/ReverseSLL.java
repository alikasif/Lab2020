package google2024.medium;

import leetcode.Node;

public class ReverseSLL {
    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // null -> 1 -> 2 -> 3 -> 4 -> 5 -> null
        // idea here is just to reverse the pointer reference b/w any 2 node

        Node prev = null;
        Node current = head;
        Node printHead = head;

        while (current != null) {
            Node tmp = current.next;
            current.next = prev;
            prev = current;
            current = tmp;
        }
        print(prev);
    }

    static void print(Node head) {
        System.out.println();
        while (head != null) {
            System.out.print(head + " -> ");
            head = head.next;
        }
    }
}
