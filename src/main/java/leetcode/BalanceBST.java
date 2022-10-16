package leetcode;

import java.util.Arrays;


// 1 2 3 4 5 6
// 123   456
/*
                    4
               2        5
           1      3         6
 */

public class BalanceBST {

    public static void main(String[] args) {

        Node balance = balance(new int[]{1, 2, 3, 4, 5, 6}, null);
        System.out.println(balance +" "+ balance.left+" "+balance.left.left+" "+balance.left.right);
        System.out.println(balance +" "+ balance.right+" "+balance.right.left+
                " "+balance.right.right);
    }

    public static Node balance(int[] elements, Node root) {

        System.out.println(Arrays.toString(elements));

        if(elements == null || elements.length  == 0)
            return null;
        else {
            root = new Node(elements[elements.length / 2]);

            root.left = balance(Arrays.copyOfRange(elements, 0, elements.length / 2), root);

            root.right = balance(Arrays.copyOfRange(elements, (elements.length / 2) + 1, elements.length), root);

            return root;
        }
    }
}