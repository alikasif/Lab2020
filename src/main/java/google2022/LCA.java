package google2022;

import leetcode.Node;

public class LCA {

    public static void main(String[] args) {

        Node root = new Node(6);
        root.left = new Node(2);
        root.right = new Node(8);
        root.left.left = new Node(0);
        root.left.right = new Node(4);
        root.left.right.left = new Node(3);
        root.left.right.right = new Node(5);

        root.right.left = new Node(7);
        root.right.right = new Node(9);

        System.out.println(lca(root,2,4));
    }

    static Node lca(Node root, int p, int q) {

        if(root == null)
            return null;

        if( root.value == p || root.value==q)
            return root;

        Node l = lca(root.left, p, q);
        Node r = lca(root.right, p, q);

        if(l == null && r == null)
            return null;
        else if(l==null && r!=null)
            return r;
        else if (l != null && r == null)
            return l;
        else if( l.value != null && r.value!=null)
            return root;
        else
            return null;
    }
}
