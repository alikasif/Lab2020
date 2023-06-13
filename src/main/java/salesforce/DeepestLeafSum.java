package salesforce;

import leetcode.Node;

import java.util.HashMap;
import java.util.Map;

public class DeepestLeafSum {
    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.left.right = new Node(5);
        root.right = new Node(3);
        root.right.right = new Node(6);
        root.right.right.right = new Node(8);
        root.left.left = new Node(4);
        root.left.left.left = new Node(7);

        Map<Node, Integer> depthMap = new HashMap<>();
        findLeaf(root, 0, depthMap);
        System.out.println(depthMap);
    }

    static void findLeaf(Node root, int depth, Map<Node, Integer> depthMap) {
        if(root == null)
            return;

        if(root.left == null && root.right == null) {
            depthMap.put(root, depth);
            return;
        }
        else {
            findLeaf(root.left, depth+1, depthMap);
            findLeaf(root.right, depth+1, depthMap);
        }
    }
}
