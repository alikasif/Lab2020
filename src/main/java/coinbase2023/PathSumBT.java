package coinbase2023;

import leetcode.Node;

public class PathSumBT {

    static  int count =0;
    public static void main(String[] args) {

        int ts = 8;

        Node root = new Node(8);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.left.left = new Node(3);
        root.left.left.right = new Node(-2);
        root.left.right = new Node(2);
        root.left.right.right = new Node(1);
        root.left.right.right.right = new Node(-1);
        root.left.right.right.right.right = new Node(1);
        root.right = new Node(-3);
        root.right.right = new Node(11);

        allPathSum(root, ts);
        System.out.println(count);
    }

    static void allPathSum(Node root, int ts) {
        if(root == null)
            return;

        if(ts == root.value)
            count++;

        pathSum(root.left, ts, 0);
        pathSum(root.right, ts, 0);
    }

    static void pathSum(Node root, int ts, int x) {
        if(root == null)
            return;
        if( x + root.value == ts) {
            count++;
        }
        pathSum(root.left, ts, x+root.value);
        pathSum(root.right, ts, x+root.value);
    }
}