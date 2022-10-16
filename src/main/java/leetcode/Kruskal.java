package leetcode;

import java.util.*;

class Edge implements Comparable<Edge> {
    int w;
    String s;
    String d;

    public Edge(int w1, String s1, String d1) {
        w =w1;
        s = s1;
        d = d1;
    }

    @Override
    public int compareTo(Edge edge) {
        if( this.w == edge.w)
            return 0;
        if(this.w > edge.w)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return s+"->"+ d +"("+w+")";
    }
}

public class Kruskal {

    public static void main(String[] args) {

        Map<String, String> parent = new HashMap<>();
        // every node parent of itself at start
        for(int i=0;i<9; i++) {
            parent.put(""+i,""+i);
        }

        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(1, "7", "6"));
        edges.add(new Edge(2, "8", "2"));
        edges.add(new Edge(2, "6", "5"));
        edges.add(new Edge(4, "0", "1"));
        edges.add(new Edge(4, "2", "5"));
        edges.add(new Edge(6, "8", "6"));
        edges.add(new Edge(7, "2", "3"));
        edges.add(new Edge(7, "7", "8"));
        edges.add(new Edge(8, "0", "7"));
        edges.add(new Edge(8, "1", "2"));
        edges.add(new Edge(9, "3", "4"));
        edges.add(new Edge(10, "5", "4"));
        edges.add(new Edge(11, "1", "7"));
        edges.add(new Edge(14, "3", "5"));

        Collections.sort(edges);
//        System.out.println(edges);
        List<Edge> mst = new ArrayList<>();

        while (!edges.isEmpty()) {
            Edge tmp = edges.get(0);
            edges.remove(tmp);
            if(!isCycle(tmp, parent)) {
                mst.add(tmp);
                Union(tmp, parent);
            }
        }
        System.out.println(mst);
    }

    private static boolean isCycle(Edge tmp,  Map<String, String> parent ) {
        String x = Find(tmp.s, parent);
        String y = Find(tmp.d, parent);
        if(x.equals(y)) // we are able to reach y from x or x from y
            return true;
        return false;
    }

    private static String Find(String k, Map<String, String> parent)
    {
        // if `k` is root
        if (parent.get(k) != null && parent.get(k).equals(k)) {
            return k;
        }

        // recur for the parent until we find the root
        return Find(parent.get(k), parent);
    }

    // Perform Union of two subsets
    private static void Union(Edge e, Map<String, String> parent)
    {
        // find the root of the sets in which elements
        // `x` and `y` belongs
        String x = Find(e.s, parent);
        String y = Find(e.d, parent);
        parent.put(x, y);
    }
}
