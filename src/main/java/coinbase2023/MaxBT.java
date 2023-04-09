package coinbase2023;

import leetcode.Node;

import java.util.Arrays;

public class MaxBT {
    public static void main(String[] args) {

        int[] arr = {3,2,1,6,0,5};
        Node root = buildTree(arr);
        System.out.println( root +" "+ root.left +" "+root.right);
        System.out.println(root.left.left +" "+root.left.right);

    }

    static Node buildTree(int[] arr) {
        if(arr == null || arr.length ==0)
            return null;

        int i = getMaxElementIndex(arr);

        Node tmp = new Node(arr[i]);
        tmp.left = buildTree(Arrays.copyOfRange(arr, 0, i));
        tmp.right = buildTree(Arrays.copyOfRange(arr, i+1, arr.length));
        return tmp;
    }

    private static int getMaxElementIndex(int[] arr) {
        int i =0;
        for(int j =0; j< arr.length; j++) {
            if(arr[j] > arr[i]){
                i = j;
            }
        }
        return i;
    }
}
