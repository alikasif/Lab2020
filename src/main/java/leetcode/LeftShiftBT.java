package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeftShiftBT {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);
        System.out.println("before");
        inorder(root);
        System.out.println("level order");
        List<Node> queue = new ArrayList<>();
        queue.add(root);

        while (true) {
            List<Node> tmpq = new ArrayList<>();
            while (!queue.isEmpty()) {
                Node tmp = queue.get(0);
                queue.remove(tmp);
                System.out.println(tmp);

                if(queue.isEmpty()) { // root condition
                    if (tmp.right == null && tmp.left != null) {
                        tmp.right = tmp.left;
                        tmp.left = null;
                    }
                }
                else {
                    Node tmp2 = queue.get(0);
                    if(tmp.right == null && tmp.left != null) {
                        tmp.right = tmp.left;
                        tmp.left = null;
                    }
                    if ( tmp.left == null && tmp2.right != null) {
                        tmp.left = tmp2.right;
                        tmp2.right = null;
                    }
                    if(tmp2.right == null && tmp2.left != null) {
                        tmp2.right = tmp2.left;
                        tmp2.left = null;
                    }
                }

                if (tmp.right != null)
                    tmpq.add(tmp.right);
                if (tmp.left != null)
                    tmpq.add(tmp.left);
            }
            if(tmpq.isEmpty())
                break;
            else
                queue.addAll(tmpq);
        }
        System.out.println("after");
        //System.out.println(root.right.left);
        inorder(root);
    }

    static void inorder(Node root) {
        if(root == null)
            return;

        inorder(root.left);
        System.out.println(root);
        inorder(root.right);
    }
}
