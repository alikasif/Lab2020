package atlassian2024.voting.algo;

import atlassian2024.voting.VoteRecord;

import java.util.*;


public class VoteCountAndPositionStrategy implements IWinnerStrategy{

    Map<Integer, Integer> positionWeightMap;
    public VoteCountAndPositionStrategy() {
        this.positionWeightMap = new HashMap<>();
        this.positionWeightMap.put(0,1);
        this.positionWeightMap.put(1,1);
        this.positionWeightMap.put(2,1);
    }

    public VoteCountAndPositionStrategy(Map<Integer, Integer> positionWeightMap) {
        super();

        if (positionWeightMap != null && !positionWeightMap.isEmpty())
            this.positionWeightMap = positionWeightMap;
    }

    @Override
    public List<VoteRecord> findWinner(List<String> votes) {

        Map<String, VoteRecord> voteMap = getTeamToVotesMap(votes);

        List<VoteRecord> voteRecords = new ArrayList<>(voteMap.values());
        Collections.sort(voteRecords, new Comparator<VoteRecord>() {
            @Override
            public int compare(VoteRecord vr1, VoteRecord vr2) {
                int idx = 0;
                while(idx < vr1.getPositionCountSize() && idx < vr2.getPositionCountSize()){
                    if (vr1.getPositionCount(idx) == vr2.getPositionCount(idx))
                        idx++;
                    else
                        return vr2.getPositionCount(idx) - vr1.getPositionCount(idx);
                }
                //If two or more teams are still tied after considering all positions, we rank them alphabetically based on their team letter.
                return vr1.getKey().compareTo(vr2.getKey());
            }
        });
        return voteRecords;
    }

    private Map<String, VoteRecord> getTeamToVotesMap( List<String> votes) {

        Map<String, VoteRecord> voteMap = new HashMap<>();

        for(String vote : votes) {
            for (int i=0; i<vote.length(); i++) {
                String key = String.valueOf(vote.charAt(i));
                voteMap.putIfAbsent(key, new VoteRecord(key, new int[vote.length()]));
                VoteRecord record = voteMap.get(key);
                record.increasePositionalCount(i, positionWeightMap.get(i));
            }
        }
        return voteMap;
    }
}
