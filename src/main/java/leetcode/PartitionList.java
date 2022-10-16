package leetcode;

public class PartitionList {

    public static void main(String[] args) {

        //Node head= new Node(1);
        Node head= new Node(5);
        head.next = new Node(4);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next.next = new Node(9);

        // 7 -> 1 -> 6 -> 2 -> 5 -> 8 -> 2 -> 9

        //partition(head, 0);
        partition2(head, 3);
    }

    static void partition(Node head, int x) {

        Node small = null;
        Node big = null;
        Node sh = null;
        Node bh = null;

        Node t = head;

        while ( t != null ) {
            if ( t.value <= x){
                if (sh == null){
                    sh = t;
                    small = t;
                }
                else {
                    small.next = t;
                    small = small.next;
                }
                if ( big != null) {
                    big.next = t.next;
                }
            }
            else {
                if (bh == null){
                    bh = t;
                    big = t;
                }
                else {
                    big.next = t;
                    big = big.next;
                }

                if ( small != null) {
                    small.next = t.next;
                }
            }
            t = t.next;
        }
        if ( small != null) {
            small.next = bh;
            System.out.println("lower than x: ");
            while (sh != null) {
                System.out.println(sh);
                sh = sh.next;
            }
        }
        else{
            while (bh != null) {
                System.out.println(bh);
                bh = bh.next;
            }
        }

        /*System.out.println("lower than x2: ");
        while (bh != null) {
            System.out.println(bh);
            bh = bh.next;
        }*/
    }

    static void partition2(Node head, int x) {

        Node f= head;
        Node s = head.next;

        while (s != null) {

            if (s.next != null && s.next.value < x) {
                Node t = s.next;
                s.next = t.next;
                Node t1 = f.next;
                f.next = t;
                t.next = t1;
                f = f.next;
            }
            else {
                s = s.next;
            }
        }
        if (head.value > x){
            Node t = head.next;
            Node t1 = f.next;
            head.next = f.next;
            f.next = head;
            head = t;
        }

        while(head != null){
            System.out.print(head +" -> ");
            head = head.next;
        }
    }

}
