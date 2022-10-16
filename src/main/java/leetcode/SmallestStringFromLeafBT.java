package leetcode;

import java.util.ArrayList;
import java.util.List;

public class SmallestStringFromLeafBT {

    public static void main(String[] args) {

        Node root = new Node("a");
        root.left = new Node("b");
        root.right = new Node("c");
        root.left.left = new Node("d");
        root.left.right = new Node("e");
        root.right.left = new Node("d");
        root.right.right = new Node("e");

        List<Node> p = new ArrayList<>();
        List<List<Node>> allPaths = new ArrayList<>();

        root2leafpath(root, p, allPaths);
        allPaths.stream().forEach(x -> System.out.println(x));
    }

    static void root2leafpath(Node root, List<Node> path, List<List<Node>> allPaths) {

        if (root == null)
            return;

        // leaf node condition
        if(root.left == null && root.right == null) {
            path.add(root);
            allPaths.add(path);
        }
        else {
            List<Node> tl = new ArrayList<>(path);
            tl.add(root);
            root2leafpath(root.left, tl, allPaths);

            List<Node> tr = new ArrayList<>(path);
            tr.add(root);
            root2leafpath(root.right, tr, allPaths);
        }
    }
}
