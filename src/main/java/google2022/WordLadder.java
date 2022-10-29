package google2022;

import java.util.*;

public class WordLadder {

    public static void main(String[] args) {

        String beginWord = "be";

        String endWord = "ko";

        String[] wordArray = {"ce", "mo", "ko", "me", "co"};

        System.out.println(findPath(beginWord, endWord, wordArray));
        //System.out.println(visited);
    }

    private static int findPath(String beginWord, String endWord, String[] wordArray) {
        List<String> wordList = Arrays.asList(wordArray);

        int changes = 0;
        List<String> visited = new ArrayList<>();

        List<String> queue = new ArrayList<>();
        queue.add(beginWord);

        while (!queue.isEmpty()) {

            String tmp = queue.remove(0);
            changes++;
            for(int i=0; i<tmp.length(); i++) {
                for(int j= 97; j<97+26; j++) {
                    String tmp2 = getString(tmp, i, j);
                    if (tmp2.equals(endWord)) {
                        System.out.println("found");
                        visited.add(tmp2);
                        return changes;
                    }
                    if(!visited.contains(tmp2) && wordList.contains(tmp2)) {
                        queue.add(tmp2);
                        visited.add(tmp2);
                    }
                }
            }
        }
        return 0;
    }

    private static String getString(String tmp, int i, int j) {
        char[] chars = tmp.toCharArray();
        chars[i] = (char)j;
        return String.valueOf(chars);
    }
}
