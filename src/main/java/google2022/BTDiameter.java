package google2022;

import leetcode.Node;

import java.util.concurrent.atomic.AtomicInteger;

public class BTDiameter {

    static  int max =0;
    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);


        root.left.left = new Node(4);
        root.left.right = new Node(5);

//      root.left.right.right = new Node(6);

        //System.out.println(root2leaflength(root, 0));
        maxDepth(root);
        System.out.println(max);

    }

    static int root2leaflength(Node root, int length) {

        if( root == null )
            return length;

        return Math.max( root2leaflength(root.left, length++), root2leaflength(root.right, length++));
    }

    static int maxDepth(Node root) {

        if(root == null)
            return 0;

        int l = maxDepth(root.left);
        int r = maxDepth(root.right);

        max = Math.max(max, l+r);

        return Math.max(l, r)+1;
    }
}
