package salesforce;

import leetcode.Node;

import java.util.Arrays;

public class FindLLPathInBT {
    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(4);
        root.left.left = new Node(8);
        root.right = new Node(4);
        root.right.right = new Node(6);
        root.left.right = new Node(2);
        root.left.right.left = new Node(1);

        root.right.left = new Node(2);
        root.right.left.left = new Node(6);
        root.right.left.right = new Node(8);
        root.right.left.right.left = new Node(1);
        root.right.left.right.right = new Node(3);

        Node head = new Node(4);
        head.next = new Node(2);
        head.next.next = new Node(8);
        System.out.println(findPath(root, head));
    }

    static boolean findPath(Node root, Node head) {
        if(root == null)
            return false;

        if(dfs(root, head))
            return true;

        if (findPath(root.left, head))
            return true;

        if(findPath(root.right, head))
            return true;

        return false;
    }

    static boolean dfs(Node root, Node n) {
        if(n == null)
            return true;

        if(root == null || root.value != n.value)
            return false;

        if(dfs(root.left, n.next))
            return true;

        if(dfs(root.right, n.next))
            return true;

        return false;
    }
}
