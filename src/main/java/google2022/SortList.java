package google2022;

import leetcode.Node;

public class SortList {
    public static void main(String[] args) {

        Node head = new Node(3);
        head.next = new Node(5);
        head.next.next = new Node(4);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(4);

        printLL(sort(head));
    }

    static Node sort(Node head) {

        printLL(head);
        if(head == null || head.next == null)
            return head;

        Node tmphead = head;
        Node right = head;
        Node prev = head;

        while (head !=null && head.next != null) {
            head = head.next.next;
            prev = right;
            right = right.next;
        }

        prev.next = null;
        tmphead = sort(tmphead);
        right = sort(right);
        return merge(tmphead, right);
    }

    private static void printLL(Node head) {
        while (head != null){
            System.out.print(head.value +" => ");
            head =head.next;
        }
        System.out.println();
    }

    static Node merge(Node slow, Node fast) {

        if (slow == null) return fast;
        if (fast == null) return slow;

        Node tmp = null;
        if(slow.value < fast.value) {
            tmp = slow;
            tmp.next = merge(slow.next, fast);
        }
        else {
            tmp = fast;
            tmp.next = merge(slow, fast.next);
        }
        return tmp;
    }
}
