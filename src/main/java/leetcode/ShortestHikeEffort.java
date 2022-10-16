package leetcode;

import java.util.*;

public class ShortestHikeEffort {

    static int[][] paths = new int[][] {
            {1,2,3},
            {3,8,4},
            {5,3,5}
    };

    public static void main(String[] args) {

        int n = 3;
        int m = 3;

        PriorityQueue<Edge> pq = new PriorityQueue();
        pq.add(new Edge(0,"0","0"));

        int[][] dists = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dists[i], Integer.MAX_VALUE);
        }
        dists[0][0]=0;

        while (!pq.isEmpty()) {

            Edge e = pq.poll();
            System.out.println(e);

            int r = Integer.valueOf(e.s);
            int c = Integer.valueOf(e.d);
            int d = Integer.valueOf(e.w);

            if ( r == n-1 && c == m-1) {
                System.out.println(dists[r][c]);
                System.out.println(d);
                return;
            }

            // right
            if(c+1 < m ) {
                d = Math.max(dists[r][c], Math.abs(paths[r][c] - paths[r][c+1]));
                if ( d < dists[r][c+1]) {
                    dists[r][c+1] = d;
                    pq.add(new Edge(d, r + "", (c + 1) + ""));
                }
            }
            // left
            if( c-1 >= 0 ) {
                d = Math.max(dists[r][c], Math.abs(paths[r][c] - paths[r][c-1]));
                if ( d < dists[r][c-1] ) {
                    dists[r][c - 1] = d;
                    pq.add(new Edge(d, r + "", (c - 1) + ""));
                }
            }
            // top
            if(r-1 >= 0 ) {
                d = Math.max(dists[r][c], Math.abs(paths[r][c] - paths[r-1][c]));
                if ( d < dists[r-1][c]) {
                    dists[r - 1][c] = d;
                    pq.add(new Edge(d, r - 1 + "", (c) + ""));
                }
            }
            // bottom
            if(r+1 < n ) {
                d = Math.max(dists[r][c], Math.abs(paths[r][c] - paths[r+1][c]));
                if ( d < dists[r+1][c]) {
                    dists[r + 1][c] = d;
                    pq.add(new Edge(d, r + 1 + "", (c) + ""));
                }
            }
        }
        System.out.println(dists[2][2]);
    }
}
