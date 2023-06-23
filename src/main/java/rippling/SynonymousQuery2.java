package rippling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DisjointSet {
    private Map<String, String> parents;

    public DisjointSet() {
        parents = new HashMap<>();
    }

    public String getRoot(String w) {
        List<String> wordsTraversed = new ArrayList<>();
        while (parents.containsKey(w) && !parents.get(w).equals(w)) {
            wordsTraversed.add(w);
            w = parents.get(w);
        }
        for (String word : wordsTraversed) {
            parents.put(word, w);
        }
        return w;
    }

    public void addSynonyms(String w1, String w2) {
        if (!parents.containsKey(w1)) {
            parents.put(w1, w1);
        }
        if (!parents.containsKey(w2)) {
            parents.put(w2, w2);
        }

        String w1Root = getRoot(w1);
        String w2Root = getRoot(w2);
        if (w1Root.compareTo(w2Root) < 0) {
            String temp = w1Root;
            w1Root = w2Root;
            w2Root = temp;
        }
        parents.put(w2Root, w1Root);
    }

    public boolean areSynonymous(String w1, String w2) {
        return getRoot(w1).equals(getRoot(w2));
    }
}

public class SynonymousQuery2 {

    private static List<Boolean> synonymQueries(List<List<String>> synonymWords, List<List<String>> queries) {
        DisjointSet synonyms = preprocessSynonyms(synonymWords);

        List<Boolean> output = new ArrayList<>();
        for (List<String> query : queries) {
            String[] q1 = query.get(0).split(" ");
            String[] q2 = query.get(1).split(" ");
            if (q1.length != q2.length) {
                output.add(false);
                continue;
            }
            boolean result = true;
            for (int i = 0; i < q1.length; i++) {
                String w1 = q1[i];
                String w2 = q2[i];
                if (w1.equals(w2)) {
                    continue;
                } else if (synonyms.areSynonymous(w1, w2)) {
                    continue;
                }
                result = false;
                break;
            }
            output.add(result);
        }
        return output;
    }

    private static DisjointSet preprocessSynonyms(List<List<String>> synonymWords) {
        DisjointSet ds = new DisjointSet();
        for (List<String> pair : synonymWords) {
            String w1 = pair.get(0);
            String w2 = pair.get(1);
            ds.addSynonyms(w1, w2);
        }
        return ds;
    }

    public static void main(String[] args) {
        List<List<String>> synonymWords = new ArrayList<>();
        List<String> pair1 = new ArrayList<>();
        pair1.add("happy");
        pair1.add("joyful");
        List<String> pair2 = new ArrayList<>();
        pair2.add("quick");
        pair2.add("fast");
        synonymWords.add(pair1);
        synonymWords.add(pair2);

        List<List<String>> queries = new ArrayList<>();
        List<String> query1 = new ArrayList<>();
        query1.add("happy quick");
        query1.add("joyful fast");
        List<String> query2 = new ArrayList<>();
        query2.add("happy quick");
        query2.add("joyful speed");
        queries.add(query1);
        queries.add(query2);

        List<Boolean> results = synonymQueries(synonymWords, queries);
        for (boolean result : results) {
            System.out.println(result);
        }
    }
}