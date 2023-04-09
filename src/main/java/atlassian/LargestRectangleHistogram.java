package atlassian;

public class LargestRectangleHistogram {
    public static void main(String[] args) {

        int[] arr = {3,1,4,2,2,1};

        int width = 1;
        int height = arr[0];
        int area = width * height;

        for(int i=1; i< arr.length; i++) {

            height = Math.min(height, arr[i]);
            int tarea = height * (width+1);

            if (arr[i] > tarea) {
                width = 1;
                height = arr[i];
                area = arr[i];
            }
            else if (tarea > area) {
                area = tarea;
            }
        }
    }
}
