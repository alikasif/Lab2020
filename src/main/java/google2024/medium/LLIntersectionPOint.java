package google2024.medium;

import leetcode.Node;

public class LLIntersectionPOint {
    public static void main(String[] args) {

        Node intersectioNode = new Node(511);
        intersectioNode.next = new Node(6);
        intersectioNode.next.next = new Node(7);

        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = intersectioNode;

        Node head2 = new Node(11);
        head2.next = new Node(22);
        head2.next.next = new Node(33);
        head2.next.next.next = new Node(44);
        head2.next.next.next.next = intersectioNode;

        Node a = head1;
        Node b = head2;

        while (a != b) {
            System.out.println(a + " | " + b);
            a = a == null? head2: a.next;
            b = b == null? head1 : b.next;
        }
        System.out.println(a);
    }
}
