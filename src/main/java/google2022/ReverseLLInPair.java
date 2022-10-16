package google2022;

import leetcode.Node;

public class ReverseLLInPair {

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Node node = reverseKNodes(head, 5);
        printLinkedList(node);
    }

    //utility function to find length of the list
    static int lengthOfLinkedList(Node head) {
        int length = 0;
        while(head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }
    //utility function to reverse k nodes in the list
    static Node reverseKNodes(Node head,int k) {
        if(head == null||head.next == null) return head;

        int length = lengthOfLinkedList(head);

        Node dummyHead = new Node(0);
        dummyHead.next = head;

        Node pre = dummyHead;
        Node cur;
        Node nex;

        while(length >= k) {
            cur = pre.next;
            nex = cur.next;
            for(int i=1;i<k;i++) {
                System.out.println(pre +" " + cur + " "+nex);
                cur.next = nex.next;
                nex.next = pre.next;
                pre.next = nex;
                nex = cur.next;
            }
            pre = cur;
            length -= k;
        }
        return dummyHead.next;
    }
    //utility function to print the list
    static void printLinkedList(Node head) {
        while(head.next != null) {
            System.out.print(head.value+"->");
            head = head.next;
        }
        System.out.println(head.value);
    }

}
