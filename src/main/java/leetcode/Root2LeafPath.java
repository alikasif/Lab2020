package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Root2LeafPath {

    public static void main(String[] args) {

        Node root = new Node(5);
        root.left = new Node(4);
        root.right = new Node(8);

        root.left.left = new Node(11);
        root.left.left.left = new Node(7);
        root.left.left.right = new Node(2);

        root.right.left = new Node(13);
        root.right.right = new Node(4);
        root.right.right.right = new Node(1);
        root.right.right.left = new Node(5);

        path(root, null);
    }

    static void path(Node root, List<Node> path) {

        if(root == null) {
            return;
        }

        if (path == null) {
            List<Node> llist = new ArrayList<>();
            llist.add(root);
            path(root.left, llist);

            List<Node> rlist = new ArrayList<>();
            rlist.add(root);
            path(root.right, rlist);
        }
        else {
            if (root.left == null && root.right == null) {
                path.add(root);
                System.out.println(path);
            }
            else {
                List<Node> llist = new ArrayList<>();
                llist.addAll(path);
                llist.add(root);
                path(root.left, llist);

                List<Node> rlist = new ArrayList<>();
                rlist.addAll(path);
                rlist.add(root);
                path(root.right, rlist);
            }
        }

    }
}
