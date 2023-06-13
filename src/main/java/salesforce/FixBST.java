package salesforce;

import leetcode.Node;

public class FixBST {

    static Node prev = null;
    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(1);
        root.right = new Node(4);
        root.right.left = new Node(2);
        inOrder2(root);
        inOrder(root);
        System.out.println();
        inOrder2(root);

    }

    static void inOrder2(Node root) {

        if(root == null)
            return;

        inOrder2(root.left);
        System.out.println(root);
        inOrder2(root.right);
    }

    static void inOrder(Node root) {

        if(root == null)
            return;

        inOrder(root.left);
        //System.out.println(root +" "+ prev);
        if(prev != null && prev.value > root.value) {
            int t = prev.value;;
            prev.value = root.value;
            root.value =t;
        }
        prev = root;
        inOrder(root.right);
    }
}