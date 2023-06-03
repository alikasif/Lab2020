package salesforce;

import leetcode.Node;

import java.util.ArrayList;
import java.util.List;

public class AllNodesDistanceKFromTarget {
    public static void main(String[] args) {

        Node root = new Node(3);

        root.right = new Node(1);
        root.right.left = new Node(0);
        root.right.right = new Node(8);

        root.left = new Node(5);
        root.left.left = new Node(6);
        root.left.right= new Node(2);

        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);

        List<Node> list = new ArrayList<>();
        findNode(root,5, list, 0);
        findDistance(list.get(0), 2, 0);
        findDistance(root, 1, 0);
    }

    static void findNode(Node root, int target, List<Node> list, int d) {

        if(root == null || !list.isEmpty())
            return;

        if(root.value == target) {
            list.add(root);
            System.out.println(d);
        }

        findNode(root.left, target, list, d+1);
        findNode(root.right, target, list, d+1);
    }

    static void findDistance(Node root, int k, int d) {

        if(root == null)
            return;

        if(d == k)
            System.out.println(root);
        else {
            findDistance(root.left, k, d + 1);
            findDistance(root.right, k, d + 1);
        }
    }
}
