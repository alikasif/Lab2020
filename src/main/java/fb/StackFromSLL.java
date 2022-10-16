package fb;
import fb.Util;

public class StackFromSLL {

    public static void main(String[] args) {

        Node head = new Node(1);
        head = push(head, 2);
        head = push(head, 3);
        head = push(head, 4);
        Util.printSLL(head);
    }

    static Node push(Node head, int val) {

        Node tmp = head;
        Node n = new Node(val);
        n.next = tmp;
        head = n;
        return head;

    }
}
