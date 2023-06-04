package salesforce;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharByFreq {
    public static void main(String[] args) {
        String s = "tree";

        Map<Character, Integer> freqMap = new HashMap<>();

        for(int i=0; i<s.length(); i++) {
            freqMap.put(s.charAt(i), freqMap.getOrDefault(s.charAt(i), 0)+1);
        }
        System.out.println(freqMap);

        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return freqMap.get(o2) - freqMap.get(o1);
            }
        });

        for(Character c : freqMap.keySet())
            pq.add(c);

        int k = 2;
        while (!pq.isEmpty())
            System.out.println(pq.poll());
    }
}