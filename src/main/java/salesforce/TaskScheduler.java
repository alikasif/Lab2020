package salesforce;

import scala.Char;

import java.util.*;

public class TaskScheduler {

    public static void main(String[] args) {

        //char[] arr = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        char[] arr = {'A', 'A', 'A'};
        int n = 2;
        //System.out.println(leastInterval(arr, n));
        usingPQ(arr, n);
    }

    static void usingPQ(char[] arr, int n) {

        Map<Character, Integer> freqMap = new HashMap<>();

        PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));

        for(Character c : arr) {
            freqMap.put(c, freqMap.getOrDefault(c, 0)+1);
        }
        pq.addAll(freqMap.keySet());


        int time=0;
        while (!pq.isEmpty()) {
            List<Character> remaining = new ArrayList<>();
            int cycles = n+1;
            while (cycles > 0 && !pq.isEmpty()) {
                Character poll = pq.poll();
                System.out.println("working on " + poll);
                time++;
                cycles--;
                freqMap.put(poll, freqMap.get(poll) - 1);
                if (freqMap.get(poll) > 0) {
                    remaining.add(poll);
                }
            }
            if(remaining.size() > 0) {
                pq.addAll(remaining);
            }
            if(pq.isEmpty()) break;
            time = time+cycles; // cycles will remain if pq has only 1 char. so we take break. these are idel cycles.
        }
        System.out.println(time);
    }
}
