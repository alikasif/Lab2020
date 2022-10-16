package fb;
import fb.Util;

public class Sort012 {

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(0);
        head.next.next.next.next = new Node(2);
        head.next.next.next.next.next = new Node(0);
        head.next.next.next.next.next.next =new Node(1);
        head.next.next.next.next.next.next.next =new Node(2);

        Util.printSLL(head);
        sort(head);
    }

    static void sort(Node head) {

        Node last = head;

        while (last.next != null) {
            last = last.next;
        }
        //System.out.println(last);

        Node start = head;

        Node zero = null;
        Node zh = null;

        Node one = null;
        Node oh = null;

        Node two = null;
        Node th = null;

        while ( start != null) {
            System.out.println(start + " => ");

            if ( start.val == 0) {

                if(zero == null) {
                    zero = start;
                    //zero.next = null;
                    zh = zero;
                }
                else {
                    zero.next = start;
                    zero  = zero.next;
                    //zero.next = null;
                }
            }

            else if ( start.val == 1) {

                if(one == null) {
                    one = start;
                    //one.next = null;
                    oh = one;
                }
                else {
                    one.next = start;
                    one  = one.next;
                    //one.next = null;
                }
            }

            else if ( start.val == 2) {

                if(two == null) {
                    two = start;
                    //two.next = null;
                    th = two;
                }
                else {
                    two.next = start;
                    two  = two.next;
                    //two.next = null;
                }
            }
            start = start.next;
        }

        zero.next = oh;
        one.next = th;
        two.next = null;

        Util.printSLL(zh);
        //Util.printSLL(oh);
        //Util.printSLL(th);
    }
}
