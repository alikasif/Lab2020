package leetcode;

public class BSTLCA {

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
        System.out.println();
        preOrder(root);
        System.out.println();

//        Node lca = LCA(root, 11, 2);
//        System.out.println("\nlca => "+ lca.value);

//        System.out.println(" LCA2 => "+ LCA2(root, null, 11, 2));
    }

    static Node LCA(Node root, int x, int y) {

        while (root.value < x || root.value > y) {

            while (root.value < x) {
                root = root.right; // LCA must be in p’s right child.
            }

            while (root.value > y) {
                root = root.left; // LCA must be in p’s left child.
            }
        }
        return root;
    }

    static Node LCA2(Node root, Node parent, int x, int y) {

        while (root != null){
            if(root.value < x && root.value < y){
                root = root.right;
            }
            else if(root.value > x && root.value > y){
                root = root.left;
            }
            else
                break;
        }
        return root;
    }


    static void inOrder(Node root) {
        if(root == null)
            return;
        inOrder(root.left);
        System.out.print(root +" ");
        inOrder(root.right);
    }

    static void preOrder(Node root) {
        if(root == null)
            return;
        System.out.print(root +" ");
        preOrder(root.left);
        preOrder(root.right);
    }
}
