package google2022;

import leetcode.Node;

public class SameTree {

    public static void main(String[] args) {

        Node p = new Node(1);
        p.left = new Node(2);
        p.right = new Node(3);


        Node q = new Node(1);
        q.left = new Node(2);
        q.right = new Node(3);
        boolean res = true;
        res = isSame(p, q, res);
        System.out.println(res);
    }

    private static boolean isSame(Node p, Node q, boolean result) {

        if(p == null && q == null)
            return result;

        if(p ==null && q != null)
            return false;

        if(p !=null && q == null)
            return false;

        if(p.value != q.value) {
            result = result && false;
        }
        return isSame(p.left, q.left, result) && isSame(p.right, q.right, result);
    }
}
