package google2022;

import leetcode.Node;

public class IntersectionList {

    public static void main(String[] args) {

        Node cnode = new Node(8);
        cnode.next = new Node(4);
        cnode.next.next = new Node(5);

        Node head1 = new Node(4);
        head1.next = new Node(1);
        head1.next.next = cnode;

        Node head2 = new Node(5);
        head2.next = new Node(6);
        head2.next.next = new Node(1);
        head2.next.next.next = cnode;

/*
        int l1 = findLLLength(head1);
        int l2 = findLLLength(head2);

        while (l1 > l2) {
            head1 = head1.next;
            l1--;
        }

        while (l2 > l1) {
            head2 = head2.next;
            l2--;
        }
*/

        boolean found = false;

        Node a = head1;
        Node b = head2;

        while (a != b) {
            if (a == null)
                a = head2;
            else
                a= a.next;

            if(b == null)
                b = head1;
            else
                b=b.next;
        }
        System.out.println(a.value);
    }

    static int findLLLength(Node head) {
        int i = 0;
        while (head != null) {
            i++;
            head = head.next;
        }
        return i;
    }
}
