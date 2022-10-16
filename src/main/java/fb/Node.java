package fb;

public class Node {

    int val;
    Node left;
    Node right;
    Node next;
    Node prev;

    public Node(int v) {
        val =v;
    }

    @Override
    public String toString() {
        return val+"";
    }
}
