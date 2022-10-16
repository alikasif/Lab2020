package leetcode;

public class MergeSLL {

    public static void main(String[] args) {

        Node head1 = new Node(2);
        head1.next = new Node(5);
        head1.next.next = new Node(7);

        Node head2 = new Node(3);
        head2.next = new Node(11);

        Node tmp1 = head1;
        Node tmp2 = head2;
        Node n = new Node(0);
        Node n2 = n;

        while (tmp1 != null && tmp2 != null) {
            if(tmp1.value <= tmp2.value) {
                 n.next = tmp1;
                 tmp1 = tmp1.next;
            }
            else {
                n.next = tmp2;
                tmp2 = tmp2.next;
            }
            n = n.next;
        }
        if(tmp1!= null)
            n.next = tmp1;
        else
            n.next = tmp2;

        while (n2 != null){
            System.out.println(n2.value);
            n2 = n2.next;
        }
    }
}
