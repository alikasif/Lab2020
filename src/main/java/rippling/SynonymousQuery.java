package rippling;

import java.util.*;
import java.util.stream.Collectors;

public class SynonymousQuery {
    public static void main(String[] args) {

        Map<String, List<String>> map = new HashMap<>();

        List<String> synonyms = new ArrayList<>();
        synonyms.add("ratings");
        synonyms.add("rate");
        map.put("rate", synonyms);

        List<String> synonyms2 = new ArrayList<>();
        synonyms2.add("rate");
        synonyms2.add("ratings");
        map.put("ratings", synonyms2);

        List<String> synonyms3 = new ArrayList<>();
        synonyms3.add("approval");
        synonyms3.add("popularity");
        map.put("popularity", synonyms3);

        List<String> synonyms4 = new ArrayList<>();
        synonyms4.add("popularity");
        synonyms4.add("approval");
        map.put("approval", synonyms4);

        System.out.println(map);

        // a -> b  c -> d   g -> d  b -> g  b -> d  a -> x
        List<String[]> synonymList = new ArrayList<>();
        synonymList.add(new String[]{"a", "b"});
        synonymList.add(new String[]{"c", "d"});
        synonymList.add(new String[]{"g", "d"});
        synonymList.add(new String[]{"b", "g"});
        synonymList.add(new String[]{"b", "d"});
        synonymList.add(new String[]{"a", "x"});
        synonymList.add(new String[]{"i", "j"});
        synonymList.add(new String[]{"p", "q"});
        synonymList.add(new String[]{"m", "n"});
        synonymList.add(new String[]{"j", "z"});

        Map<String, String> synonymMap = prepareSynonymMap2(synonymList);
        System.out.println(synonymMap);



        synonymList = new ArrayList<>();
        synonymList.add(new String[]{"ratings", "rate"});
        synonymList.add(new String[]{"approval", "popularity"});
        synonymList.add(new String[]{"ratings", "rates"});
        synonymMap = prepareSynonymMap2(synonymList);
        System.out.println(synonymMap);


        List<String[]> sentenceList = new ArrayList<>();
        String[] sentences1 = {"obama approval rate", "obama popularity ratings"};
        sentenceList.add(sentences1);

        String[] sentences2 = {"obama approval rates", "obama popularity ratings"};
        sentenceList.add(sentences2);
        String[] sentences3 = {"obama approval rate", "popularity ratings obama"};
        sentenceList.add(sentences3);


        Map<String, Boolean> stringBooleanMap = checkSimilarity(synonymMap, sentenceList);
        System.out.println(stringBooleanMap);

    }

    private static Map<String, String> prepareSynonymMap2(List<String[]>  synonyms) {

        Map<String, String> synonymMap = new HashMap<>();

        Map<String, List<String>> map = new HashMap<>();
        Set<String> allWords = new HashSet<>();

        for(String[] list : synonyms) {

            String w1 = list[0];
            String w2 = list[1];

            allWords.add(w1);
            allWords.add(w2);

            List<String> w1List = map.getOrDefault(w1, new ArrayList<>());
            w1List.add(w2);
            map.put(w1, w1List);

            List<String> w2List = map.getOrDefault(w2, new ArrayList<>());
            w2List.add(w1);
            map.put(w2, w2List);
        }

        Set<String> visited = new HashSet<>();
        List<String> wordList = new ArrayList<>(allWords);

        while (!wordList.isEmpty()) {

            String removedWord = wordList.remove(0);

            if(visited.contains(removedWord))
                continue;

            Stack<String> stack = new Stack<>();
            stack.add(removedWord);
            Set<String> synonymSet = new HashSet<>();

            while (!stack.isEmpty()) {
                String pop  = stack.pop();
                visited.add(pop);
                synonymSet.add(pop);
                for(String value : map.get(pop)) {
                    if(!visited.contains(value)) {
                        stack.add(value);
                    }
                }
            }

            String synWord = null;
            for(String w : synonymSet) {
                if(synWord == null)
                    synWord = w;
                synonymMap.put(w, synWord);
            }
        }
        return synonymMap;
    }

    static Map<String, Boolean> checkSimilarity(Map<String, String> synonymMap, List<String[]> sentenceList) {

        Map<String, Boolean> resultMap = new HashMap<>();

        for(String[] sentences : sentenceList) {

            String[] sentence1 = sentences[0].split(" ");
            String[] sentence2 = sentences[1].split(" ");

            if(sentence1.length != sentence2.length) {
                resultMap.put(sentences[0] + " | " + sentences[1], false);
                continue;
            }

            int i = 0;
            boolean result = true;
            while ( result && i < sentence1.length) {
                if(!sentence1[i].equals(sentence2[i])) {
                    if(synonymMap.containsKey(sentence1[i]) && synonymMap.containsKey(sentence2[i]) ) {
                        if (!(synonymMap.get(sentence1[1]).equals(synonymMap.get(sentence2[1])))) {
                            result = false;
                        }
                    }
                    else {
                        result = false;
                    }
                }
                i++;
            }
            resultMap.put(sentences[0] +" | " + sentences[1], result);
        }
        return resultMap;
    }

    static String[][]  processSentence(List<String> lst) {
        String[][] tmp = new String[lst.size()][];
        int maxl = 0;
        int k = 0;
        for (String s : lst) {
            String[] s1 = s.split(" ");
            tmp[k] = s1;
            maxl = Math.max(maxl, s1.length);
            k++;
        }
        System.out.println(lst);
        for(String x[] : tmp) {
            System.out.println(Arrays.toString(x));
        }
        return tmp;
    }

    static boolean checkSynonym(String[][] tmp, Map<String, List<String>> map) {

        int r=0; int c=0; boolean found = true;
        int maxl= tmp.length;

        if(!arrayLenOK(tmp))
            return false;

        int len = tmp[0].length;

        Set<String> synonyms = new HashSet<>();
        while (c < len) {
            synonyms.add(tmp[r][c]);
            r++;

            if(r < tmp.length)
                continue;
            r=0;
            c++;
            System.out.println("synonyms "+ synonyms);
            if(synonyms.size() > 1) {
                Set<String> synonyms2 = new HashSet<>();
                for(String ss : synonyms) {
                    // synonyms2.add(ss);
                    if(map.get(ss) != null)
                        synonyms2.addAll(map.get(ss));
                }

                if(!synonyms2.containsAll(synonyms)) {
                    return false;
                }
                else {
                    synonyms.clear();
                    continue;
                }
            }
            else {
                synonyms.clear();
                continue;
            }
        }
    return found;
    }

    private static boolean arrayLenOK(String[][] tmp) {
        Set<Integer> arrayLen = new HashSet<>();
        for(String[] singles : tmp)
            arrayLen.add(singles.length);

        if(arrayLen.size() > 1)
            return false;

        return true;
    }
}