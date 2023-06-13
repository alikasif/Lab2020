package salesforce;

import leetcode.Node;

import java.util.*;

public class BTInfection {
    public static void main(String[] args) {
        Node root = new Node(1);

        root.left = new Node(5);
        root.left.right = new Node(4);
        root.left.right.left = new Node(9);
        root.left.right.right = new Node(2);

        root.right = new Node(3);
        root.right.left = new Node(10);
        root.right.right = new Node(6);
        findPath(root, 10, new ArrayList<>());

        Map<Integer, List<Integer>> map = new HashMap<>();
        createAdjList(root, null, map);
        System.out.println(map);
        findTime(3, map);
    }

    static void findTime(Integer value, Map<Integer, List<Integer>> map) {

        List<Integer> list  = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int time = 0;
        list.add(value);
        visited.add(value);

        while (!list.isEmpty()) {
            Integer integer = list.remove(0);
            List<Integer> integers = map.get(integer);
            List<Integer> tmp = new ArrayList<>();
            for(Integer k : integers) {
                if(!visited.contains(k))
                    tmp.add(k);
            }
            if(!tmp.isEmpty()) {
                time++;
                visited.addAll(tmp);
                list.addAll(tmp);
            }
        }
        System.out.println(time);
    }

    private static void createAdjList(Node root, Node parent,  Map<Integer, List<Integer>> map) {
        if(root == null)
            return;

        List<Integer> adjNodes = map.getOrDefault(root.value, new ArrayList<>());

        if(parent != null)
            adjNodes.add(parent.value);

        if(root.left != null)
            adjNodes.add(root.left.value);

        if(root.right != null)
            adjNodes.add(root.right.value);
        map.put(root.value, adjNodes);

        createAdjList(root.left, root, map);
        createAdjList(root.right, root, map);
    }

    static void findPath(Node root, int value, List<Node> path) {
        if(root == null)
            return;
        if(root.value == value) {
            path.add(root);
            System.out.println(path);
        }

        List<Node> nodes = new ArrayList<>(path);
        nodes.add(root);
        findPath(root.left, value, nodes);
        findPath(root.right, value, nodes);
    }
}
