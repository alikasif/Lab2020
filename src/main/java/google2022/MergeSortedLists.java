package google2022;

import leetcode.Node;

import java.util.PriorityQueue;

public class MergeSortedLists {

    public static void main(String[] args) {

        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(4);

        Node head2 = new Node(1);
        head2.next = new Node(3);
        head2.next.next = new Node(4);

        Node head3 = null;
        Node tmp = null;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        while ( head1 != null && head2 != null) {

                if(head1.value <= head2.value) {
                    if(head3 == null) {
                        head3 = new Node(head1.value);
                        tmp = head3;
                    }
                    else {
                        head3.next = head1;
                        head3 = head3.next;
                    }
                    head1 = head1.next;
                }
                else{
                    if(head3 == null) {
                        head3 = new Node(head2.value);
                        tmp = head3;
                    }
                    else {
                        head3.next = head2;
                        head3 = head3.next;
                    }
                    head2 = head2.next;
                }
            System.out.println(head3);
        }

        while (head1 != null) {
            head3.next = head1;
            head1 = head1.next;
            head3 = head3.next;
        }

        while (head2 != null) {
            head3.next = head2;
            head2 = head2.next;
            head3 = head3.next;
        }
        System.out.println("done");
        while (tmp != null) {
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }
}
