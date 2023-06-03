package practise2023;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

public class MaxOnes {
    public static void main(String[] args) {

        int[] arr = {1, 0, 0, 1, 0, 1, 0, 1, 1, 1};

        // 0, 3, 5, 7, 9
        // 1, 2, 4, 6, 8

        // 2, 1, 1, 1

        // 1 => 0 3
        // 0 -> 1 2

        final Map<Integer, List<Integer>> map = new HashMap<>();
        final AtomicInteger i = new AtomicInteger(0);

        final AtomicInteger k = new AtomicInteger(2);
        final AtomicInteger finalLength = new AtomicInteger(0);

        Arrays.stream(arr).forEach(new IntConsumer() {
            @Override
            public void accept(int value) {

                if(value == 0 ) {
                    if (k.get() > 0) {
                        k.decrementAndGet();
                        List<Integer> integers = map.getOrDefault(value, new ArrayList<>());
                        integers.add(i.get());
                        map.put(value, integers);
                    }
                    else {
                        List<Integer> zeroList = map.get(0);
                        List<Integer> oneList = map.get(1);
                        int start = Math.min(zeroList.get(0), oneList.get(0));
                        int end = Math.max(zeroList.get(zeroList.size()-1), oneList.get(oneList.size()-1));
                        System.out.println(start+"|"+end);
                        int length = 1 + (end-start);
                        finalLength.set(Math.max(length, finalLength.get()));

                        int shrinkTill = zeroList.get(0);
                        zeroList.remove(zeroList.get(0));
                        zeroList.add(i.get());
                        map.put(0, zeroList);

                        while (oneList.get(0) < shrinkTill) {
                            oneList.remove(0);
                        }

                        map.put(1, oneList);
//                        k.incrementAndGet();
                    }
                }
                else {
                    List<Integer> integers = map.getOrDefault(value, new ArrayList<>());
                    integers.add(i.get());
                    map.put(value, integers);
                }
                i.incrementAndGet();
            }
        });
        List<Integer> zeroList = map.get(0);
        List<Integer> oneList = map.get(1);
        int start = Math.min(zeroList.get(0), oneList.get(0));
        int end = Math.max(zeroList.get(zeroList.size()-1), oneList.get(oneList.size()-1));
        System.out.println(start+"|"+end);
        int length = 1 + (end-start);
        finalLength.set(Math.max(length, finalLength.get()));

        System.out.println(finalLength);
    }
}
