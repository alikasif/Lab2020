package leetcode;

public class LLCycle {

    public static void main(String[] args) {

        Node head= new Node(3);
        Node t = new Node(2);
        head.next = t;
        head.next.next = new Node(0);
        head.next.next.next = new Node(4);
        head.next.next.next.next = t;

        /*while ( head != null) {
            System.out.println(head);
            head = head.next;
        }*/
        findLoop(head);
    }

    static void findLoop(Node head) {
        Node s = head;
        Node f = head.next.next;

        while (f.value != s.value) {
            s = s.next;
            f = f.next.next;
        }

        System.out.println(s);
        System.out.println(f);
    }
}
