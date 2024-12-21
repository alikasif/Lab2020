package atlassian;

import java.util.*;
import java.util.stream.Collectors;

public class OnlineVoting2 {

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
}
