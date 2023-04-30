package atlassian;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OnlineVoting {

    static Map<Integer, Integer> process(int[] votes, int[] time) {

        Map<Integer, Integer> voteCountMap = new HashMap<>();
        Map<Integer, Integer> winnerAtT = new HashMap<>();

        int i = 0;
        while (i < time.length) {
            Integer votec = voteCountMap.get(votes[i]);
            if (votec == null) {
                voteCountMap.put(votes[i], 1);
            } else {
                votec += 1;
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

        for (int j = 0; j <= i; j++) {
            int c = voteCountMap.get(votes[j]);
            if (c >= mc) {
                mc = c;
                id = votes[j];
            }
        }
        return id;
    }

    public static void main(String[] args) {

        /*int[] votes = {0, 1, 1, 2, 0, 1, 0, 2, 2};
        int[] time = {0, 0, 5, 10, 15, 20, 25, 30, 35};
        int[] queryTime = {3, 12, 25, 15, 24, 8, 45};

        Map<Integer, Integer> winnerATT = process(votes, time);
        for (int t : queryTime) {
            int i = closestBinarySearch(t, time);
            System.out.println(i + " :: winner at " + t + " " + winnerATT.get(i));
        }*/

        //onlineVoting2();
        onlineVoting3();
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
            if (winner == null) {

                if (c1 == c2) {
                    winnerAtT.put(time[i], votes[i]);
                    winnerAtTMap.put(time[i], votes[i]);
                } else if (c1 > c2) {
                    winnerAtT.put(time[i], 0);
                    winnerAtTMap.put(time[i], 0);
                } else {
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
        if (q > times[times.length - 1])
            return times[times.length - 1];

        int low = 0;
        int high = times.length - 1;
        int mid = low + (high - low) / 2;

        while (low <= high) {
            // System.out.println(mid);
            if (times[mid] == q) {
                return times[mid];
            } else {
                if (times[mid] > q)
                    high = mid - 1;
                else
                    low = mid + 1;
            }
            mid = low + (high - low) / 2;
        }
        //return times[high];
        return Math.abs(q - times[low]) < Math.abs(q - times[high]) ? times[low] : times[high];
    }

    static void onlineVoting3() {

        List<String> vote1 = Arrays.asList("A","B","C");
        List<String> vote2 = Arrays.asList("A","B","D");
        List<String> vote3 = Arrays.asList("B","C","A");

        List<Integer> vote4 = List.of(1, 2, 3);

        List<List<String>> votesList = new ArrayList<>();
        votesList.add(vote1);
        votesList.add(vote2);
        votesList.add(vote3);

        Map<String, Integer> voteCount = new HashMap<>();
        Map<Integer, List<String>> indexMap = new HashMap<>();

        for(int i=0; i<votesList.size(); i++) {
            List<String> votes = votesList.get(i);
            int w = votes.size();
            int j = 0;
            for(String vote : votes) {
                voteCount.put(vote, voteCount.getOrDefault(vote,0) + (w) );
                w--;
                List<String> list = indexMap.getOrDefault(j, new ArrayList<>());
                list.add(vote);
                indexMap.put(j++, list);
            }
        }

        System.out.println(indexMap);

        List<Map.Entry<String, Integer>> sortedVotes = voteCount.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());

        List<String> winners = new ArrayList<>();
        int count =  sortedVotes.get(0).getValue(); //max vote count

        for(Map.Entry<String, Integer> entry : sortedVotes) {
            if(entry.getValue() == count){
                winners.add(entry.getKey());
            }
        }

        System.out.println(winners);
        System.out.println(voteCount);
        System.out.println(sortedVotes);

        if(winners.size()>1) { // tie breaker based on index
            for (Map.Entry<Integer, List<String>> entry : indexMap.entrySet()) {
                List<String> indexWinner = new ArrayList<>();
                for (String s : winners) {
                    if (entry.getValue().contains(s)) {
                        indexWinner.add(s);
                    }
                }
                if (indexWinner.size() == 1) {
                    System.out.println(indexWinner + " is the winner !!!");
                    return;
                }
            }
        }
        else {
            System.out.println(winners + " is the winner !!!");
        }
    }

    static void onlineVoting2() {
        String[] votes = {
                "john", "johnny", "jackie",
                "johnny", "john", "jackie",
                "jamie", "jamie", "john",
                "johnny", "jamie", "johnny",
                "john"};

        Map<String, Integer> voteCountMap = new HashMap<>();

        class Winner implements Comparable<Winner>{
            int c;
            String id;

            @Override
            public String toString() {
                return id+" "+c;
            }

            @Override
            public int compareTo(Winner o) {
                return o.c - this.c;
            }
        }

        Winner winner = null;

        for (String vote : votes) {
            voteCountMap.put(vote, voteCountMap.getOrDefault(vote, 0)+1);
            if(winner == null) {
                winner = new Winner();
            }
            if(voteCountMap.get(vote) > winner.c) {
                winner.id = vote;
                winner.c = voteCountMap.get(vote);
            }
       }

        System.out.println(voteCountMap);
        System.out.println(winner);
        List<Map.Entry<String, Integer>> list = voteCountMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
        System.out.println(list);
    }
}