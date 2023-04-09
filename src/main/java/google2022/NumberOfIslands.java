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
                {0,1,0,0,0},
                {1,1,0,0,1},
                {0,0,1,0,0},
                {1,0,0,1,1}
                };

        int r = 4, c=5;
        int ci =0;

        for(int i =0; i<r; i++) {
            for (int j = 0; j < c; j++) {

                if (mat[i][j] == 0 || mat[i][j] == 2)
                    continue;
                System.out.println("found at: " + i +" "+ j);
                Stack<Pair2> stack = new Stack<>();
                stack.push(new Pair2(i, j));
                ci++;

                while (!stack.isEmpty()) {

                    Pair2 p = stack.pop();

                    if (mat[p.i][p.j] == 1) {
                        mat[p.i][p.j] = 2;

                        if (p.i + 1 < r && mat[p.i + 1][p.j] == 1)
                            stack.push(new Pair2(p.i + 1, p.j));

                        if (p.i - 1 >= 0 && mat[p.i - 1][p.j] == 1)
                            stack.push(new Pair2(p.i - 1, p.j));

                        if (p.j + 1 < c && mat[p.i][p.j + 1] == 1)
                            stack.push(new Pair2(p.i, p.j + 1));

                        if (p.j - 1 >= 0 && mat[p.i][p.j - 1] == 1)
                            stack.push(new Pair2(p.i, p.j - 1));
                    }
                }
            }
        }

        for(int x =0;x<mat.length;x++)
            System.out.println(Arrays.toString(mat[x]));

        System.out.println(ci);
    }
}
