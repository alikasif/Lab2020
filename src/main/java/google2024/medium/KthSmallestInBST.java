package google2024.medium;

import leetcode.Node;

public class KthSmallestInBST {

    static int x=0;
    static Integer smallest = null;

    static void smallestK(Node root, int k) {

        //System.out.println(root + " | "+smallest);
        if(root == null)
            return;

        smallestK(root.left, k);
        //System.out.println("x: "+x + " val: "+root.value);
        x++;
        if( k==x) {
            smallest = root.value;
          //  System.out.println(smallest);
            return;
        }
        smallestK(root.right, k);
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(1);
        root.left.right = new Node(2);
        root.right = new Node(4);
        System.out.println(smallest);
        smallestK(root, 3);
        System.out.println(smallest);
    }
}
