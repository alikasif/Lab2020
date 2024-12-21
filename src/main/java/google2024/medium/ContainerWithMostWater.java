package google2024.medium;

public class ContainerWithMostWater {
    public static void main(String[] args) {

        //int[] height = {1,8,6,2,5,4,8,3,7};
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};

        // heighest bar with max distance will contain max water
        /// map of bar and its index
        // sort nlogn

        int l =0;
        int r = height.length-1;
        int maxVol = 0;
        while( l < r ) {

            int vol = (r-l) * Math.min(height[l], height[r]);
            maxVol = Math.max(vol, maxVol);
            if ( height[l] < height[r])
                l++;
            else r--;
        }
        System.out.println(maxVol);
    }
}
