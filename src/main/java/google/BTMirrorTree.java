package google;

import leetcode.Node;

public class BTMirrorTree {

    public static void main(String[] args) {
        Node root = new Node(19);
        root.left = new Node(7);
        root.left.left = new Node(3);
        root.left.right = new Node(11);

        root.right = new Node(43);
        root.right.right = new Node(47);
        root.right.left = new Node(23);

        inOrder(root);
        System.out.println();
        mirror(root);
        System.out.println();
        inOrder(root);
    }

    static void mirror(Node root) {
        if( root== null )
            return;

        Node tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        mirror(root.left);
        mirror(root.right);
    }

    static void inOrder(Node root) {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.print(root +" ");
        inOrder(root.right);
    }
}
