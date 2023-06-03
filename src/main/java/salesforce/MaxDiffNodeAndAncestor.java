package salesforce;

import leetcode.Node;

public class MaxDiffNodeAndAncestor {
    public static void main(String[] args) {
        Node root = new Node(6);

        root.right = new Node(8);
        root.right.left = new Node(7);
        root.right.right = new Node(9);

        root.left = new Node(2);
        root.left.left = new Node(0);
        root.left.right= new Node(4);
        root.left.right.left = new Node(3);
        root.left.right.right = new Node(5);

        System.out.println(maxDiff(root, root.value, root.value));
    }

    static int maxDiff(Node root, int min, int max) {

        if(root == null)
            return max-min;

        min = Math.min(min, root.value);
        max = Math.max(max, root.value);

        int l = maxDiff(root.left, min, max);
        int r = maxDiff(root.right, min, max);

        return Math.max(r,l);
    }
}