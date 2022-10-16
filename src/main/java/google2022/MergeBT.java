package google2022;

import leetcode.Node;

public class MergeBT {
    public static void main(String[] args) {

        Node root1 = new Node(1);
        root1.left = new Node(3);
        root1.right = new Node(2);
        root1.left.left = new Node(5);

        Node root2 = new Node(2);
        root2.left = new Node(1);
        root2.right = new Node(3);
        root2.left.right = new Node(4);
        root2.right.right = new Node(7);

        Node root = merge(root1, root2);
        System.out.println(root.value);
        System.out.println(root.left.value);
        System.out.println(root.right.value);
        System.out.println(root.right.right.value);
    }

    static Node merge(Node root1, Node root2) {

        if(root1 == null && root2 == null)
            return null;

        Node root = new Node((root1 == null? 0 : root1.value) + (root2 == null? 0: root2.value));

        root.left = merge( root1 == null? root1 : root1.left, root2 == null? root2:root2.left);
        root.right = merge( root1 == null? root1 : root1.right, root2 == null? root2:root2.right);

        return root;
    }
}
