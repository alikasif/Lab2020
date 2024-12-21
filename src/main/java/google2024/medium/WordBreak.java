package google2024.medium;

import java.util.*;

public class WordBreak {
    public static void main(String[] args) {

        String word = "leetcode";
        List<String> dict = new ArrayList<>();
        dict.add("leet");
        dict.add("code");

        List<String> combinations = new ArrayList<>();
        for(String s : dict) {
            combinations.add(s);
            for (String g : dict) {
                combinations.add(s+g);
            }
        }
        System.out.println(combinations);
        System.out.println(combinations.contains(word));

        Map<String, Boolean> memo = new HashMap<>();
        Set<String> wordSet = new HashSet<>(dict);
        boolean result =  dfs(word, wordSet, memo);
        System.out.println(result);
    }

    private static boolean dfs(String s, Set<String> wordSet, Map<String, Boolean> memo) {
        System.out.println(memo);
        if (memo.containsKey(s)) return memo.get(s);
        if (wordSet.contains(s)) return true;
        for (int i = 1; i < s.length(); i++) {
            String prefix = s.substring(0, i);
            if (wordSet.contains(prefix) && dfs(s.substring(i), wordSet, memo)) {
                memo.put(s, true);
                return true;
            }
        }
        memo.put(s, false);
        System.out.println(memo);
        return false;
    }
}
