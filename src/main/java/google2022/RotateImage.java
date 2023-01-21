package google2022;

public class RotateImage {

    public static void main(String[] args) {

        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        // 147
//         258
  //       369
        /*
        741
        852
        963
        * */


        for(int i =0; i< matrix.length; i++) {
            for(int j=0; j<matrix.length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

        for(int i =0; i< matrix.length; i++) {
            for(int j=i; j<matrix.length; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }
        for(int i =0; i< matrix.length; i++) {
            for(int j=0; j<matrix.length/2; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1];
                matrix[i][matrix.length-1] = t;
            }
        }

        for(int i =0; i< matrix.length; i++) {
            for(int j=0; j<matrix.length; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

    }
}
