package salesforce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DistantBarcodes {
    public static void main(String[] args) {

        int [] codes = {1,1,1,2,2,2};
        rearrangeBarcodes(codes);
        System.out.println(Arrays.toString(codes));
    }

   static class Bar {
        int barcode;
        int freq;
        Bar(int barcode, int freq) {
            this.barcode = barcode;
            this.freq = freq;
        }
    }

    static public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : barcodes) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Bar> pq = new PriorityQueue<>(
                (a, b) -> b.freq - a.freq
        );

        for (int key : map.keySet()) {
            pq.offer(new Bar(key, map.get(key)));
        }

        Bar prev = null;
        for (int i = 0; i < barcodes.length; i++) {
            Bar curr = pq.poll();
            barcodes[i] = curr.barcode;
            curr.freq--;

            if (prev != null) {
                pq.offer(prev);
            }
            if (curr.freq > 0) {
                prev = curr;
            }
            else prev = null;
        }

        return barcodes;
    }
}
