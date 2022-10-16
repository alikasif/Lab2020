package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestorBT {

//                    3
//                5             1
//          6         2     0       8
//                7      4

    public static void main(String[] args) {

        /*Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.right.left = new Node(0);
        root.right.right = new Node(8);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);
*/
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


        //System.out.println(height(root));
        System.out.println(lca3(root, null,2, 11));
    }

    static Node lca3(Node root, Node parent, int x, int y) {

        if(root == null)
            return null;

        if(root.value == x || root.value == y) {
            System.out.println(" lca "+ root);
            return root;
        }
        else {

            Node llca = lca3(root.left, root, x, y);
            Node rlca = lca3(root.right, root, x, y);

            if (llca != null && rlca != null) {
                return root;
            } else
                return (llca != null) ? llca : rlca;
        }
    }

    static Node lca2(Node root) {

        int lh = height(root.left);
        int rh= height(root.right);

        if( lh == rh) {
            return root;
        }
        else {
            if(lh > rh)
                return lca2(root.left);
            else
                return lca2(root.right);
        }
    }

    static int height(Node root) {
        if(root == null)
            return 0;
        else
            return Math.max(1+height(root.left), 1+height(root.right));
    }


    static void lca1(){
        Integer[] nodes = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};

        // 0  -> 1, 2
        // 1 -> 3, 4
        // 2 -> 5, 6
        // 3 -> 7, 8
        // 4 -> 9, 10
        // 5 -> 11, 12

        int i = 0;
        int offset = 1;
        List<Integer> leaves = new ArrayList<>();

        while( i < nodes.length ) {

            if(nodes[i] == null) {
                i++;
                continue;
            }

            if( ( i+offset < nodes.length && nodes[i+offset] == null )
                    && ( i+offset+1 < nodes.length && nodes[i+offset+1] == null) ) {
                leaves.add(i);
            }

            else if( i+offset >= nodes.length ) {
                leaves.add(i);
            }
            offset++;
            i++;
        }
        System.out.println(leaves);

    }

}
