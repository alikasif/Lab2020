package google2024.hard;

import java.util.*;

public class WordLadder {

    public static void main(String[] args) {

//        String beginWord = "be";
//        String endWord = "ko";
//        String[] words = {"ce","mo","ko","me","co"};

        String beginWord = "hit";
        String endWord = "cog";
        String[] words = {"hot","dot","dog","lot","log", "cog"};

        List<String> list = new ArrayList<>(Arrays.asList(words));
        list.add(beginWord);
        HashMap<String, List<String>> stringListHashMap = buildMap(list);

        findPath1(beginWord, stringListHashMap, endWord, new HashSet<>(), new HashSet<>());
    }

    private static void findPath1(String  key, HashMap<String, List<String>> stringListHashMap, String endWord, HashSet<String> path, HashSet<String> visited) {

        if(path.contains(endWord)) {
            System.out.println(path);
            return;
        }

        if(visited.contains(key))
            return;
        visited.add(key);

        List<String> stringList = stringListHashMap.get(key);

        for(String s : stringList) {
            if(!path.contains(s)) {
                HashSet<String> stringHashSet = new HashSet<>(path);
                stringHashSet.add(s);
                findPath1(s, stringListHashMap, endWord, stringHashSet, visited);
            }
        }
    }

    static HashMap<String, List<String>> buildMap(List<String> list) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(int i=0; i<list.size(); i++) {
            for(int j=0; j<list.size(); j++) {
                if(i != j) {
                    String key = list.get(i);
                    String val = list.get(j);
                    if(isDistance1(key, val)) {
                        map.putIfAbsent(key, new ArrayList<>());
                        List<String> stringList = map.get(key);
                        stringList.add(val);
                    }
                }
            }
        }
        System.out.println(map);
        return map;
    }

    static boolean isDistance1(String s1, String s2) {
        int c=0;
        for(int i=0; i< s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i))
                c++;
        }
        return c == 1;
    }
}
