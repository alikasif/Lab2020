package google2024.hard;

import leetcode.Node;

public class ReverseInK {

    static public void reverse2(Node begin, Node end) {
        Node prev = begin;
        Node curr = begin.next;

        Node next, first;
        first = curr;

        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        begin.next = prev;
        first.next = curr;
        print(begin);
    }

    // utility function to reverse k nodes in the list
    static Node reverse(Node head, int k)
    {
        // If head is NULL or K is 1 then return head
        if (head == null || head.next == null)
            return head;

        // creating dummy node
        Node dummy = new Node(-1);
        dummy.next = head;

        // Initializing three points prev, curr, next
        Node prev = dummy;
        Node curr = dummy;
        Node next = dummy;

        // Calculating the length of linked list
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        // Iterating till next is not NULL
        while (next != null) {
            System.out.println(prev);
            curr = prev.next; // Curr position after every reverse group
            next = curr.next; // Next will always next to curr

            int toLoop = k < count ? k : count - 1;
            // toLoop will set to count - 1 in case of remaining element

            for (int i = 1; i < toLoop; i++) {
                // 4 steps as discussed above
                curr.next = next.next;
                next.next = prev.next;
                prev.next = next;
                next = curr.next;
            }

            prev = curr; // Setting prev to curr
            count -= k; // Update count
        }
        return dummy.next;
        // dummy -> next will be our new head for output linked list
    }

    static void reverse(Node start, Node end) {

        Node prev = null;
        Node current = start;
        Node printHead = start;

        while (current != end) {
            Node tmp = current.next;
            current.next = prev;
            prev = current;
            current = tmp;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);


        // 1 -> 2 -> 3 -> 4 -> 5
        // idea here is just to reverse the pointer reference b/w any 2 node


        Node prev = null;
        Node current = head;
        Node end = head.next.next;
        print(head);
        System.out.println("\nafter");
        //reverse2(current, end);
        Node reverse = reverse(head, 3);
        print(reverse);
    }

    static void print(Node head) {
        System.out.println();
        while (head != null) {
            System.out.print(head + " -> ");
            head = head.next;
        }
    }
}
