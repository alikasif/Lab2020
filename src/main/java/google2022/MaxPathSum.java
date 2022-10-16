package google2022;

import leetcode.Node;

public class MaxPathSum {

    static int maxSum =0;

    public static void main(String[] args) {
        Node root = new Node(-4);
        root.left = new Node(4);
        root.right = new Node(6);

        maxPathSum(root);
        System.out.println(maxSum);
    }

    static int maxPathSum(Node root) {
        if(root == null)
            return 0;

        int ls = Math.max(0, maxPathSum(root.left));
        int rs = Math.max(0, maxPathSum(root.right));

        int cs = root.value + ls +rs;

        maxSum = Math.max(maxSum, cs);
        return root.value + Math.max(ls, rs);
    }
}
