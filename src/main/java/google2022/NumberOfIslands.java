package google2022;

import java.util.Arrays;
import java.util.Stack;

class Pair2 {
    int i,j;

    public Pair2(int x, int y) {
        i =x;
        j =y;
    }
}
public class NumberOfIslands {

    public static void main(String[] args) {

        int mat[][]= {
                {1,1,0,0,0},
                {1,1,0,0,1},
                {0,0,1,0,0},
                {1,0,0,1,1}
                };
        
        int i=0,j=0;
        int mi=0, mj=0;
        Stack<Pair2> stack = new Stack<>();
        stack.push(new Pair2(i, j));

        while (!stack.isEmpty() ) {

            Pair2 p = stack.pop();
            if (p.i > mi)
                mi=p.i;
            if(p.j>mj)
                mj = p.j;

            if (mat[p.i][p.j] == 1) {
                mat[p.i][p.j] = 2;

                if(p.i+1 < mat.length && mat[p.i+1][p.j] == 1)
                    stack.push(new Pair2(p.i+1, p.j));

                if(p.i-1 >= 0  && mat[p.i-1][p.j] == 1)
                    stack.push(new Pair2(p.i-1, p.j));

                if(p.j+1 < mat.length && mat[p.i][p.j+1] == 1)
                    stack.push(new Pair2(p.i, p.j+1));

                if(p.j-1 >= 0  && mat[p.i][p.j-1] == 1)
                    stack.push(new Pair2(p.i, p.j-1));
            }
        }

        for(int x =0;x<mat.length;x++)
            System.out.println(Arrays.toString(mat[x]));

        System.out.println(mi +" " + mj);
    }
}
