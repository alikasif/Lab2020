package leetcode;

public class ReverseSLL {

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node c = head;
        Node n = head.next;
        boolean t = true;

        while (n != null){

            if(t){
                t = false;
                c.next = null;
                Node tmp = n.next;
                n.next = c;
                c = n;
                n = tmp;
            }
            else {
                Node tmp = n.next;
                n.next = c;
                c = n;
                n = tmp;
            }
        }

        while(c != null) {
            System.out.print(c +" => ");
            c = c.next;
        }
    }
}
