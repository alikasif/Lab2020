package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortedList2BST {

    public static void main(String[] args) {

        int[] arr = {-10, -3, 0, 5, 9};
        System.out.println(Arrays.toString(Arrays.copyOfRange(arr, 0, 2)));
        List<Node> roots = new ArrayList<>();
        Node root = createBST(arr);
        inOrder(root);
        // 1 2 3
    }

    static Node createBST(int[] arr) {

        if ( arr.length <= 1) {
            if ( arr.length == 0)
                return null;
            Node  n = new Node(arr[0]);
            return n;
        }
        else {
            int m = arr.length / 2;
            Node root = new Node(arr[m]);
            root.left = createBST(Arrays.copyOfRange(arr, 0, m));
            root.right = createBST(Arrays.copyOfRange(arr, m + 1, arr.length));
            return root;
        }
    }

    static void inOrder(Node root) {
        if ( root == null)
            return;
        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);
    }

}
