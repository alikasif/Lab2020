package atlassian2024.voting.algo;

import atlassian2024.voting.VoteRecord;

import java.util.List;

public interface IWinnerStrategy {
    List<VoteRecord> findWinner(List<String> votes);
}
