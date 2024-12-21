package google2024.hard;

import java.util.Stack;

class mpair{
    int i;
    int j;
    int d;

    public mpair(int i, int j, int d) {
        this.i = i;
        this.j = j;
        this.d = d;
    }

    @Override
    public String  toString() {
        return "mpair{" +
                "i=" + i +
                ", j=" + j +
                '}';
    }
}
public class LongestIncreasingPathMatrix {
    public static void main(String[] args) {

        int[][] mat =  {
                {9,19,4},
                {6,6,8},
                {2,1,1}
        };
        dfs(mat);
    }

    static void dfs(int[][] matrix) {

        Stack<mpair> stack = new Stack<>();
        int maxCol = matrix[0].length;
        int maxRow = matrix.length;
        System.out.println(maxCol);
        System.out.println(maxRow);

        for(int i=0; i<maxRow; i++) {
            for(int j=0; j<maxRow; j++) {
                stack.push(new mpair(i,j,1));
                System.out.println("starting with "+i+"|"+j);

                while (!stack.isEmpty()) {
                    mpair pop = stack.pop();
                    System.out.println(pop);
                    boolean ispushed = false;

                    if( pop.j+1 < maxCol && matrix[pop.i][pop.j+1] > matrix[pop.i][pop.j] ) {
                        stack.push(new mpair(pop.i, pop.j+1, pop.d+1));
                        ispushed = true;
                    }

                    if( pop.j-1 >= 0 && matrix[pop.i][pop.j-1] > matrix[pop.i][pop.j]) {
                        stack.push(new mpair(pop.i, pop.j-1, pop.d+1));
                        ispushed = true;
                    }

                    if(pop.i+1 < maxRow && matrix[pop.i+1][pop.j] > matrix[pop.i][pop.j]) {
                        stack.push(new mpair(pop.i+1, pop.j, pop.d+1));
                        ispushed = true;
                    }

                    if(pop.i-1 >= 0 && matrix[pop.i-1][pop.j] > matrix[pop.i][pop.j]) {
                        stack.push(new mpair(pop.i-1, pop.j, pop.d+1));
                        ispushed = true;
                    }
                    if(!ispushed) {
                        System.out.println(pop.d);
                    }
                }
            }
        }
    }
}
