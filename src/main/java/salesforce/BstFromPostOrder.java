package salesforce;

import leetcode.Node;

import java.util.Arrays;

public class BstFromPostOrder {
    public static void main(String[] args) {

        int[] arr = {8, 12, 10, 16, 25, 20, 15};

        // l, r, root

        Node root = constructBST(arr);
        System.out.println(root +" "+ root.left +" "+ root.right);
        System.out.println(root.left.left +" "+ root.left.right);
        System.out.println(root.right.left +" "+ root.right.right);

    }

    static Node constructBST(int[] arr) {

        if(arr == null || arr.length ==0)
            return null;

        Node root = new Node(arr[arr.length-1]);

        int i =-1;
        for( i= arr.length-2 ;i>=0; i--) {
            if(arr[i] < root.value) {
                break;
            }
        }

        /*if (i == -1)
            return root;
        */

        root.left = constructBST(Arrays.copyOfRange(arr, 0, i+1));
        root.right = constructBST(Arrays.copyOfRange(arr, i+1, arr.length-1));

        return root;
    }

}
