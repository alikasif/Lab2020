package atlassian2024.voting;

import atlassian2024.voting.algo.IWinnerStrategy;

import java.util.*;

public class OnlineVoting {

    private final IWinnerStrategy winnerStrategy;
    private final List<String> votes;

    public OnlineVoting(IWinnerStrategy winnerStrategy){
        votes = new ArrayList<>();
        this.winnerStrategy = winnerStrategy;
    }

    public void addVote(String vote){
        votes.add(vote);
    }

    public List<VoteRecord> getWinners(){
       return winnerStrategy.findWinner(votes);
    }
}
