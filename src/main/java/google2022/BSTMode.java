package google2022;

import leetcode.Node;

class mode {

    int e;
    int c;
    int maxc;
    int maxe;

    public mode(int e, int c) {
        this.e = e;
        this.c = c;
        maxc =c;
        maxe=e;
    }
}

public class BSTMode {
    public static void main(String[] args) {

        Node root = new Node(1);
        root.right = new Node(2);
        root.right.left = new Node(2);
        root.left = new Node(1);
        root.left.left = new Node(1);

        mode m = new mode(root.value,1);
        inorder(root, m, null);
        System.out.println(m.maxc+" "+m.maxe);
    }

    static void inorder(Node root, mode m, Node prev) {

        if(root == null)
            return;

        inorder(root.left, m, root);

        if(prev != null) {

            if (prev.value == root.value) {
                m.c = m.c+1;
            }
            else{
               m.c = m.c-1;
            }

            if(m.c > m.maxc) {
                m.maxc = m.c;
                m.maxe = m.e;
            }

            if(m.c==0){
                m.e = root.value;
                m.c=1;
            }
        }

        inorder(root.right, m, root);
    }
}