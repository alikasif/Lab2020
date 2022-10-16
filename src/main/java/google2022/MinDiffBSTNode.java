package google2022;

import leetcode.Node;

import java.util.concurrent.atomic.AtomicInteger;

public class MinDiffBSTNode {
    public static void main(String[] args) {

        Node root = new Node(4);
        root.left = new Node(2);
        root.right = new Node(6);
        root.left.left = new Node(1);
        root.left.right = new Node(3);

        AtomicInteger prev = new AtomicInteger(-1);
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);

        minDiff(root, prev, min);
        System.out.println(min.intValue());
    }

    static void minDiff(Node root, AtomicInteger prev, AtomicInteger min) {
        if(root == null)
            return ;

        minDiff(root.left, prev, min);

        if(prev.intValue() == -1) {
            prev.set(root.value);
            min.set(root.value);
        }
        else {
            min.set(Math.min(min.intValue(), Math.abs(min.intValue() - root.value)));
            prev.set(root.value);
        }
        minDiff(root.right, prev, min);
    }
}
