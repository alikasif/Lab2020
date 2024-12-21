package google2024.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeIntervals {

    public static int[][] fun(Integer[][] intervals){

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> list = new LinkedList<>();
        int size = intervals.length;
        int start = intervals[0][0];
        int end = intervals[0][1];

        for (int i = 1; i < size; i++) {
            if (end >= intervals[i][0]) {
                if (end < intervals[i][1]) {
                    end = intervals[i][1];
                }

                continue;
            }

            list.add(new int[] { start, end });
            start = intervals[i][0];
            end = intervals[i][1];
        }

        list.add(new int[] { start, end });

        int[][] ans = new int[list.size()][2];
        int j = 0;

        for (int[] i : list) {
            ans[j][0] = i[0];
            ans[j][1] = i[1];
            j++;
        }

        return ans;
    }


    public static void main(String[] args) {

        //Integer [][] arr = {{1,3},{8,10}, {2,6},{15,18}};
        //Integer [][] arr = {{1,3},{5,10}, {2,6},{9,18}};
        Integer [][] arr = {{1,3},{8,10}, {4,6},{9,18}};

//        int[][] fun = fun(arr);
//        for(int[] t : fun) {
//            System.out.println(Arrays.toString(t));
//        }

        System.out.println("done");
        Arrays.sort(arr, (a, b) -> a[0].compareTo(b[0]));
        for(Integer[] t : arr) {
            System.out.println(Arrays.toString(t));
        }
        System.out.println();

        List<Integer[]> list = new ArrayList<>();

        Integer[] tmp = arr[0];
        int start = tmp[0];
        int end = tmp[1];

        int i=1;
        while (i < arr.length) {

            Integer[] x = arr[i];

            if ( x[0] >= start && x[0] <= end ) {
                start = Math.min(start, x[0]);
                end = Math.max(end, x[1]);
            }
            else {
                list.add(new Integer[] {start, end});
                start = x[0];
                end = x[1];
            }
            i++;
        }
        list.add(new Integer[] {start, end});

        for(Integer[] f : list) {
            System.out.println(Arrays.toString(f));
        }
    }
}
