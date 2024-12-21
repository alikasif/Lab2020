package google2024.medium;

import leetcode.Node;

public class RangeSumBST {

    static int sum = 0;

    public static void main(String[] args) {

        Node root = new Node(10);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right = new Node(15);
        root.right.right = new Node(18);
        sum = rangeSum(root, 7, 15);
        System.out.println(sum);

    }

    static int rangeSum(Node root, int min, int max) {

        if(root == null)
            return 0;
        System.out.println(root.value);

        if (root.value < min)
            return rangeSum(root.right, min, max);
        if (root.value > max)
            return rangeSum(root.left, min, max);

        return  root.value + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }
}
