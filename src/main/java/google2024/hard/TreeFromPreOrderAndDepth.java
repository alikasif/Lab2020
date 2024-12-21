package google2024.hard;

import leetcode.Node;

import java.util.*;

public class TreeFromPreOrderAndDepth {
    public static void main(String[] args) {

        //String traversal = "1-2--3--4-5--6--7";
        String traversal = "1-2--3---4-5--6---7";

        Map<Integer, List<Node>> map = new HashMap<>();
        ArrayList<Node> pos = new ArrayList<>();
        pos.add(new Node(String.valueOf(traversal.charAt(0))));
        map.put(0, pos);

        int dash = 0;
        for(int i=1; i<traversal.length(); i++) {
            String s = String.valueOf(traversal.charAt(i));
            if(s.equals("-")) {
                dash++;
            }
            else {
                map.putIfAbsent(dash, new ArrayList<>());
                List<Node> nodes = map.get(dash);
                nodes.add(new Node(s));
                dash=0;
            }
        }
        System.out.println(map);

        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);

        Node parent = map.get(0).get(0);
        Node tmp = parent;

        for(int i=0; i< list.size(); i++) {
            List<Node> parents = map.get(i);
            List<Node> children = map.get(i+1);
            int c = 0;

            for(Node p : parents) {

                if (children != null && children.size() > c) {
                    p.left = children.get(c);
                    c++;
                }
                if (children != null && children.size() > c) {
                    p.right = children.get(c);
                    c++;
                }
            }
        }
        System.out.println(parent);
        System.out.println(parent.left);
        System.out.println(parent.right);

        System.out.println(parent.left.left);
        System.out.println(parent.left.left.left);
        System.out.println(parent.left.right);

        System.out.println(parent.right.left);
        System.out.println(parent.right.left.left);

    }
}
