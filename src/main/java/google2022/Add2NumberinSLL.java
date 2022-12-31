package google2022;


import leetcode.Node;

public class Add2NumberinSLL {
    public static void main(String[] args) {

        Node head1 = new Node(2);
        head1.next= new Node(4);
        head1.next.next = new Node(3);

        Node head2 = new Node(5);
        head2.next= new Node(6);
        head2.next.next = new Node(8);
        head2.next.next.next = new Node(9);

        Node t1 = head1;
        Node t2 = head2;

        int carryover =0;
        Node res = null;
        Node tmp = null;

        while (t1 != null & t2 != null) {
            int s = t1.value + t2.value + carryover;
            carryover = s/10;
            s = s % 10;
            if (res == null){
                res = new Node(s);
                tmp = res;
            }
            else {
                res.next = new Node(s);
                res = res.next;
            }
            t1 = t1.next;
            t2 = t2.next;
        }

        while (t1 != null) {
            int s = t1.value + carryover;
            carryover = s/10;
            s = s % 10;
            if (res == null){
                res = new Node(s);
                tmp = res;
            }
            else {
                res.next = new Node(s);
                res = res.next;
            }
            t1 = t1.next;
        }

        while (t2 != null) {
            int s = t2.value + carryover;
            carryover = s/10;
            s = s % 10;
            if (res == null){
                res = new Node(s);
                tmp = res;
            }
            else {
                res.next = new Node(s);
                res = res.next;
            }
            t2 = t2.next;
        }

        if(carryover >0) {
            res.next = new Node(carryover);
            res = res.next;
        }

        while (tmp != null) {
            System.out.println(tmp.value);
            tmp = tmp.next;
        }
    }
}
