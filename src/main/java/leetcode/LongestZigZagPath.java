package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LongestZigZagPath {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.right = new Node(2);
        root.right.left = new Node(3);
        root.right.right = new Node(4);
        root.right.right.right = new Node(5);
        root.right.right.left = new Node(6);
        root.right.right.left.right = new Node(7);
        root.right.right.left.right.right = new Node(8);

        List<Node> finalst = new ArrayList<>();
        List<Node> path = new ArrayList<>();

        path.add(root);
        lpath(root, path, "na", finalst);
        System.out.println("longest path : " + finalst);
    }

    static void lpath(Node root, List<Node> path, String lastDir, List<Node> finalst) {

        System.out.println(path +"  from : "+lastDir);

        if (root == null) {
            if(path.size() > finalst.size()) {
                finalst.clear();
                finalst.addAll(path);
            }
            return;
        }

        if (root.right != null) {
            List<Node> t = new ArrayList<>(path);
            t.add(root.right);
            lpath(root.right, t, "right", finalst);
        }
        else {
            if (path.size() > finalst.size()) {
                finalst.clear();
                finalst.addAll(path);
                return;
            }
        }

        if (root.left != null) {
            List<Node> t = new ArrayList<>(path);
            t.add(root.left);
            lpath(root.left, t, "left", finalst);
        }
        else {
            if (path.size() > finalst.size()) {
                finalst.clear();
                finalst.addAll(path);
                return;
            }
        }
    }
}

