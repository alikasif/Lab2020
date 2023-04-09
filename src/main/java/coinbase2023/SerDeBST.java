package coinbase2023;

import leetcode.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class SerDeBST {

    public static void main(String[] args) {

        Node root = new Node(5);
        root.left = new Node(2);
        root.left.right = new Node(3);
        root.right = new Node(7);
    }

    static void serialize(Node root, StringBuilder sbl) {
        if(root == null)
            return;
        sbl.append(root.value).append(",");
        serialize(root.left, sbl);
        serialize(root.right, sbl);
    }

    static Node deserialize(List<String> queue, int lower, int high) {

        if (queue.isEmpty())
            return null;

        String s = queue.get(0);
        Node tmp = new Node(Integer.parseInt(s));

        if(Integer.parseInt(s) < lower || Integer.parseInt(s) > high)
            return null;
        queue.remove(0);

        tmp.left = deserialize(queue, lower, Integer.parseInt(s));
        tmp.right = deserialize(queue, Integer.parseInt(s), high);
        return tmp;
    }
}
