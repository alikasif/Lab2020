package voting;

import atlassian2024.voting.OnlineVoting;
import atlassian2024.voting.VoteRecord;
import atlassian2024.voting.algo.VoteCountAndPositionStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VotingTest {

    @Test
    public void voting() {
        //onlineVoting3();
        //  {"ABC","ACB","ABC","ACB","ACB"};
        OnlineVoting onlineVoting = new OnlineVoting(new VoteCountAndPositionStrategy());

        onlineVoting.addVote("ABC");
        System.out.println(onlineVoting.getWinners());

        onlineVoting.addVote("ACB");
        System.out.println(onlineVoting.getWinners());

        onlineVoting.addVote("ABC");
        System.out.println(onlineVoting.getWinners());

        onlineVoting.addVote("ACB");
        System.out.println(onlineVoting.getWinners());

        onlineVoting.addVote("CAB");
        System.out.println(onlineVoting.getWinners());
    }

    @Test
    public void getWinnerOnEmpty() {
        OnlineVoting onlineVoting = new OnlineVoting(new VoteCountAndPositionStrategy());
        Assert.assertTrue(onlineVoting.getWinners().isEmpty());
    }

    @Test
    public void tieOnAllPosition() {
        OnlineVoting onlineVoting = new OnlineVoting(new VoteCountAndPositionStrategy());

        onlineVoting.addVote("ABC");
        onlineVoting.addVote("BCA");
        onlineVoting.addVote("CAB");

        List<VoteRecord> winners = onlineVoting.getWinners();

        Assert.assertEquals("A", winners.get(0).getKey());
        Assert.assertEquals("B", winners.get(1).getKey());
        Assert.assertEquals("C", winners.get(2).getKey());
    }

    @Test
    public void tieOnAny2Position() {
        OnlineVoting onlineVoting = new OnlineVoting(new VoteCountAndPositionStrategy());

        onlineVoting.addVote("ABC");
        onlineVoting.addVote("ACB");

        List<VoteRecord> winners = onlineVoting.getWinners();

        Assert.assertEquals("A", winners.get(0).getKey());
        Assert.assertEquals("B", winners.get(1).getKey());
        Assert.assertEquals("C", winners.get(2).getKey());
    }

    @Test
    public void winnerWithoutTie() {
        OnlineVoting onlineVoting = new OnlineVoting(new VoteCountAndPositionStrategy());

        onlineVoting.addVote("ABC");
        onlineVoting.addVote("ACB");
        onlineVoting.addVote("ABC");

        List<VoteRecord> winners = onlineVoting.getWinners();

        Assert.assertEquals("A", winners.get(0).getKey());
        Assert.assertEquals("B", winners.get(1).getKey());
        Assert.assertEquals("C", winners.get(2).getKey());
    }

    @Test
    public void duplicateSingleInput() {
        OnlineVoting onlineVoting = new OnlineVoting(new VoteCountAndPositionStrategy());

        onlineVoting.addVote("AAA");

        List<VoteRecord> winners = onlineVoting.getWinners();

        Assert.assertEquals("A", winners.get(0).getKey());
        System.out.println(winners);
    }

    @Test
    public void duplicateMultipleInput() {
        OnlineVoting onlineVoting = new OnlineVoting(new VoteCountAndPositionStrategy());

        onlineVoting.addVote("AAA");
        onlineVoting.addVote("BBB");
        onlineVoting.addVote("CCC");

        List<VoteRecord> winners = onlineVoting.getWinners();

        Assert.assertEquals("A", winners.get(0).getKey());
        Assert.assertEquals("B", winners.get(1).getKey());
        Assert.assertEquals("C", winners.get(2).getKey());
    }

    @Test
    public void MultipleInputPosition() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.put(1, 2);
        map.put(2, 3);

        OnlineVoting onlineVoting = new OnlineVoting(new VoteCountAndPositionStrategy(map));

        onlineVoting.addVote("AAA");
        onlineVoting.addVote("ABA");
        onlineVoting.addVote("CAC");
        onlineVoting.addVote("BCC");

        List<VoteRecord> winners = onlineVoting.getWinners();
        System.out.println(winners);

        Assert.assertEquals("A", winners.get(0).getKey());
        Assert.assertEquals("C", winners.get(1).getKey());
        Assert.assertEquals("B", winners.get(2).getKey());
    }

}
