package google2022;

import leetcode.Node;

public class PathSum {

    public static void main(String[] args) {

        Node root = new Node(5);
        root.left = new Node(4);
        root.left.left = new Node(11);
        root.left.left.left = new Node(7);
        root.left.left.right = new Node(2);

        root.right = new Node(8);
        root.right.left = new Node(13);
        root.right.right = new Node(4);
        root.right.right.right = new Node(1);

        System.out.println(pathSum(root, 0, 22));
    }

    static boolean pathSum(Node root, int sum, int targetsum) {

        if( root == null)
            return false;

        if(root.left == null && root.right == null) {
            if (root.value + sum == targetsum)
                return true;
            else
                return false;
        }
        return (pathSum(root.left, sum+root.value, targetsum) ||
         pathSum(root.right, sum+root.value, targetsum));
    }
}
