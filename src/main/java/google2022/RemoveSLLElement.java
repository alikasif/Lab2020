package google2022;

import leetcode.Node;

public class RemoveSLLElement {
    public static void main(String[] args) {

        Node head1 = new Node(6);
        head1.next = new Node(6);
        head1.next.next = new Node(6);
/*
        head1.next.next.next = new Node(3);
        head1.next.next.next.next = new Node(4);
        head1.next.next.next.next.next = new Node(5);
        head1.next.next.next.next.next.next = new Node(6);
*/

        int v = 6;

        while ( head1 != null && head1.value == v)
            head1 = head1.next;

        if (head1 != null){
            Node p = head1;
            Node c = head1.next;

            while (c != null) {

                if (c.value == v) {
                    p.next = c.next;
                    c = c.next;
                } else {
                    p = c;
                    c = c.next;
                }
            }

            while (head1 != null) {
                System.out.println(head1);
                head1 = head1.next;
            }
        }

    }
}
