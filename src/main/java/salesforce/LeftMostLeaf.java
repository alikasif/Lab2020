package salesforce;

import leetcode.Node;
import java.util.concurrent.atomic.AtomicInteger;

public class LeftMostLeaf {

    static AtomicInteger leftDepth = new AtomicInteger(0);
    static AtomicInteger leftMostValue = new AtomicInteger(0);
    public static void main(String[] args) {

        Node root = new Node(1);

        root.right = new Node(3);
        root.right.left = new Node(5);
        root.right.left.left = new Node(7);
        root.right.right = new Node(6);

        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.left.right = new Node(8);
        root.left.left.right.left = new Node(9);

        findLeftMostLeaf(root,0);
        System.out.println("depth = "+leftDepth.get()+" v = " + leftMostValue.get());

    }

    static void findLeftMostLeaf(Node root, int depth) {
        if(root == null)
            return;

        if(root.left != null) {
            if(depth > leftDepth.get()) {
                leftDepth.set(Math.max(depth, leftDepth.get()));
                leftMostValue.set(root.left.value);
            }
        }
        findLeftMostLeaf(root.left, depth+1);
        findLeftMostLeaf(root.right, depth+1);
    }
}
