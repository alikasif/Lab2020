package leetcode;

public class SearchKeyGreaterThanNode {

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

        findK(root, 23, Integer.MAX_VALUE);
        findK2(root, 23);
    }

    static boolean findK(Node root, int k, int maxdiff) {

        if(root == null)
            return false;

//        System.out.println(root.value);

        if(root.value <= k){
            findK(root.right, k, maxdiff);
        }
        else {
            if ( root.value - k < maxdiff ) {
                System.out.println(root.value);
                maxdiff = Math.min(maxdiff, (root.value)-k);
                findK(root.left, k, maxdiff);
                findK(root.right, k, maxdiff);
            }
        }
        return false;
    }

    static boolean findK2(Node root, int k) {
        System.out.println("K2");
        int maxsofar = -1;
        while (root != null) {
            if (root.value > k) {
                maxsofar = root.value;
                root= root.left; // go left if u find a node > K. bcoz on right every  node is > K
            }
            else{
                root = root.right;
            }
        }
        System.out.println(maxsofar);
        return true;
    }
}
