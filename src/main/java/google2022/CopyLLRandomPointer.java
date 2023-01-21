package google2022;

import leetcode.Node;

public class CopyLLRandomPointer {

    public static void main(String[] args) {

        Node head = new Node(7);
        Node node13 = new Node(13);
        Node node11 = new Node(11);
        Node node10 = new Node(10);
        Node node1 = new Node(1);

        head.next = node13;
        node13.next = node11;
        node11.next = node10;
        node10.next = node1;

        head.right = null;
        node13.right = head;
        node11.right = node1;
        node10.right = node11;
        node1.right = head;
        copy(head);
    }

    static void copy(Node head) {

        Node tmp = head;
        Node head2 = null;
        Node tmp2 = head2;

        while (tmp != null) {
            if(head2 == null)
                head2 = new Node(tmp.value);
            else {
                head2.next = new Node(tmp.value);
                head2= head2.next;
            }
            if(tmp2 == null)
                tmp2 = head2;
            head2.right = tmp;
            tmp = tmp.next;
        }
        head2 = tmp2;
        while (tmp2 != null) {
            System.out.println(tmp2 +" "+tmp2.next);
            tmp2 = tmp2.next;
        }
        System.out.println("b1");
        //head2 = tmp2;
        tmp2 = head2;
        while (tmp2 !=null) {
            tmp2.right = tmp2.right.right;
            tmp2 = tmp2.next;
        }

        System.out.println("b2");
        while (head != null) {
            System.out.println(head +" "+head.right);
            head = head.next;
        }
        System.out.println("b3");
        while (head2 != null) {
            System.out.println(head2 +" "+head2.right);
            head2 = head2.next;
        }
        System.out.println("b4");
    }
}
