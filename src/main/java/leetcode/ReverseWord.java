package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReverseWord {

    public static void main(String[] args) {

        String s = "this is good";
        char[] arr = s.toCharArray();
        List<Integer> spaces = new ArrayList<>();
        for(int i=0; i<arr.length/2; i++) {
            char c = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = c;
        }
        int start = 0;
        System.out.println(Arrays.toString(arr));

        for(int i =0; i<arr.length; i++) {
            if(arr[i] == ' ' || i==arr.length-1) {
                int end = i==arr.length-1?i:i-1;
                int tmp = end;
                System.out.println(start+" "+end+" "+tmp);
                for(int j = start; j<=(end+start)/2;j++) {
                    char t = arr[j];
                    arr[j] = arr[tmp];
                    arr[tmp] = t;
                    tmp--;
                }
                start=i+1;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}