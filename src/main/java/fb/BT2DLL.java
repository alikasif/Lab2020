package fb;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BT2DLL {

    public static void main(String[] args) {

        Node root = new Node(100);
        root.left = new Node(50);
        root.right = new Node(200);
        root.left.left = new Node(25);
        root.left.right = new Node(75);
        root.right.left = new Node(125);
        root.right.right = new Node(350);
        Node leftmost = root.left.left;

        List<Node> lst = new ArrayList();
        lst.add(root);
        convert2DLL(root);
        root = leftmost;
        while (root != null) {
            System.out.println(root.prev+" -> " + root +" -> "+ root.next);
            root = root.next;
        }
    }

    static void convert2DLL(Node root) {

        Stack<Node> stack = new Stack<>();
        Node prev = null;
        while (root != null || stack.size() > 0) {

            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            if(prev == null)
                prev = root;
            else {
                prev.next = root;
                root.prev = prev;
                prev = root;
            }
            System.out.println(root);
            root = root.right;
        }
    }
}
