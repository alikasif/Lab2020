package google2022;

import leetcode.Node;

import java.util.Arrays;

public class SortedArr2BST {

    public static void main(String[] args) {

        //int[] nums = {-10, -3, 0, 5, 9};
        int[] nums = {1,2,3,4,5,6};
        Node root = makeTree(nums);
        System.out.println(root);
        System.out.println(root.left);
        System.out.println(root.left.left);
        System.out.println(root.left.right);

    }

    static Node makeTree(int[] arr) {

        if (arr.length == 0)
            return null;

        Node root = new Node(arr[arr.length/2]);

        if(arr.length>1) {
            root.left = makeTree(Arrays.copyOfRange(arr, 0, (arr.length / 2) ));
            root.right = makeTree(Arrays.copyOfRange(arr, (arr.length / 2) + 1, arr.length));
        }
        return root;
    }
}
