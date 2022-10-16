package google2022;

import leetcode.Node;

public class HeightBalancedBT {

    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(2);
        root.left.left = new Node(3);
        root.right.right = new Node(3);
        //root.right.right.right = new Node(71);

        int hdiff = isHBalanced(root);
        System.out.println(1+isHBalanced(root.left));
        System.out.println(1+ isHBalanced(root.right));

    }

    static int isHBalanced(Node root) {

        if(root == null)
            return 0;

        return ( Math.max( (1+isHBalanced(root.left)) ,(1+isHBalanced(root.right)) ) );
    }
}
