package leetcode;

import java.util.ArrayList;
import java.util.List;

class Counter {

    public int c =0;
    public Counter(){
    }
}

public class GoodLeafPair {

    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right = new Node(3);
        root.right.right = new Node(7);
        root.right.left = new Node(6);
        getLeafDistance(root);
    }

    // find which are leaf nodes
    // find distance between such nodes

    public static void getLeafDistance(Node root) {

        List<Node> leafNodes = new ArrayList<>();
        findLeafNodes(root, leafNodes);
        System.out.println(leafNodes);

        for(int i=0;i<leafNodes.size();i++) {
            for(int j=i+1; j< leafNodes.size(); j++) {
                System.out.println("distance between "+ leafNodes.get(i) +" and "+ leafNodes.get(j));

                List<Node> nodes = new ArrayList<>();
                List<Node> result = new ArrayList<>();
                List<Node> distance1 = distance(root, leafNodes.get(i), nodes, result);
                System.out.println("dis1 : "+distance1);

                nodes = new ArrayList<>();
                result = new ArrayList<>();
                List<Node> distance2 = distance(root, leafNodes.get(j), nodes, result);
                System.out.println("dis2 : "+distance2);

                int k=0;
                while(true) {
                    System.out.println(k + " "+distance1.get(k) +" "+distance2.get(k));
                        if (distance1.get(k).value == distance2.get(k).value) {
                            k++;
                            continue;
                        }
                        break;
                }
                System.out.println(distance1 +"  "+distance2);
                System.out.println(distance1.size()-k + distance2.size()-k);
            }
        }


    }

    public static List<Node> distance(Node root, Node first, List<Node> nodes, List<Node> result) {

        if(root == null || !result.isEmpty())
            return result;

        if(root.value == first.value) {
            nodes.add(root);
            System.out.println("path : "+ nodes);
            result.addAll(nodes);
            return result;
        }
        else {
            List<Node> t1 = new ArrayList<>(nodes);
            t1.add(root);
            distance(root.left, first, t1, result);
            List<Node> t2 = new ArrayList<>(nodes);
            t2.add(root);
            distance(root.right, first, t2, result);
        }
        return result;
    }

    public static void findLeafNodes(Node root, List<Node> leafNodes) {

        if(root == null)
            return;

        if(root.left == null && root.right == null)
            leafNodes.add(root);

        findLeafNodes(root.left, leafNodes);
        findLeafNodes(root.right, leafNodes);
    }
}
