package fb;
import fb.Util;

public class ReverseLLByK {

    public static void main(String[] args) {

        Node head = new Node(1);
            head.next = new Node(2);
            head.next.next = new Node(4);
            head.next.next.next = new Node(6);
            head.next.next.next.next = new Node(4);
            head.next.next.next.next.next = new Node(2);
            head.next.next.next.next.next.next =new Node(1);
            head.next.next.next.next.next.next.next =new Node(3);

            Util.printSLL(head);
            findMid(head);
           // reverseSLL(head);
    }

    static void findMid(Node head) {

        Node slow = head;
        Node fast = head;
        Node slow2 = null;

        while ( fast != null) {
            slow2 = slow;
            slow = slow.next;
            fast = fast.next;
            if( fast != null)
                fast = fast.next;
        }

        Node tmp = slow;
        slow2.next = null;

        Util.printSLL(tmp);
        Util.printSLL(head);

        Node rh = reverseSLL(tmp);
        Util.printSLL(rh);

        Node p1 = rh;
        Node p2 = head;

        while ( p1 != null && p2 != null) {

            if( p1.val != p2.val) {
                System.out.println("not a palindrome");
                return;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        if( p1 == null && p2 != null ) {
            if ( p2.next == null) {
                System.out.println("palindrome");
            }
        }

        else if( p1 != null && p2 == null) {
            if ( p1.next == null) {
                System.out.println("palindrome");
            }
        }

        else {
            System.out.println("not palindrome");
        }
    }

    // 1 2 4 6
    // 6 4 2 1
    static Node reverseSLL(Node head) {

        Node p1 = head;
        Node p2 = head.next;
        boolean f = true;

        while ( p2 != null ) {
            Node t = p2.next;
            p2.next = p1;
            //System.out.println(p1+" "+p2+" "+t);
            if(f) {
                p1.next = null;
                f = false;
            }
            p1 = p2;
            p2 = t;
        }
        head = p1;
        return head;
        //printSLL(head);
    }

}
