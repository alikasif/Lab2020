package salesforce;

import leetcode.Node;

import java.util.Arrays;

public class BSTFromPreOrderList {

    public static void main(String[] args) {
        int[] arr = {8, 5, 1, 7, 10, 12};

        Node root = createBST(arr);
        inOrder(root);

    }

    static void inOrder(Node root) {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);
    }

    static Node createBST(int[] arr) {

        if (arr == null || arr.length == 0)
            return null;

        Node root = new Node(arr[0]);

        if(arr.length == 1)
            return root;

        int i = 1;
        while (arr[i] < arr[0])
            i++;

        root.left = createBST(Arrays.copyOfRange(arr,1, i));
        root.right = createBST(Arrays.copyOfRange(arr,i, arr.length));
        return root;
    }
}