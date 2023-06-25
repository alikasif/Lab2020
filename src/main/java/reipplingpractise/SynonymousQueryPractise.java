package reipplingpractise;

import java.util.*;

class SynonymPractise {

    public  Map<String, String> prepareSynMap(List<String[]> synonyms){

        Map<String, String> synMap = new HashMap<>();

        Map<String, List<String>> map = new HashMap<>();

        Set<String> allWords = new HashSet<>();
        for(String[] arr: synonyms) {
            String syn1 = arr[0];
            String syn2 = arr[1];
            allWords.add(syn1);
            allWords.add(syn2);

            List<String> list = map.getOrDefault(syn1, new ArrayList<>());
            list.add(syn2);
            map.put(syn1, list);

            List<String> list2 = map.getOrDefault(syn2, new ArrayList<>());
            list2.add(syn1);
            map.put(syn2, list2);
        }
        System.out.println(map);
        List<String> allSynWords = new ArrayList<>(allWords);

        while (!allSynWords.isEmpty()) {

            Stack<String> stack  = new Stack<>();
            stack.add(allSynWords.remove(0));

            Set<String> synSet = new HashSet<>();

            while (!stack.isEmpty()) {
                String pop = stack.pop();
                synSet.add(pop);
                for(String s : map.get(pop)) {
                    if(!synSet.contains(s)) {
                        synSet.add(s);
                        allSynWords.remove(s);
                        stack.add(s);
                    }
                }
            }

            System.out.println(synSet);
            String head = null;
            for(String s : synSet) {
                if(head == null)
                    head = s;
                synMap.put(s, head);
            }
        }
        return synMap;
    }

    public Map<String, Boolean> checkSimilarity(List<List<String>> sentenceList, Map<String, String> synMap) {

        Map<String, Boolean> map = new HashMap<>();

        for(List<String> sentences : sentenceList) {

            String[] sentenceArray1  = sentences.get(0).split(" ");
            String[] sentenceArray2  = sentences.get(1).split(" ");

            if(sentenceArray1.length != sentenceArray2.length) {
                map.put(sentences.get(0)+"|"+sentences.get(1), false);
                continue;
            }
            int i=0;
            boolean result = true;
            while (result && ++i < sentenceArray1.length) {

                if(sentenceArray1[i].equals(sentenceArray2[i])) continue;

                if( !synMap.containsKey(sentenceArray1[i]) || !synMap.containsKey(sentenceArray2[i])) {
                    map.put(sentences.get(0)+"|"+sentences.get(1), false);
                    result = false;
                    continue;
                }

                if(!synMap.get(sentenceArray1[i]).equals(synMap.get(sentenceArray2[i]))) {
                    map.put(sentences.get(0)+"|"+sentences.get(1), false);
                    result = false;
                    continue;
                }
            }
            if(result) {
                map.put(sentences.get(0)+"|"+sentences.get(1), true);
            }
        }
        return map;
    }
}

public class SynonymousQueryPractise {
    public static void main(String[] args) {
        SynonymPractise synonymPractise = new SynonymPractise();

        String[] syn1 = {"a", "b"};
        String[] syn2 = {"c", "d"};
        String[] syn3 = {"e", "f"};
        String[] syn4 = {"c", "f"};
        String[] syn5 = {"f", "b"};
        String[] syn6 = {"x", "y"};
        String[] syn7 = {"p", "q"};
        List<String[]> list = new ArrayList<>();
        list.add(syn1); list.add(syn2); list.add(syn3); list.add(syn4);
        list.add(syn5); list.add(syn6); list.add(syn7);

        Map<String, String> stringStringMap = synonymPractise.prepareSynMap(list);
        System.out.println(stringStringMap);

        List<List<String>> sentenceList = new ArrayList<>();

        List<String> sentence1 = new ArrayList<>();
        sentence1.add("c is e");
        sentence1.add("f is d");
        sentenceList.add(sentence1);

        List<String> sentence2 = new ArrayList<>();
        sentence2.add("x is p");
        sentence2.add("x is y");
        sentenceList.add(sentence2);

        List<String> sentence3 = new ArrayList<>();
        sentence3.add("c is e");
        sentence3.add("f is m");
        sentenceList.add(sentence3);


        Map<String, Boolean> stringBooleanMap = synonymPractise.checkSimilarity(sentenceList, stringStringMap);
        System.out.println(stringBooleanMap);

    }
}
