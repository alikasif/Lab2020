package google2022;

public class ContainerWithMaxWater {
    public static void main(String[] args) {

        int[] arr = {1,8,6,2,5,4,8,3,2};

        int l =0;
        int r = arr.length-1;
        int h = Math.min(arr[l], arr[r]);
        int water = h * (l-r);

        while (l < r) {
            while (arr[l] <= h && l<r) l++;
            while (arr[r] <= h && l<r) r--;
            h = Math.min(arr[l], arr[r]);
            water = Math.max(water, (r-l)*h);
        }
        System.out.println(water);
    }
}
