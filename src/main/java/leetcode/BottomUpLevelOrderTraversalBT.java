package leetcode;

import java.util.ArrayList;
import java.util.List;

public class BottomUpLevelOrderTraversalBT {

    public static void main(String[] args) {

        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);

        List<Node> lst = new ArrayList<>();
        lst.add(root);
        List<Node> flst = new ArrayList<>();

        while( !lst.isEmpty() ) {

            Node tmp = lst.get(0);
            lst.remove(tmp);
            flst.add(tmp);

            if(tmp.left != null)
                lst.add(tmp.left);

            if (tmp.right != null)
                lst.add(tmp.right);
        }

        for(Node t : flst) {
            System.out.print(t +" => ");
        }
    }
}
