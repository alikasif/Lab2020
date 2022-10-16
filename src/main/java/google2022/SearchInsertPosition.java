package google2022;

public class SearchInsertPosition {

    public static void main(String[] args) {

        int[] arr = {1,3,5,6};
        int x=7;
        int i=0;

        if(x > arr[arr.length-1])
            System.out.println(arr.length);
        else {

            while (i < arr.length) {

                if (arr[i] == x) {
                    System.out.println(i);
                    break;
                } else {
                    if (arr[i] > x) {
                        System.out.println(i);
                        break;
                    }
                }
                i++;
            }
        }
    }
}
