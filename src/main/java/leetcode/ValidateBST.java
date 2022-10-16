package leetcode;

class Suple {

    Integer val;

    public Suple() {

    }
    public Suple(int val) {
        this.val = val;
    }
}

public class ValidateBST {

    public static void main(String[] args) {

        Node root = new Node(5);
        root.left = new Node(3);
        root.right = new Node(8);
        root.left.left = new Node(2);
        root.left.right = new Node(6);
        root.right.left = new Node(6);
        root.right.right = new Node(11);

        Boolean result = true;
        Suple s = null;
        System.out.println(validate(root, new Suple(), result));
    }

    //                            5
    //                 3                    8
    //
    //            2           4       6           11
    //
    //                 3          7

    static boolean validate(Node root, Suple lastval, Boolean result){
        System.out.println(lastval);
        if (root == null)
            return result && true;

        validate(root.left, lastval, result);
        if(lastval.val == null)
            lastval.val = root.value;
        else {
            if( root.value < lastval.val)
                return result && false;
            else {
                lastval.val = root.value;
            }
        }
        validate(root.right, lastval, result);
        return result && true;
    }
}
