package google2022;

import leetcode.Node;

public class ReverseSLL {
    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node c = head;
        Node n = head.next;
        c.next = null;

        while ( n != null) {

            Node t = n.next;
            n.next = c;

            c = n;
            n = t;
        }
        while (c != null) {
            System.out.println(c);
            c = c.next;
        }
    }
}
