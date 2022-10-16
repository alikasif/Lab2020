package leetcode;

import java.util.*;

class Tuple1 {
    int i; int j;

    public Tuple1(int ii, int jj){
        this.i= ii;
         this.j = jj;
    }
}

public class ConnectedIslands {

    public static void main(String[] args) {

        int mat[][] = {
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 1}
        };

        List<String> stack = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        for(int i = 0; i<mat.length; i++) {
            for (int j=0; j< mat[i].length; j++) {
                if(mat[i][j] == 1 && !visited.contains(i+"|"+j)) {
                    stack.add(i+"|"+j);
                }
                if(!stack.isEmpty())
                    System.out.println(" island : \n");
                while (!stack.isEmpty()) {
                    String tmp = stack.get(stack.size()-1);
                    stack.remove(tmp);
                    visited.add(tmp);
                    System.out.println(tmp+""+ Arrays.toString(tmp.split("|")));
                    int r = Integer.valueOf(tmp.split("|")[0]);
                    int c = Integer.valueOf(tmp.split("|")[2]);

                    if(c+1 < mat[r].length && mat[r][c+1] == 1 && !visited.contains(r+"|"+(c+1)))
                        stack.add(r+"|"+(c+1));

                    if(c-1 >= 0 && mat[r][c-1] == 1 && !visited.contains(r+"|"+(c-1)))
                        stack.add(r+"|"+(c-1));

                    if(r+1 < mat.length && mat[r+1][c] == 1 && !visited.contains(r+1+"|"+(c)))
                        stack.add((r+1)+"|"+(c));

                    if(r-1 >= 0 && mat[r-1][c] == 1 && !visited.contains((r-1)+"|"+(c)))
                        stack.add((r-1)+"|"+(c));
                }
            }
        }
    }
}
