package google2024.medium;

import java.util.*;

public class  LongestRepeatingCharReplacement {

    public int characterReplacement(String s, int k) {
        // Initialising an empty array to store the count of the
        // characters in the given string s
        int[] arr = new int[26];
        int res = 0;
        int max = 0;

        // The left pointer for the sliding window is l AND r is the
        // right pointer
        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            // Counting the number of each character in the string s
            arr[s.charAt(r) - 'A']++;

            // Checking the character with max number of occurrence
            max = Math.max(max, arr[s.charAt(r) - 'A']);

            // Now we check if our current window is valid or not
            if (r - l + 1 - max > k) {
                // this means the no. of replacements is more than
                // allowed (k)
                // Decrementing the count of the character which was
                // at l because it is no longer in the window
                arr[s.charAt(l) - 'A']--;
                l++;
            }

            // The max our window can be
            res = Math.max(res, r - l + 1);
        }

        return res;
    }
    public static void main(String[] args) {

        String s = "AABABBA";
        int k = 2;

        Map<Character, List<Integer>> map = new HashMap<>();

        for(int i =0; i<s.length(); i++) {
            Character c = s.charAt(i);
            map.putIfAbsent(c, new ArrayList<>());
            List<Integer> integers = map.get(c);
            integers.add(i);
        }
        System.out.println(map);

        for(Character c : map.keySet()) {
            System.out.println("processing "+ c);
            List<Integer> integers = map.get(c);
            int i = 1;
            int dist = k;
            Set<Integer> set = new HashSet<>();
            while ( i < integers.size() ) {
                int t = integers.get(i) - integers.get(i - 1) - 1;
                if (dist - t >=0) {
                    dist = dist - t;
                    set.add(integers.get(i));
                    set.add(integers.get(i-1));
                }
                i++;
            }
            System.out.println(set);
        }
    }
}
