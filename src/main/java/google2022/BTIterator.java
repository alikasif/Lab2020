package google2022;

import leetcode.Node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class BTInOrderItr implements Iterator<Node> {

    Node root;
    List<Node> nodes = new LinkedList<>();
    public BTInOrderItr(Node root) {
        this.root = root;
        inOrder(root);
    }

    private void inOrder(Node tmp) {
        if(tmp == null)
            return;
        inOrder(tmp.left);
        nodes.add(tmp);
        inOrder(tmp.right);
    }

    @Override
    public boolean hasNext() {
        return !nodes.isEmpty();
    }

    @Override
    public Node next() {
        return nodes.remove(0);
    }
}

public class BTIterator {

    public static void main(String[] args) {
        Node root = new Node(7);
        root.left = new Node(3);
        root.right = new Node(15);
        root.right.left = new Node(9);
        root.right.right = new Node(20);

        Iterator<Node> itr = new BTInOrderItr(root);
        System.out.println(itr.hasNext());
        System.out.println(itr.next());
    }

}
