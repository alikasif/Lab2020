package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveDupeNode {

    public static void main(String[] args) {

/*
        Node head= new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(4);
        head.next.next.next.next.next = new Node(4);
        head.next.next.next.next.next.next = new Node(5);
*/

        Node head= new Node(1);
        head.next = new Node(1);
        head.next.next = new Node(1);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(3);

        removeDupes(head);
    }

    static void removeDupes(Node head) {

        Set<Integer> dupes = new HashSet<>();
        Node p1 = head;

        while(p1 != null) {
            if ( p1.next != null && p1.value == p1.next.value ) {
                dupes.add(p1.value);
                p1.next = p1.next.next;
            }
            p1= p1.next;
        }
        System.out.println(dupes);
        p1 = head;
        Node p2 = null;
        while( p1 != null ) {
            while (dupes.contains(p1.value)) {
                p1.value = p1.next.value;
                p1.next = p1.next.next;
            }
            p1= p1.next;
        }
        p1 = head;
        while(p1 != null) {
            System.out.println(p1);
            p1 = p1.next;
        }
    }
}
