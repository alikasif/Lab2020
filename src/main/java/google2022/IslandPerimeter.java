package google2022;

public class IslandPerimeter {

    public static void main(String[] args) {

        int[][] grid = new int[][] {
            {0,1,0,0},
            {0,1,0,0},
            {0,0,0,0},
            {0,0,0,0}
        };

        IslandPerimeter islandPerimeter = new IslandPerimeter();

        System.out.println(islandPerimeter.islandPerimeter(grid));

    }

    int count=0;

    public int islandPerimeter(int[][] grid) {

        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++) {
                dfs(grid, i,j);
            }
        }
        return count;
    }

    boolean dfs(int[][] grid, int i, int j){


        if(i<0 || i>=grid.length || j<0 || j>=grid[i].length || grid[i][j]==0){
            return false;
        }

        if(grid[i][j]==2) return true;

        grid[i][j]=2;

        count+=4;
        if(dfs(grid, i-1, j)) count--;
        if(dfs(grid, i, j-1)) count--;
        if(dfs(grid, i+1, j)) count--;
        if(dfs(grid, i, j+1)) count--;

        return true;
    }
}
