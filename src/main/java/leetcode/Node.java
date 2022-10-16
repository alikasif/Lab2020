package leetcode;

public class Node {

    public Node next;
    public Node prev;
    public Node child;
    public Integer value;
    public String sval;
    public Node left;
    public Node right;

    public Node(int x){
        this.value = x;
    }

    public Node(String x){
        this.sval = x;
    }

    @Override
    public String toString() {
        String s= "";
        return value+"";

        /*if(value != null)
         s= s+ "Node {" +  " value=" + value ;

        if(sval != null)
            s=s+" sval="+sval;

        s= s+" }";
        return s;*/
    }
}
