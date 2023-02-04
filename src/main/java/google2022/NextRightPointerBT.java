package google2022;

import leetcode.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NextRightPointerBT {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        //root.right.left = new Node(6);
        root.right.right = new Node(7);

        List<Node> queue = new LinkedList<>();
        queue.add(root);
        Node prev = null;
        List<Node> tmpq = new LinkedList<>();
        while (!queue.isEmpty()) {
            Node remove = queue.remove(queue.size() - 1);
            System.out.println("remove "+ remove);
            remove.next = prev;
            prev = remove;
            if(remove.right != null) {
                if (!queue.isEmpty())
                    tmpq.add(queue.size() - 1, remove.right);
                else
                    tmpq.add(0, remove.right);
            }
            if(remove.left != null) {
                if(!queue.isEmpty())
                    tmpq.add(queue.size() - 1, remove.left);
                else
                    tmpq.add(0, remove.left);
            }
            if(queue.isEmpty()) {
                System.out.println(tmpq);
                queue.addAll(tmpq);
                tmpq.clear();
                prev = null;
            }
        }
        System.out.println(root.next);
        System.out.println(root.right.next);
        System.out.println(root.left.next);
        System.out.println(root.right.right.next);
        System.out.println(root.left.right.next);
    }


}
