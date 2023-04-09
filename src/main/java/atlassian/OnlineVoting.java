package atlassian;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class OnlineVoting {

    public static void main(String[] args) {

        int[] votes = {0, 1, 1, 1, 0, 0, 1, 0};
        int[] time =  {0, 0, 5, 10, 15, 20, 25, 30};

        int[] queryTime = {3, 12, 25, 15, 24, 8};

        // map of time & winner for input
        // binary search to find closest neighbour less than given time and then print result
        // preprocess and keep a mapping of time to interval boundary

        Map<Integer, Integer> winnerAtT = new HashMap<>();
        TreeMap<Integer, Integer> winnerAtTMap = new TreeMap<>();

        int c1 = 0;
        int c2 = 0;

        if (votes[0] == 0) {
            c1++;
        } else {
            c2++;
        }

        for (int i = 1; i < time.length; i++) {
            if (votes[i] == 0) {
                c1++;
            } else {
                c2++;
            }
            Integer winner = winnerAtT.get(time[i]);
            if(winner == null) {

                if(c1 == c2) {
                    winnerAtT.put(time[i], votes[i]);
                    winnerAtTMap.put(time[i], votes[i]);
                }
                else if (c1 > c2) {
                    winnerAtT.put(time[i], 0);
                    winnerAtTMap.put(time[i], 0);
                }
                else {
                    winnerAtT.put(time[i], 1);
                    winnerAtTMap.put(time[i], 1);
                }
            }
        }

        System.out.println(winnerAtT);
        System.out.println(winnerAtTMap);

        System.out.println(winnerAtT.get(time[closestBinarySearch(3, time)]));
        System.out.println(winnerAtTMap.floorEntry(3).getValue());

        System.out.println(winnerAtT.get(time[closestBinarySearch(6, time)]));
        System.out.println(winnerAtT.get(time[closestBinarySearch(11, time)]));
        System.out.println(winnerAtT.get(time[closestBinarySearch(33, time)]));
    }

    static int closestBinarySearch(int q, int[] times) {

        int low =0;
        int high = times.length-1;
        int mid = low + (high-low)/2;

        while (low <= high) {
            // System.out.println(mid);
            if (times[mid] == q) {
                return mid;
            }
            else {
                    if (times[mid] > q)
                        high = mid-1;
                    else
                        low = mid+1;
            }
            mid = low + (high-low)/2;
        }
        return high;
    }
}