package salesforce;

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

        Map<String, String> stringStringMap = prepareSynonymMap(synonymList);
        System.out.println(stringStringMap);


        List<List<String>> sentences = new ArrayList<>();

        List<String> list = new ArrayList<>();
        list.add("obama approval rate");
        list.add("obama popularity ratings");
        sentences.add(list);

        List<String> list2 = new ArrayList<>();
        list2.add("obama approval rates");
        list2.add("obama popularity ratings");
        sentences.add(list2);

        List<String> list3 = new ArrayList<>();
        list3.add("obama approval rate");
        list3.add("popularity ratings obama");
        sentences.add(list3);

        System.out.println(sentences);
        System.out.println();

        /*for (List<String> lst : sentences) {
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
            int r=0; int c=0; boolean found = true;

            while (c < maxl) {
                found = true;
                Set<String> set = new HashSet<>();
                while (r < tmp.length) {
                    set.add(tmp[r][c]);
                    r++;
                }
                System.out.println("words from set => " +set);
                c++;
                r=0;
                if(set.size()>1) {
                    Set<String> tmps = new HashSet<>();
                    for(String rs : set) {
                        if(map.get(rs) != null) {
                            tmps.addAll(map.get(rs));
                            tmps.add(rs);
                        }
                    }
                    for(String rs : set) {
                        if(!tmps.contains(rs)) {
                            System.out.println(rs +" not found in "+ tmps);
                            found = false;
                            break;
                        }
                    }
                }
            }
            if(!found) {
                System.out.println("### Not synonymous " + lst);
            }
            else {
                System.out.println("*** synonymous " + lst);
            }
        }*/

        for (List<String> lst : sentences) {
            String[][] strings = processSentence(lst);
            boolean checked = checkSynonym(strings, map);
            System.out.println(lst + " *** result " + checked);
            System.out.println("\n");
        }
    }

    private static Map<String, String> prepareSynonymMap2(String[] words, Map<String, String> map) {
        if (!map.containsKey(words[0])) {
            map.put(words[0], words[0]);
        }
        if (!map.containsKey(words[1])) {
            map.put(words[1], words[1]);
        }

        List<String> wordsTraversed = new ArrayList<>();

        return map;
    }

    private static Map<String, String> prepareSynonymMap(List<String[]> synonyms) {

        // a -> b  c -> d   g -> d  b -> g  b -> d  a -> x
        // map all keys to same value
        //
        // key map to anther  key
        // key map to another value
        // value map to another key
        // value map to another value

        // at a time we will deal with 2 entries only

        Map<String, String> map = new HashMap<>();

        for(String[] lst : synonyms) {
            if( map.containsKey(lst[0]) || map.containsKey(lst[1]) || map.containsValue(lst[0]) || map.containsValue(lst[1]) ) {

                if(map.containsKey(lst[0])) {
                    map.put(lst[1], map.get(lst[0]));
                }

                // x -> b =>  (a,x) => a -> b
                else if(map.containsKey(lst[1])) {
                    map.put(lst[0], map.get(lst[1]));
                }

                // a -> x => (x, b) => b -> x
                else if(map.containsValue(lst[0])) {
                    map.put(lst[1], lst[0]);
                }

                // a -> x => (b, x) => b -> x
                else if(map.containsValue(lst[1])) {
                    map.put(lst[0], lst[1]);
                }
            }
            else {
                map.put(lst[0], lst[1]);
            }
        }

        // find entries where value is key
        boolean modified = true;
        int c=0;
        while (modified) {
            modified = false;
            Set<String> samekV = new HashSet<>();
            for(Map.Entry<String, String> kv : map.entrySet()) {
                if(map.containsKey(kv.getValue())) {
                    map.put(kv.getKey(), map.get(kv.getValue()));
                    modified = true;
                    c++;
                }
                if(kv.getKey().equals(kv.getValue())) {
                    samekV.add(kv.getKey());
                }
            }
            for(String k : samekV)
                map.remove(k);
        }
        System.out.println("looped "+c);
        return map;
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