package salesforce;

import leetcode.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class LCA {
    static AtomicBoolean found = new AtomicBoolean(false);
    static List<Node> finalList = new ArrayList<>();
    public static void main(String[] args) {

        Node root = new Node(6);

        root.right = new Node(8);
        root.right.left = new Node(7);
        root.right.right = new Node(9);

        root.left = new Node(2);
        root.left.left = new Node(0);
        root.left.right= new Node(4);
        root.left.right.left = new Node(3);
        root.left.right.right = new Node(5);

        // inOrder(root);
        LCA(root, 0, new ArrayList<>());
        System.out.println(finalList);

        ArrayList<Node> nodes1 = new ArrayList<>(finalList);

        finalList.clear();
        found.set(false);
        LCA(root, 3, new ArrayList<>());
        System.out.println(finalList);
        ArrayList<Node> nodes2 = new ArrayList<>(finalList);

        for(int i=0; i < Math.min(nodes1.size(), nodes2.size()); i++) {

            if(nodes1.get(i).value == nodes2.get(i).value) {
                continue;
            }
            else {
                System.out.println("LCA == " + nodes1.get(i-1));
            }
        }

        System.out.println(LCA2(root, 0, 5));
        System.out.println(LCABST(root, 0, 5));

    }

    static void inOrder(Node root) {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);
    }
    static Node LCA2(Node root, int val1, int val2) {
        if(root == null || root.value == val1 || root.value == val2)
            return root;

        Node left = LCA2(root.left, val1, val2);
        Node right = LCA2(root.right, val1, val2);

        if(left == null)
            return right;
        else if(right == null)
            return left;
        return
                root;
    }

    static Node LCABST(Node root, int val1, int val2) {

        if(root == null)
            return null;

        if(val1 < root.value && val2 < root.value)
            return LCABST(root.left, val1, val2);

        if(val1 > root.value && val2 > root.value)
            return LCABST(root.right, val1, val2);

        return root;
    }

    static void LCA(Node root, int value, List<Node> list) {

        if(root == null)
             return;

        if(!found.get() && root.value == value) {
            System.out.println("found");
            list.add(root);
            finalList.addAll(list);
            found.set(true);
        }
        else {
            if(!found.get()) {
                list.add(root);
                LCA(root.left, value, new ArrayList<>(list));
                LCA(root.right, value, new ArrayList<>(list));
            }
        }
    }
}