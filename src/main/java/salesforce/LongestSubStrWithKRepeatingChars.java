package salesforce;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LongestSubStrWithKRepeatingChars {

    public static void main(String[] args) {

        String s = "ababbc";
        final int k = 2;

        Map<Character, Integer> freqMap = new HashMap<>();

        for(int i =0; i<s.length(); i++)
            freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i), 0)+1);

        Set<Map.Entry<Character, Integer>> collect = freqMap.entrySet().stream().filter(new Predicate<Map.Entry<Character, Integer>>() {
            @Override
            public boolean test(Map.Entry<Character, Integer> characterIntegerEntry) {
                return characterIntegerEntry.getValue() < k;
            }
        }).collect(Collectors.toSet());
        System.out.println(collect);

        boolean allChars = true;

        for(Character c : freqMap.keySet()) {
            if(freqMap.get(c) < k)
                allChars = false;
        }

        ArrayList<Map.Entry<Character, Integer>> entries = new ArrayList<>(collect);
        List<String> strings = new ArrayList<>();
        if(!allChars) {
            while (true) {
                if(entries.isEmpty())
                    break;
                Character key = entries.remove(0).getKey();
                String[] split = s.split(String.valueOf(key));
                strings.addAll(Arrays.asList(split));
            }
            for(String s1 : strings)
                System.out.println(s1);
        }
    }
}

