package fb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BTLeftView {

    public static void main(String[] args) {

        /*Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left.right = new Node(8);
*/
        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.left.left = new Node(5);
        root.left.right = new Node(3);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);
        root.right.right = new Node(25);

       // topView(root);
        bottomView(root);
    }

    static void bottomView(Node root) {

        if (root == null)
            return;

        if (root.left != null) {
            if (root.left.right != null) {
                bottomView(root.left.right);
                bottomView(root.left);
            }
            else {
                System.out.println(root);
                bottomView(root.left);
            }
        }

        if (root.right != null) {
            if (root.right.left != null) {
                bottomView(root.right.left);
                bottomView(root.right);
            }
            else {
                System.out.println(root);
                bottomView(root.right);
            }
        }
    }

    static void topView(Node root) {

        List<Node> queue = new ArrayList<>();
        queue.add(root);
        Map<Node, Integer> topMap = new HashMap<>();

        while ( !queue.isEmpty() ) {

            Node n = queue.get(0);
            queue.remove(n);
            Integer t = topMap.get(n);

            if( t == null)
                System.out.println(n);

            if (n.left != null && n.left.right != null) {
                  topMap.put(n.left.right, 1);
            }
            if (n.right != null && n.right.left != null) {
                topMap.put(n.right.left, 1);
            }

            if(n.left != null)
                queue.add(n.left);
            if(n.right != null)
                queue.add(n.right);
        }
    }

    static void leftView(Node root) {
        List<Node> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            List<Node> tmp = new ArrayList<>();
            System.out.println();

            while (!queue.isEmpty()) {
                Node n = queue.get(0);
                queue.remove(n);
                System.out.print(n +" -> ");
                if(n.left != null)
                    tmp.add(n.left);
                if (n.right != null)
                    tmp.add(n.right);
            }
            queue = tmp;
        }
    }

    static void rightView(Node root) {

        List<Node> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            List<Node> tmp = new ArrayList<>();
            System.out.println();

            while (!queue.isEmpty()) {
                Node n = queue.get(0);
                queue.remove(n);
                System.out.print(n + " -> ");
                if (n.right != null)
                    tmp.add(n.right);
                if (n.left != null)
                    tmp.add(n.left);
            }
            queue = tmp;
        }
    }
}
