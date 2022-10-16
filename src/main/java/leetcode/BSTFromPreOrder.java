package leetcode;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class BSTFromPreOrder {

    public static void main(String[] args) {

        int [] pre = {19, 7, 3, 2, 5, 11, 17, 13, 43, 23, 37, 29 ,31 ,41, 47, 53};
        //int[] pre = {5, 3, 2, 9, 7, 10};
        int[] in = Arrays.copyOf(pre, pre.length);
        Arrays.sort(in);

        Node node = buildBST(null, pre, in, new AtomicInteger(0));

//        System.out.println(node +" " +node.left +" "+node.right);
//        System.out.println(node.left +" " +node.left.left +" "+node.left.right);
//        System.out.println(node.left.left +" " +node.left.left.left +" "+node.left.left.right);

        inOrder(node);
        System.out.println();
        preOrder(node);

    }

    static Node buildBST(Node root, int[] pre, int[] in, AtomicInteger pi) {

//        System.out.println(" pre => "+Arrays.toString(pre));
//        System.out.println(" in => "+Arrays.toString(in));

        if (in.length == 0)
            return null;

        root = new Node(pre[pi.get()]);

        int i = indexOf(pre[pi.get()], in);
//        System.out.println(pi.get()+" "+pre[pi.get()] + " "+i);

        pi.incrementAndGet();

        root.left = buildBST(root, pre, Arrays.copyOfRange(in, 0, i), pi);

        root.right = buildBST(root, pre, Arrays.copyOfRange(in, i + 1, in.length), pi);

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

    private static int indexOf(int i, int[] in) {

        for(int j =0; j< in.length; j++){
            if (in[j] == i)
                return j;
        }
        return -1;
    }
}
