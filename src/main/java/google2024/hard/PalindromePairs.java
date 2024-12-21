package google2024.hard;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class PalindromePairs {
    public static void main(String[] args) {
        String[] arr = {"abcd", "dcba", "lls", "s", "sssll"};

        Map<String, List<Integer>> map = new HashMap<>();

        for(int i =0; i< arr.length; i++) {
            String s = arr[i];
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String s1 = String.valueOf(charArray);
            map.putIfAbsent(s1, new ArrayList<>());
            List<Integer> integers = map.get(s1);
            integers.add(i);
        }

        System.out.println(map);
    }
}
