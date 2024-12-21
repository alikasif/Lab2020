package google2024.hard;

import java.util.*;

class WPair {

}
public class WordSearch {
    public static void main(String[] args) {

        String[][] mat =
                {
                {"A","B","C","E"},
                {"S","F","C","S"},
                {"A","D","E","E"}
        };

        String word = "ABCCED";

        int maxr = mat.length;
        int maxc = mat[0].length;

        Stack<int[]> stack = new Stack<>();

        Set<String> visited = new HashSet<>();

        for(int i=0; i<mat.length; i++) {
            for(int j=0; j<mat[0].length; j++) {

                String key = mat[i][j]+i+j;

                if(!word.contains(mat[i][j])) {
                    visited.add(key);
                    continue;
                }

                if(visited.contains(key))
                    continue;

                stack.add(new int[] {i, j});
                while (!stack.isEmpty()) {

                    int[] pop = stack.pop();
                    int r = pop[0];
                    int c = pop[1];
                    System.out.println(mat[r][c] + r + c);

                    visited.add(mat[r][c]+r+c);

                    if(r+1 < maxr && word.contains(mat[r+1][c]) && !visited.contains(mat[r+1][c]+(r+1)+c)) {
                        stack.push(new int[] {r+1, c});
                        visited.add(mat[r+1][c]+(r+1)+c);
                    }

                    if(r-1 >= 0  && word.contains(mat[r-1][c])  && !visited.contains(mat[r-1][c]+(r-1)+c) ) {
                        stack.push(new int[] {r-1, c});
                        visited.add(mat[r-1][c]+(r-1)+c);
                    }

                    if(c+1 < maxc && word.contains(mat[r][c+1])  && !visited.contains(mat[r][c+1]+(r)+(c+1)) ) {
                        stack.push(new int[] {r, c+1});
                        visited.add(mat[r][c+1]+(r)+(c+1));
                    }

                    if(c-1 >=0 && word.contains(mat[r][c-1])  && !visited.contains(mat[r][c-1]+(r)+(c-1)) ) {
                        stack.push(new int[] {r, c-1});
                        visited.add(mat[r][c-1]+(r)+(c-1));
                    }
                }
            }
        }
    }
}
