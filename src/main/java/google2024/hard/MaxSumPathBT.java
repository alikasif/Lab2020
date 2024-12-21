package google2024.hard;

import leetcode.Node;

import java.util.concurrent.atomic.AtomicInteger;

public class MaxSumPathBT {

    static int maxs = 0;

    static int maxSum2(Node root) {

        if(root == null)
            return 0;

        int lsum = maxSum2(root.left);
        int rsum = maxSum2(root.right);

//      System.out.println(root +"|"+lsum +"|"+rsum);

        int sum = 0;

        if (root.value <0)
            sum = Math.max(lsum, rsum);
        else {
            sum = root.value;
            if (lsum >= 0)
                sum = sum + lsum;
            if (rsum >= 0)
                sum = sum + rsum;
        }

        maxs = Math.max(maxs, sum);
        return sum;
    }

    static int maxSum(Node node, int sum, AtomicInteger maxSum) {

        if(node == null)
            return 0;

        node.maxSum = node.value + maxSum(node.left, sum, maxSum) + maxSum(node.right, sum, maxSum);
        maxSum.set(Math.max(maxSum.intValue(), node.maxSum));
        return node.maxSum;
    }

    static void traverse(Node node) {
        if (node == null)
            return;

        traverse(node.left);
        System.out.println(node.maxSum);
        traverse(node.right);
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(-9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);
        AtomicInteger maxSum = new AtomicInteger(0);

//        maxSum(root, 0, maxSum);
//        System.out.println(maxSum.intValue());
//        traverse(root);

        System.out.println("new method");
//        Node root2 = new Node(1);
//        root2.left = new Node(2);
//        root2.right = new Node(3);
        maxSum2(root);
        System.out.println(maxs);

    }
}
