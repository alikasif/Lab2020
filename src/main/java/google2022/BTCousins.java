package google2022;

import leetcode.Node;

import java.util.ArrayList;
import java.util.List;

public class BTCousins {
    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.left.right = new Node(4);
        root.right = new Node(3);
        root.right.right = new Node(5);

        List<Node> queue = new ArrayList<>();
        queue.add(root);

        int x=2, y=3;
        boolean fx=false, fy=false;

        while (!queue.isEmpty()) {

            fx = false;
            fy = false;
            List<Node> tmp = new ArrayList<>();

            for(Node t : queue) {
                if(t.value == x)
                    fx = true;
                if(t.value == y)
                    fy = true;
                if(t.left != null && t.right != null){
                    if(t.left.value == x && t.right.value == y) {
                        System.out.println("sibling");
                        return;
                    }
                    else if (t.left.value == y && t.right.value == x) {
                        System.out.println("sibling");
                        return;
                    }
                }
                if(t.left != null) {
                    tmp.add(t.left);
                }
                if(t.right != null)
                    tmp.add(t.right);

                if(fx && fy)
                {
                    System.out.println("found cosuin");
                    return;
                }
            }
            queue.clear();
            queue.addAll(tmp);
        }
    }
}
