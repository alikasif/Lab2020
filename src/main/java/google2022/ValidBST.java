package google2022;

import leetcode.Node;

import java.util.List;

public class ValidBST {
    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(4);
        root.left.left = new Node(3);
        root.right = new Node(8);
        root.right.left = new Node(2);
        root.right.right = new Node(10);
        siValid(root, new int[1]);
    }

    static void siValid(Node root, int[] arr) {
        if(root == null)
            return;
        siValid(root.left, arr);
        if (arr.length == 0)
            arr[0] = root.value;
        else {
            if(arr[0] > root.value) {
                System.out.println("invalid");
                return;
            }
            else {
                arr[0] = root.value;
            }
        }
        siValid(root.right, arr);
    }
}
