package google2022;

import leetcode.Node;
import java.util.Arrays;

public class TreeFromPreInOrder {

    public static void main(String[] args) {

        int [] pre = {3,9,20,15,7};
        int [] in = {9,3,15,20,7};

        // root l r
        // l root r
        Node root = makeBT(pre, in);
        System.out.println(root +" "+ root.left + " "+ root.right +" "+ root.right.left+" "+ root.right.right);
    }

    static Node makeBT(int[] pre, int[] in) {

        System.out.println(Arrays.toString(pre) +"  " + Arrays.toString(in));

        if(in == null || in.length == 0)
            return null;

        Node root = new Node(pre[0]);

        root.left = makeBT(Arrays.copyOfRange(pre,1, pre.length), getLeftSubArray(in, pre[0]));

        if(pre.length>=2)
            root.right = makeBT(Arrays.copyOfRange(pre,2, pre.length), getRightSubArray(in, pre[0]));

        return root;
    }

    private static int[] getRightSubArray(int[] in, int x) {
        for(int i=0; i<in.length; i++) {
            if(in[i] == x) {
                return Arrays.copyOfRange(in, i+1, in.length);
            }
        }
        return null;
    }

    private static int[] getLeftSubArray(int[] in, int x) {
        for(int i=0; i<in.length; i++) {
            if(in[i] == x) {
                return Arrays.copyOfRange(in, 0, i);
            }
        }
        return null;
    }
}