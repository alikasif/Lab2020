package google2022;

import leetcode.Node;

public class BTMaxDepth {

    public static void main(String[] args) {

        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);

        System.out.println(maxDepth(root));
    }

    private static int maxDepth(Node root) {
        if(root == null)
            return 0;
        return Math.max(1+ maxDepth(root.left), 1+ maxDepth(root.right));
    }
}
