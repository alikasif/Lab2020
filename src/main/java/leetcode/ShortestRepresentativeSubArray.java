package leetcode;

import scala.Int;

import java.util.*;
import java.util.stream.Stream;

public class ShortestRepresentativeSubArray {

    public static void main(String[] args) {

        int[] arr = {3,1,4,3,5,2,1,4,6,7,8,9,2,1,3,5,3,2,6,7,8,9};
        int[] distinct = getDistinctElements(arr);
        getSmallestSubarray(arr,arr.length,9);
    }

    static void mySol(int[] arr, int[] distinct) {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int t: distinct)
            list.add(t);

        int i = 0;
        int j = 0;
        while ( j < arr.length ) {
            if( list.size() == 0 || j == arr.length-1 ) {
                System.out.println( i + " " + (j-1) + " "+ Arrays.toString(Arrays.copyOfRange(arr,i,(j))));
                while (true) {
                    if(map.get(arr[i]) > 1) {
                        map.put(arr[i], map.get(arr[i])-1);
                        i++;
                    }
                    else {
                        list.addAll(map.keySet());
                        break;
                    }
                }
            }
            else {
                list.remove(Integer.valueOf(arr[j]));
                map.put(arr[j], map.containsKey(arr[j])? map.get(arr[j])+1:1);
            }
            j++;
        }
    }

    static int[] getDistinctElements(int[] arr) {
        return Arrays.stream(arr).distinct().toArray();
    }

    public static void getSmallestSubarray(int arr[], int n, int k)
    {
        int left = 0, right = n;
        int j = -1;
        HashMap<Integer, Integer> MAP=new HashMap<>();

        for (int i = 0; i < n; i++)
        {
            while (j < n-1)
            {
                j++;
                if (MAP.size() < k)
                {
                    MAP.put(arr[j], MAP.containsKey(arr[j])? MAP.get( arr[j] ) + 1: 1);
                }
                if (MAP.size() == k && ((right - left) >= (j - i)))
                {
                    System.out.println(MAP+" :"+1+" "+i+" "+j);
                    left = i;
                    right = j;
                    break;
                }
            }
            if (MAP.size() < k)
                break;
            while (MAP.size() == k)
            {
                System.out.println(MAP+" :"+2+" "+i+" "+j);
                Integer integer = MAP.get(arr[i]) == 1 ? MAP.remove(arr[i]) : MAP.put(arr[i], MAP.get(arr[i]) - 1);
                i++;
                if (MAP.size() == k && (right - left) >= (j - i)) // size is still k and we found smaller range
                {
                    left = i;
                    right = j;
                }
            }
            //Integer integer = MAP.get(arr[i]) == 1 ? MAP.remove(arr[i]) : MAP.put(arr[i], MAP.get(arr[i]) - 1);
            System.out.println(MAP+" :"+3+" "+i+" "+j);
        }
        if (left == 0 && right == n)
            System.out.println("Invalid k");
        else
            System.out.println(left+" "+right);
    }
}