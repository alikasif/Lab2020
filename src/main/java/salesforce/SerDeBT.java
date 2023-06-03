package salesforce;

import leetcode.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SerDeBT {

    static StringBuilder sbl =  new StringBuilder();
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
        preOrder(root);
        System.out.println();
        serialize(root);
        System.out.println(sbl.toString());
        Node root2 = deser(new ArrayList<String>(Arrays.asList(sbl.toString().split(","))));
        preOrder(root2);

    }


    static void preOrder(Node root) {
        if(root == null)
            return;
        System.out.print(root +",");
        preOrder(root.left);
        preOrder(root.right);
    }

    /* root left right     6,  */

    static void serialize(Node root) {

        if(root == null)
            return;

        sbl.append(root).append(",");

        if(root.left == null)
            sbl.append("#").append(",");
        else
            serialize(root.left);

        if(root.right == null)
            sbl.append("#").append(",");
        else
            serialize(root.right);
    }

    static Node deser(List<String> queue) {

        String v  = queue.remove(0);

        if(v.equals("#")) {
            return null;
        }

            Node root = new Node(Integer.valueOf(v));
            root.left = deser(queue);
            root.right = deser(queue);
            return root;

    }
}