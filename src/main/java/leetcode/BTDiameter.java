package leetcode;

public class BTDiameter {

    public static void main(String[] args) {

        Node root = new Node(3);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(6);
        root.left.right = new Node(2);
        root.right.left = new Node(0);
        root.right.right = new Node(8);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(4);

        // find root to leaf path
        // 2 longest represent the diameter
        System.out.println(diameter(root));
        //System.out.println(height(root.right, 1));
    }

    static int diameter(Node root) {

        if(root == null)
            return 0;

        int hl = height(root.left,1);
        int rh = height(root.right, 1);

        return Math.max(hl+rh, Math.max(diameter(root.left), diameter(root.right)));
    }

    static int height(Node root, int h) {

        if(root == null)
            return h;

        if(root.left == null && root.right == null) {
            return h+1;
        }
        else
            return Math.max(height(root.left, h+1), height(root.right, h+1));
    }
}