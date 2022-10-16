package google;

import leetcode.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AllPathSum {

    public static void main(String[] args) {

        Node root= new Node(5);
        root.left = new Node(4);
        root.right = new Node(8);

        root.left.left = new Node(11);
        root.left.left.left = new Node(7);
        root.left.left.right = new Node(2);

        root.right.left = new Node(13);
        root.right.right = new Node(4);
        root.right.right.left = new Node(5);
        root.right.right.right = new Node(1);

        int k = 22;
        pathSum(root, k, new ArrayList<>());
    }

    static void pathSum(Node root, int k, List<Integer> path) {

        if(root == null)
            return;

        if( k == root.value + sum(path)) {
            System.out.println(root.value +" "+ path);
        }
        else {
            path.add(root.value);
            pathSum(root.left, k, new ArrayList<>(path));
            pathSum(root.right, k, new ArrayList<>(path));
        }
    }

    private static Integer sum(List<Integer> path) {
        Optional<Integer> reduce = path.stream().reduce((x, y) -> x + y);
        if(reduce.isEmpty())
            return 0;
        return reduce.get();
    }
}
