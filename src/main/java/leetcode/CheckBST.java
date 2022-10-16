package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class QueueNode {
    Node node;
    int max;
    int min;

    public QueueNode(Node n, int minn, int maxx){
        node = n;
        min = minn;
        max = maxx;
    }
}

public class CheckBST {

    public static void main(String[] args) {

        Node root = new Node(19);
        root.left = new Node(7);
        root.left.left = new Node(3);
        root.left.left.left = new Node(2);
        root.left.left.right = new Node(5);
        root.left.right = new Node(11);
        root.left.right.right = new Node(17);
        root.left.right.right.left = new Node(13);

        root.right = new Node(43);
        root.right.right = new Node(47);
        root.right.right.right = new Node(53);
        root.right.left = new Node(23);
        root.right.left.right = new Node(37);
        root.right.left.right.right = new Node(41);
        root.right.left.right.left = new Node(29);
        root.right.left.right.left.right = new Node(31);

        inOrder(root);
        System.out.println(checkValidBSTDFS(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
        System.out.println(checkValidBSTBFS(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    static boolean checkValidBSTDFS(Node root, int min, int max) {

        if(root == null)
            return true;
        else if (root.value < min || root.value > max)
            return false;

        return checkValidBSTDFS(root.left, min, root.value) && checkValidBSTDFS(root.right, root.value, max);
    }

    static boolean checkValidBSTBFS(Node root, int min, int max) {

        List<QueueNode> queue = new ArrayList<>();
        queue.add(new QueueNode(root, min, max));
        while (!queue.isEmpty()) {
            QueueNode tmp = queue.get(0);
            System.out.print(tmp.node.value +" ");
            queue.remove(tmp);
            if(tmp.node.value < tmp.min || tmp.node.value > tmp.max)
                return false;
            else {
                if(tmp.node.left != null)
                    queue.add(new QueueNode(tmp.node.left, tmp.min, tmp.node.value));
                if(tmp.node.right != null)
                    queue.add(new QueueNode(tmp.node.right, tmp.node.value, tmp.max));
            }
        }
        return true;
    }

    static void inOrder(Node root) {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.print(root +" ");
        inOrder(root.right);
    }
}