package google;

import leetcode.Node;

public class MergeSortedSLL {

    public static void main(String[] args) {

        Node head1 = new Node(2);
        head1.next = new Node(5);
        head1.next.next = new Node(7);

        Node head2 = new Node(3);
        head2.next = new Node(11);

        Node tmp = null;

        while (head1 != null && head2 != null) {

            if (head1.next != null && head1.next.value < head2.value) {
                head1 = head1.next;
                continue;
            }

            if (head2.next != null && head2.next.value < head1.value) {
                head2 = head2.next;
                continue;
            }

            if(head1.value <= head2.value) {

                if(tmp == null)
                    tmp = head1;

                Node t = head1.next;
                head1.next = head2;
                head1 = t;
            }
            else {
                if (tmp == null)
                    tmp = head2;
                Node t = head2.next;
                head2.next = head1;
                head2 = t;
            }
        }

        while (tmp != null)
        {
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }
}
