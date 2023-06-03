package salesforce;

import leetcode.Node;

public class Sort2BST {

    public static void main(String[] args) {
        Node root1 = new Node(2);
        root1.left = new Node(1);
        root1.right = new Node(4);

        Node root2 = new Node(3);
        root2.left = new Node(0);
        root2.right = new Node(5);
        root2.right.left = new Node(4);
        bst(root1, root2);
    }

    static void bst(Node root1, Node root2) {
        if(root1 == null && root2 == null)
            return;

        bst(root1 == null? null: root1.left, root2 == null? null: root2.left);

        if(root1 == null)
            System.out.print(root2.value+",");
        else if(root2 == null)
            System.out.print(root1.value+",");
        else if(root1.value < root2.value)
            System.out.print(root1.value +","+ root2.value+",");
        else
            System.out.print(root2.value+","+root1.value+",");

        bst(root1 == null? null: root1.right, root2 == null? null: root2.right);
    }
}
