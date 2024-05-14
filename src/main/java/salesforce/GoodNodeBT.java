package salesforce;

import leetcode.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GoodNodeBT {
    public static void main(String[] args) {

        Node root = new Node(3);
        root.left = new Node(1);
        root.left.left = new Node(3);
        root.right = new Node(4);
        root.right.left = new Node(1);
        root.right.right = new Node(5);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        findGoodNodes(root, pq);
    }

    private static void findGoodNodes(Node root, PriorityQueue<Integer> pq ) {

        if(root == null) return;

        if(pq.isEmpty())
            pq.add(root.value);
        else {
            if(root.value > pq.peek()) {
                System.out.println(root);
                pq.add(root.value);
            }
            else {
                //findGoodNodes();
            }
        }
    }
}