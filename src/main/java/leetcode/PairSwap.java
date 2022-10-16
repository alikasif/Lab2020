package leetcode;

public class PairSwap {

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
       // head.next.next.next.next.next = new Node(6);

        Node curr = head;
        Node prev = curr;

        Node nh = null;
        while (curr != null) {

            Node tmp = curr.next;
            System.out.println("b4 => " +curr +" " +tmp +" "+prev);

                if ( tmp != null) {

                    prev.next = tmp;

                    curr.next = tmp.next;
                    tmp.next = curr;

                    prev = curr;
                    curr = curr.next;

                }
                else {
                    curr = tmp;
                }

            if (nh == null) {
                nh = tmp;
            }

            System.out.println("aft => "+curr +" " +prev);
        }

        System.out.println("swapped");
        while ( nh != null) {
            System.out.print(nh +" -> ");
            nh = nh.next;
        }

    }
}
