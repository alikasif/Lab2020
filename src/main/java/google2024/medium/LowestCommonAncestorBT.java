package google2024.medium;

import leetcode.Node;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestorBT {
    static Node previous = null;

    static Node ancestor(Node root, int p, int q) {

        if(root == null || root.value == p || root.value == q)
            return root;

        Node left = ancestor(root.left, p, q);
        Node right = ancestor(root.right, p, q);

        if (right != null && left != null)
            return root;

        if(left == null)
            return right;
        else
            return left;

    }

    static void traverse(Node root, List<Node> path, int p) {

        if(root == null || root.value == p) {
            if(root == null)
                return;
            path.add(root);
            System.out.println(path);
            return;
        }
        path.add(root);
        traverse(root.left, new ArrayList<>(path), p);
        traverse(root.right, new ArrayList<>(path), p);
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(5);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);

        root.right = new Node(1);
        root.right.left = new Node(0);
        root.right.right = new Node(8);

        //ancestor(root, 0, 5);
        traverse(root, new ArrayList<>(), 7);
        traverse(root, new ArrayList<>(), 6);

        System.out.println(ancestor(root, 7, 6));
    }
}
