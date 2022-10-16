package google2022;

import leetcode.Node;

public class IsSubTree {

    public static void main(String[] args) {

        Node root = new Node(3);
        root.right = new Node(5);
        root.left = new Node(4);
        root.left.left = new Node(1);
        root.left.right = new Node(2);

        System.out.println(st(root, root.left));
    }

    static boolean st(Node root, Node subnode){
        if(root == null)
            return false;

        if (checkEquality(root, subnode))
            return true;

        return st(root.left, subnode) || st(root.right, subnode);

    }

    private static boolean checkEquality(Node root, Node subnode) {

        if(root == null && subnode == null){
            return true;
        }
        if( root == null || subnode == null)
            return false;

        if(subnode.value != root.value)
            return false;

            return  checkEquality(root.left, subnode.left) &&
                    checkEquality(root.right, subnode.right);
    }
}
