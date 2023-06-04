package salesforce;

import leetcode.Node;
import java.util.Stack;

public class ReorderSLL {
    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        Stack<Node> stack = new Stack<>();
        Node slow = head;
        Node fast = head;

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
            if(fast != null)
                fast = fast.next;
        }

        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }

        Node tmp = head;
        Node nextp  = null;
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            nextp = head.next;
            head.next = pop;
            pop.next = nextp;
            head = nextp;
        }

        head.next = null;

        while (tmp != null){
            System.out.println(tmp);
            tmp = tmp.next;
        }
    }
}
