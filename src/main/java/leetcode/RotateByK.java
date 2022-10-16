package leetcode;

public class RotateByK {

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        /*head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);*/
        rotate(6, head);
    }

    static void rotate(int k, Node head) {

        int s =0;
        Node x = head;

        while (x != null){
            s++;
            x = x.next;
        }

        System.out.println(" llsize : "+s);
        System.out.println("final k : " + (k%s));
        k = (k%s);
        Node p1 = head;
        int i =0;

        while(i < k) {
            p1 = p1.next;
            i++;
        }

        System.out.println(p1);
        Node t = p1.next;
        p1.next = null;

        Node l = t;

        while (l.next != null)
            l = l.next;

        System.out.println(l);
        l.next = head;
        head = t;

        System.out.println("rotate: ");

        while (head != null) {
            System.out.println(head);
            head = head.next;
        }
    }
}
