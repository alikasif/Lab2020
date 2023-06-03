package atlassian;

import leetcode.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class PerNodePathSum {

    static List<List<Node>> lists = new ArrayList<>();
    public static void main(String[] args) {

        Node root = new Node(10);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.left.left = new Node(3);
        root.left.left.right = new Node(-2);
        root.left.right = new Node(2);
        root.left.right.right = new Node(1);
        root.right = new Node(-3);
        root.right.right = new Node(11);
        inOrder(root, 8);
    }

    static void inOrder(Node root, int target) {

        if(root == null)
            return;
        inOrder(root.left, target);
        perNodeSum(root, target, root.value);
        inOrder(root.right, target);
    }

    static void perNodeSum(Node root, int target, int currSum ) {

        if(root == null) {
            return;
        }

        if(currSum == target)
            System.out.println("found target");

        if(root.left != null)
            perNodeSum(root.left, target, currSum+root.left.value);

        if(root.right != null)
            perNodeSum(root.right, target, currSum+root.right.value);
    }

    private static int sumList(List<Node> list) {

        if(list.isEmpty())
            return 0;
        Optional<Node> reduce = list.stream().reduce(new BinaryOperator<Node>() {
            @Override
            public Node apply(Node node, Node node2) {
                return new Node(node.value + node2.value);
            }
        });

        System.out.println(list + " sum: "+ reduce.get().value);
        return reduce.get().value;
    }
}
