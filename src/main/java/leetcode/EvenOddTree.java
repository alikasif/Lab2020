package leetcode;

import java.util.ArrayList;
import java.util.List;

public class EvenOddTree {

    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(10);
        root.right = new Node(4);
        root.left.left = new Node(3);
        root.left.left.left = new Node(12);
        root.left.left.right = new Node(8);

        root.right.left = new Node(2); //7
        root.right.left.left = new Node(6);
        root.right.right = new Node(9);
        root.right.right.right = new Node(2);

        System.out.println(levelOrder(root));
    }

    public static boolean levelOrder(Node root) {

        List<Node> queue = new ArrayList<>();
        queue.add(root);

        boolean hasElements = true;
        boolean isOdd = false;

        while(hasElements) {

            List<Node> tq = new ArrayList<>();
            while(!queue.isEmpty()) {
                Node tmp = queue.get(0);
                System.out.println(tmp);
                queue.remove(tmp);

                if (tmp.left != null) {
                    if (isOdd) { // shud be increasing order
                        if(!tq.isEmpty()) {
                            if(tq.get(tq.size()-1).value <= tmp.left.value)
                                tq.add(tmp.left);
                            else
                                return false;
                        }
                        else
                            tq.add(tmp.left);
                    }
                    else {
                        if(!tq.isEmpty()) {
                            if(tq.get(tq.size()-1).value >= tmp.left.value)
                                tq.add(tmp.left);
                            else
                                return false;
                        }
                        else
                            tq.add(tmp.left);
                    }
                }

                if (tmp.right != null) {
                    if (isOdd) { // shud be increasing order
                        if(!tq.isEmpty()) {
                            if(tq.get(tq.size()-1).value <= tmp.right.value)
                                tq.add(tmp.right);
                            else
                                return false;
                        }
                        else
                            tq.add(tmp.right);
                    }
                    else {
                        if(!tq.isEmpty()) {
                            if(tq.get(tq.size()-1).value >= tmp.right.value)
                                tq.add(tmp.right);
                            else
                                return false;
                        }
                        else
                            tq.add(tmp.right);
                    }
                }
            }
            isOdd = isOdd?false:true;

            if(!tq.isEmpty()){
                queue.addAll(tq);
            }
            else
                hasElements = false;
        }
        return true;
    }
}
