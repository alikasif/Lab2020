package atlassian2024.popularproduct.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

enum VoteValue{
    UP,
    DOWN
}

class Vote {

    @Getter
    Long voteTime;
    @Getter
    VoteValue voteValue;

    public Vote(VoteValue voteValue, Long voteTime) {
        this.voteValue = voteValue;
        this.voteTime = voteTime;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "voteTime=" + voteTime +
                ", voteValue=" + voteValue +
                '}';
    }
}

public class Content {

    @Getter
    private Integer contentId;
    private List<Vote> votes;
    private Vote lastUpVote;

    public Content(Integer contentId){
        this.contentId = contentId;
        votes = new ArrayList<>();
    }

    public Long getLastUpVoteTime()  {
        return lastUpVote.getVoteTime();
    }

    public Integer getTotalUpVotes() { return votes.size();}

    public void upVote(long voteTime) {
        Vote vote = new Vote(VoteValue.UP, voteTime);
        this.votes.add(vote);
        if (lastUpVote == null)
            lastUpVote = vote;
        else {
            if (voteTime > lastUpVote.voteTime) {
                lastUpVote = vote;
            }
        }
    }

    public void downVote(long voteTime) {
        Collections.sort(votes, new Comparator<Vote>() {
            @Override
            public int compare(Vote o1, Vote o2) {
                return o1.voteTime.compareTo(o2.voteTime);
            }
        });

        // remove only if down vote time is after earliest vote
        if (voteTime >= votes.get(0).voteTime) {
            votes.remove(0);
        }
        else {
            System.out.println("invalid down vote time "+voteTime +" earliest vote " + votes.get(0));
        }
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentId=" + contentId +
                ", votes=" + votes.size() +
                ", lastUpVote=" + lastUpVote +
                '}';
    }
}
