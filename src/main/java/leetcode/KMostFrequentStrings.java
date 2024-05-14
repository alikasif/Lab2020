package leetcode;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Item implements Comparable<Item> {
    int f;
    String s;
    public Item(String s1, int f1) {
        f = f1;
        s= s1;
    }

    @Override
    public String toString() {
        return "Item{" +
                "f=" + f +
                ", s='" + s + '\'' +
                '}';
    }

    @Override
    public int compareTo(Item o) {
        if(this.f < o.f)
            return -1;
        else if(this.f > o.f)
            return 1;
        else
            return 0;
    }
}

public class KMostFrequentStrings {

    public static void main(String[] args) {
        int k=3;
        Map<String, Integer> freqMap = new HashMap<>();
        PriorityQueue<Item> pq = new PriorityQueue<>(3);

        Scanner in = new Scanner(System.in);
        while (true) {

            String input = in.nextLine();
            if(input.equals("exit"))
                break;
            //System.out.println(input);
            if(freqMap.containsKey(input))
                freqMap.put(input, freqMap.get(input)+1);
            else
                freqMap.put(input, 1);

            System.out.println(freqMap);
            if(pq.size() < 3)
                pq.add(new Item(input, freqMap.get(input)));
            else {
                Item poll = pq.peek();
                if(poll.f < freqMap.get(input)) {
                    pq.remove(poll);
                    pq.add(new Item(input, freqMap.get(input)));
                }
            }
        }
        System.out.println(pq);
    }
}
