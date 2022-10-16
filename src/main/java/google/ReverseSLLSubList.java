package google;

import leetcode.Node;

public class ReverseSLLSubList {

    public static void main(String[] args) {

        // 1 -> 3 -> 5 -> 7 -> 2
        // 1 -> 7 -> 5 -> 3 -> 2
        // 1 -> 3 <- 5 7 -> 2
        // 1 -> 3 <- 5 <- 7 2
        // 1 -> 7 -> 5 -> 3 -> 2

        Node head1 = new Node(1);
        head1.next = new Node(3);
        head1.next.next = new Node(5);
        head1.next.next.next = new Node(7);
        head1.next.next.next.next = new Node(2);

        int f = 2;
        int l = 4;

        Node tmp = head1;
        Node p = null;
        int i = 1;
        boolean b = true;

        while ( tmp != null && b ) {

            if( i == f ) {
                Node tmp1 = tmp.next;
                while ( f < l) {
                    Node n = tmp1.next; // 5  tmp -> 3
                    tmp1.next = n.next;
                    n.next = tmp.next;
                    tmp.next = n;
                    b = false;
                    f++;
                }
            }
            else {
                i++;
                p = tmp;
                tmp = tmp.next;
            }
        }

        System.out.println(p);
        System.out.println(tmp);
        p.next = tmp;
        System.out.println(head1 +" -> "+ head1.next+" -> "+ head1.next.next+" -> "+ head1.next.next.next+" -> "+ head1.next.next.next.next);

        /*System.out.println("reverse");
        while (head1 !=null) {
            System.out.println(head1);
            head1 = head1.next;
        }*/
    }
}
