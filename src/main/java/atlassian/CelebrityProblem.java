package atlassian;

import java.util.Arrays;

public class CelebrityProblem {

    public static void main(String[] args) {

        int[][] mat = {

                {0,0,1,0},
                {0,0,1,0},
                {0,0,0,0},
                {0,0,1,0}
        };

        int[] indegree = new int [mat.length];
        int[] outdegree = new int[mat.length];

        for(int i =0; i< mat.length; i++) {
            for(int j= 0; j< mat.length; j++) {

                if( i != j) {
                    if (mat[i][j] == 1) {
                        outdegree[i]++;
                        indegree[j]++;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(indegree));
        System.out.println(Arrays.toString(outdegree));

        for(int i=0; i<mat.length; i++) {
            if (indegree[i] == mat.length-1 && outdegree[i] == 0)
                System.out.println("celebrity "+i);
        }
    }

    /*
        0 -> 2
        1 -> 2
        3 -> 2
     */
}
