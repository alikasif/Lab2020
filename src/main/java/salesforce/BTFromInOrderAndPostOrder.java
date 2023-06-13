package salesforce;

import leetcode.Node;

import java.util.Arrays;

public class BTFromInOrderAndPostOrder {

    public static void main(String[] args) {

        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        Node tree = createTree(inorder, postorder);
        inOrder(tree);
    }

    static Node createTree(int[] in, int[] post) {

        System.out.println(Arrays.toString(in) +" "+ Arrays.toString(post));

        if(post == null || post.length==0 || in== null || in.length==0)
            return null;

        int val = post[post.length-1];
        int index = findIndex(in, val);

        if(index == -1)
            return null;

        Node root = new Node(val);

        post = Arrays.copyOfRange(post,0, post.length-1);

        int[] rightInorder = Arrays.copyOfRange(in, index + 1, in.length);
        int[] leftInorder = Arrays.copyOfRange(in, 0, index);

        int[] leftPostOrder = Arrays.copyOfRange(post, 0, leftInorder.length);

        int[] rightPostOrder = Arrays.copyOfRange(post, leftInorder.length, post.length);

        root.left = createTree(
                leftInorder, leftPostOrder);

        root.right = createTree(
                rightInorder,
                rightPostOrder);

        return root;
    }

    private static int findIndex(int[] in, int i) {
        for(int j=0; j<in.length; j++)
            if(in[j] == i)
                return j;
        return -1;
    }

    static void inOrder(Node root) {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);
    }
}