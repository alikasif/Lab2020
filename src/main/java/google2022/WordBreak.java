package google2022;

import java.util.*;

public class WordBreak {

    public static void main(String[] args) {

        String input = "catsanddog";

        String[] worddict = {"cat", "cats", "and", "sand", "dog"};

        HashSet<String> strings = new HashSet<>(List.of(worddict));
        List<String> stringList = wordBreak(input, strings);
        System.out.println(stringList);
    }

    public static List<String> wordBreak(String s, Set<String> wordDict) {
        return DFS(s, wordDict, new HashMap<>());
    }

    // DFS function returns an array including all substrings derived from s.
    static List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {

        System.out.println(map);

        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String>res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }

        for (String word : wordDict) {

            if (s.startsWith(word)) {

                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);

                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }
}
