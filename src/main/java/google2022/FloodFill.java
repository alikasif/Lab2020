package google2022;

import java.util.Arrays;

public class FloodFill {
    public static void main(String[] args) {

        int[][] image = {
                {0, 0, 0},
                {0, 0, 0},
//                {0, 0, 0}
        };
        int sr = 0;
        int sc = 0;
        int newColor = 2;
        int orig = image[sr][sc];

        for (int[] r : image) {
            System.out.println(Arrays.toString(r));
        }
        dfs(image, sr, sc, newColor, orig);
        for (int[] r : image) {
            System.out.println(Arrays.toString(r));
        }

    }

    static void dfs(int[][] image, int sr, int sc, int nc, int orig) {

        if (sr < 0 || sr >= image.length)
            return;
        if (sc < 0 || sc >= image[0].length)
            return;
        ;

        if (image[sr][sc] == orig) {

            image[sr][sc] = nc;
            dfs(image, sr + 1, sc, nc, orig);
            dfs(image, sr - 1, sc, nc, orig);
            dfs(image, sr, sc + 1, nc, orig);
            dfs(image, sr, sc - 1, nc, orig);

        }
    }
}
