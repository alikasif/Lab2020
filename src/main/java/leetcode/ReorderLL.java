package leetcode;

public class ReorderLL {

    public static void main(String[] args) {
        Node head= new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // 1 -> 2 -> 3 -> 4 -> 5
        // 1 2 5 4 3
        //
        // 1 5 2 4 3
        rearrange(head);
    }

    static void rearrange(Node head) {

        Node s = head;
        Node f = head.next.next;
        Node p = null;
        while (f != null) {
            p = s;
            s = s.next;
            f = f.next;
            if ( f != null)
                f = f.next;
        }

        System.out.println(s);
        Node mid = reverse(s);

        print(head);

        s = head;
        // 1 2 3 4 5 6 7 8 9
        // 1 2 3 4 9 8 7 6 5
        while (mid != null) {

            Node t = s.next;
            Node a = mid.next;

            s.next = mid;
            mid.next = t;
            s = t;
            mid = a;
        }

        System.out.println("rearranged");
        print(head);
    }

    static Node reverse(Node head) {
        // 1 2 3 4 5         5 4 3 2 1
        // 2 1 3 4 5
        // 2 1 4 3 5
        //

        Node p = head;
        Node n = p.next;
        boolean first = true;

        while (n != null) {
            Node t = n.next;
            n.next = p;
            if (first) {
                p.next = null;
                first  = false;
            }
            p = n;
            n = t;
        }
        print(p);
        System.out.println("returing");
        return p;

    }

    static void print(Node head) {
        while (head != null) {
            System.out.println(head);
            head = head.next;
        }
    }
}
