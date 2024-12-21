package atlassian2024.voting;


import lombok.Getter;

import java.util.Arrays;

public class VoteRecord {

    @Getter
    String key;

    int[] positionCount;

    public VoteRecord(String key, int[] votes) {
        this.key = key;
        this.positionCount = votes;
    }

    public void increasePositionalCount(int pos, int count) {
        positionCount[pos]+=count;
    }

    public int getPositionCount(int idx) {
        return positionCount[idx];
    }

    public int getPositionCountSize(){
        return positionCount.length;
    }

    @Override
    public String toString() {
        return "VoteRecord{" +
                "key='" + key + '\'' +
                ", votes=" + Arrays.toString(positionCount) +
                '}';
    }
}