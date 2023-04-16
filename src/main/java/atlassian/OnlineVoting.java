package atlassian;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class OnlineVoting {

    static Map<Integer, Integer> process(int[] votes, int[] time) {

        Map<Integer, Integer> voteCountMap = new HashMap<>();
        Map<Integer, Integer> winnerAtT = new HashMap<>();

        int i = 0;
        while (i < time.length) {
            Integer votec = voteCountMap.get(votes[i]);
            if(votec == null) {
                voteCountMap.put(votes[i], 1);
            }
            else {
                votec +=1;
                voteCountMap.put(votes[i], votec);
            }
            Integer idOfMaxVote = getIdOfMaxVote(voteCountMap, votes, i);
            winnerAtT.put(time[i], idOfMaxVote);
            i++;
        }
        System.out.println(winnerAtT);
        System.out.println(voteCountMap);
        return winnerAtT;
    }

    static Integer getIdOfMaxVote(Map<Integer, Integer> voteCountMap, int[] votes, int i) {

        int id = -1;
        int mc = -1;

        for(int j=0; j<=i; j++) {
            int c = voteCountMap.get(votes[j]);
            if(c >= mc) {
                mc = c;
                id = votes[j];
            }
        }
        return id;
    }

    public static void main(String[] args) {

        int[] votes = {0, 1, 1, 2, 0, 1, 0, 2, 2};
        int[] time =  {0, 0, 5, 10, 15, 20, 25, 30, 35};
        int[] queryTime = {3, 12, 25, 15, 24, 8, 45};

        Map<Integer, Integer> winnerATT = process(votes, time);
        for(int t : queryTime) {
            int i = closestBinarySearch(t, time);
            System.out.println(i + " :: winner at "+ t + " " + winnerATT.get(i));
        }
    }

    static void process2(int[] votes, int[] time) {

        // map of time & winner for input
        // binary search to find closest neighbour less than given time and then print result
        // preprocess and keep a mapping of time to interval boundary

        Map<Integer, Integer> voteCountMap = new HashMap<>();
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
        System.out.println("searching for " + q);
        if (q < times[0])
            return times[0];
        if(q > times[times.length-1])
            return times[times.length-1];

        int low =0;
        int high = times.length-1;
        int mid = low + (high-low)/2;

        while (low <= high) {
            // System.out.println(mid);
            if (times[mid] == q) {
                return times[mid];
            }
            else {
                    if (times[mid] > q)
                        high = mid-1;
                    else
                        low = mid+1;
            }
            mid = low + (high-low)/2;
        }
        //return times[high];
        return Math.abs(q-times[low] ) < Math.abs(q - times[high]) ? times[low] : times[high];
    }
}