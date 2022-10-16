package leetcode;

public class ReverseSubSLL {

    public static void main(String[] args) {

        Node head = new Node(11);
        head.next = new Node(3);
        head.next.next = new Node(5);
        head.next.next.next = new Node(7);
        head.next.next.next.next = new Node(2);

        int f = 3;
        int e = 4;

        Node t = head;
        int i = 1;
        while (i <= f - 1) {
            t = t.next;
            i++;
        }

        System.out.println(t);
        // 11 -> 3 -> 5 -> 7 -> 2
        Node s = t.next; // 3
        Node x = s.next; // 5
        while (f < e) {
            Node y = x.next; // 7
            x.next = s;
            s = x;
            x = y;
            f++;
        }
        t.next.next = x;
        t.next = s;
        //s.next = x;
        System.out.println("final");
        while (head!=null){
            System.out.println(head);
            head=head.next;
        }
    }
}
