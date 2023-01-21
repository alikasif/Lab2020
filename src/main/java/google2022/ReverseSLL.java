package google2022;

import leetcode.Node;

public class ReverseSLL {
    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        // 1 -> 2 -> 3 -> 4 -> 5
        Node prev = null;
        Node current = head;
        while (current != null) {
           Node tmp = current.next;
           current.next = prev;
           prev = current;
           current = tmp;
        }
        while (prev != null) {
            System.out.print(prev + " -> ");
            prev = prev.next;
        }
    }
}
