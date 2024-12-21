package google2024.medium;

import leetcode.Node;

import java.util.Arrays;

public class SortedArrayToBT {
    public static void main(String[] args) {

        int[] arr = {-10,-3,0,5,9};
        Node root = buildTree(arr);
        System.out.println(root);
        System.out.println(root.left);
        System.out.println(root.left.left);
        System.out.println(root.left.left.left);
        System.out.println(root.right);
        System.out.println(root.right.right);
        System.out.println(root.right.left);
    }

    static Node buildTree(int[] x) {
        if(x == null || x.length==0)
            return null;

        if(x.length == 1)
            return new Node(x[0]);
        int mid = x.length/2;
        Node root = new Node(x[mid]);
        root.left = buildTree(Arrays.copyOfRange(x, 0, mid));
        root.right = buildTree(Arrays.copyOfRange(x, mid+1, x.length));
        return root;
    }
}
