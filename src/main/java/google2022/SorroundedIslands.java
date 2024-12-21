package google2022;

import java.util.Arrays;
import java.util.Stack;

public class SorroundedIslands {
    public static void main(String[] args) {

        int[][] mat ={
                {1,1,1,1,1},
                {1,0,0,0,1},
                {1,0,1,0,1},
                {1,0,1,1,1}};

        int i=0;
        for(int j=0;j<mat.length;j++) {
            if(mat[i][j] == 0) {
                DFS(mat, i, j, -1);
            }
            if (mat[mat.length-1-i][j] == 0) {
                DFS(mat, mat.length - 1-i, j, -1);
            }
        }
        for(int j=0;j<mat.length;j++) {
            System.out.println(Arrays.toString(mat[j]));
        }
    }
    // move over boundary and mark 0 as -1

    static void DFS(int[][]mat, int i, int j, int v) {
        Stack<Pair2> stack = new Stack<>();
        stack.add(new Pair2(i, j));
        while (!stack.isEmpty()) {

            Pair2 tmp = stack.pop();
            mat[tmp.i][tmp.j] = v;

            if(tmp.i-1 >=0 && mat[tmp.i-1][tmp.j] == 0) {
                stack.push(new Pair2(tmp.i - 1, tmp.j));
            }

            if(tmp.i+1 < mat.length && mat[tmp.i+1][tmp.j] == 0)
                stack.push(new Pair2(tmp.i+1, tmp.j));

            if(tmp.j-1 >=0 && mat[tmp.i][tmp.j-1] ==0)
                stack.push(new Pair2(tmp.i, tmp.j-1));

            if(tmp.j+1 < mat.length && mat[tmp.i][tmp.j+1] ==0)
                stack.push(new Pair2(tmp.i, tmp.j+1));
        }
    }
}
