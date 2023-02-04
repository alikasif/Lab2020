package google2022;

import leetcode.Node;

import java.util.Arrays;

public class sortedList2BST {
    public static void main(String[] args) {
        int[] arr = {-10, -3, 0, 5, 9};
        Node root = buildTree(arr);
        System.out.println(root+" "+root.left+" "+root.right +" "+ root.left.left +" "+ root.right.left +" "+ root.right.right);
    }

    static Node buildTree(int[] arr) {
        if (arr == null || arr.length ==0)
            return null;

        int m = arr.length/2;

        Node root = new Node(arr[m]);
        root.left = buildTree(Arrays.copyOfRange(arr, 0, m));
        root.right = buildTree(Arrays.copyOfRange(arr, m+1, arr.length));
        return root;
    }
}
