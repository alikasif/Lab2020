package atlassian2024.voting;

import java.util.*;
import java.util.stream.Collectors;

class VoteRecord {
    String key;
    int[] votes;

    public VoteRecord(String key, int[] votes) {
        this.key = key;
        this.votes = votes;
    }
}
public class OnlineVoting {

    public static void main(String[] args) {
        //onlineVoting3();
        new OnlineVoting().voting();
    }

    private void voting() {
        String[] votes = {"ABC","ACB","ABC","ACB","ACB"};
        Map<String, VoteRecord> teamVoteMap = getTeamToVotesMap(votes);
        findWinner(teamVoteMap);
    }

    private Map<String, VoteRecord> getTeamToVotesMap(String[] votes) {
         Map<String,VoteRecord> voteMap = new HashMap<>();
         for(String vote : votes) {
            for (int i=0; i<vote.length(); i++) {
                String key = String.valueOf(vote.charAt(i));
                VoteRecord record = voteMap.get(key);
                if (record == null) {
                    record = new VoteRecord(key, new int[vote.length()]);
                    voteMap.putIfAbsent(key, record);
                }
                record.votes[i]++;
             }
         }
         show(voteMap);
         return voteMap;
    }

    private void findWinner(Map<String, VoteRecord> voteMap) {
        List<VoteRecord> voteRecords = new ArrayList<>(voteMap.values());
        Collections.sort(voteRecords, new Comparator<VoteRecord>() {
            @Override
            public int compare(VoteRecord o1, VoteRecord o2) {
                int idx = 0;
                while(idx < o1.votes.length && idx < o2.votes.length){
                    if(o1.votes[idx] == o2.votes[idx]){
                        idx++;
                    }
                    else{
                        return o2.votes[idx] - o1.votes[idx];
                    }
                }
                //If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.
                return o1.key.compareTo(o2.key);
            }
        });
        show(voteRecords);
    }

    private void show(List<VoteRecord> voteRecords) {
        for(VoteRecord record: voteRecords){
            System.out.println(record.key +" :: "+ Arrays.toString(record.votes));
        }
    }

    private void show(Map<String, VoteRecord> voteMap) {
        for(String key: voteMap.keySet()){
            System.out.println(key +" :: "+ Arrays.toString(voteMap.get(key).votes));
        }
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
}

