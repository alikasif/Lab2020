package popularproduct;


import atlassian2024.popularproduct.IMostPopular;
import atlassian2024.popularproduct.MostPopular;
import atlassian2024.popularproduct.PopularityTracker;
import atlassian2024.popularproduct.algo.PopularByCountAndLastVoteTime;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class MostPopularTest {

    @Test
    public void testPopularity(){

        IMostPopular popularityTracker = new MostPopular();
        long voteTimeinMillis = Instant.now().toEpochMilli();

        System.out.println("voting at time "+voteTimeinMillis);

        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(8);
        Integer actual = popularityTracker.mostPopular();
        System.out.println(actual);

      /*  Assert.assertEquals("7",String.valueOf(actual));

        popularityTracker.increasePopularity(8, voteTimeinMillis);
        popularityTracker.increasePopularity(8, voteTimeinMillis+101L);
        actual = popularityTracker.mostPopular();
        System.out.println(actual);
        Assert.assertEquals("8",String.valueOf(actual));

        popularityTracker.decreasePopularity(8, voteTimeinMillis+300L);
        popularityTracker.decreasePopularity(8, voteTimeinMillis+250L);
        actual = popularityTracker.mostPopular();
        System.out.println(actual);
        Assert.assertEquals("7",String.valueOf(actual));*/

        /*popularityTracker.decreasePopularity(7);
        popularityTracker.decreasePopularity(7);
        popularityTracker.decreasePopularity(8);
        actual = popularityTracker.mostPopular();
        System.out.println(actual);//-1
        Assert.assertEquals("-1",String.valueOf(actual));*/
    }

    @Test
    public void testPopularity2() throws  Exception{
        IMostPopular popularityTracker = new MostPopular();
        long voteTimeinMillis = Instant.now().toEpochMilli();

        popularityTracker.increasePopularity(7);
        TimeUnit.SECONDS.sleep(1);
        popularityTracker.increasePopularity(8);
        Integer actual = popularityTracker.mostPopular();
        System.out.println(actual);
        TimeUnit.SECONDS.sleep(1);
        popularityTracker.increasePopularity(8);
        TimeUnit.SECONDS.sleep(1);
        popularityTracker.increasePopularity(7);
        actual = popularityTracker.mostPopular();
        System.out.println(actual);
    }

    @Test
    public void testPopularity3() {

        IMostPopular popularityTracker = new PopularityTracker(new PopularByCountAndLastVoteTime());
        long voteTimeinMillis = Instant.now().toEpochMilli();

        System.out.println("voting at time " + voteTimeinMillis);

        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(8);
        Integer actual = popularityTracker.mostPopular();
        System.out.println(actual);

        Assert.assertEquals("7",String.valueOf(actual));

        popularityTracker.increasePopularity(8);
        actual = popularityTracker.mostPopular();
        System.out.println(actual);
        Assert.assertEquals("7",String.valueOf(actual));

        popularityTracker.increasePopularity(8);
        actual = popularityTracker.mostPopular();
        System.out.println(actual);
        Assert.assertEquals("8",String.valueOf(actual));

        popularityTracker.decreasePopularity(8);
        popularityTracker.decreasePopularity(8);
        actual = popularityTracker.mostPopular();
        System.out.println(actual);
        Assert.assertEquals("7",String.valueOf(actual));

        popularityTracker.decreasePopularity(8);
        actual = popularityTracker.mostPopular();
        System.out.println(actual);
        Assert.assertEquals("7",String.valueOf(actual));

    }

    @Test
    public void testPopularity4() {

        IMostPopular popularityTracker = new PopularityTracker(new PopularByCountAndLastVoteTime());
        long voteTimeinMillis = Instant.now().toEpochMilli();

        popularityTracker.mostPopular();

        System.out.println("voting at time " + voteTimeinMillis);

        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(8);
        Integer actual = popularityTracker.mostPopular();
        System.out.println(actual);
        Assert.assertEquals("7",String.valueOf(actual));

        popularityTracker.increasePopularity(8);
        actual = popularityTracker.mostPopular();
        System.out.println("***** "+actual);
        Assert.assertEquals("8",String.valueOf(actual));

        popularityTracker.increasePopularity(8);
        actual = popularityTracker.mostPopular();
        System.out.println(actual);
        Assert.assertEquals("8",String.valueOf(actual));

        popularityTracker.decreasePopularity(8);
        popularityTracker.decreasePopularity(8);
        actual = popularityTracker.mostPopular();
        System.out.println(actual);
        Assert.assertEquals("7",String.valueOf(actual));

        popularityTracker.decreasePopularity(8);
        actual = popularityTracker.mostPopular();
        System.out.println(actual);
        Assert.assertEquals("7",String.valueOf(actual));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonExistentDownVote(){
        IMostPopular popularityTracker = new PopularityTracker(new PopularByCountAndLastVoteTime());
        popularityTracker.decreasePopularity(1);
    }

    @Test
    public void testMostPopularOnEmpty(){
        IMostPopular popularityTracker = new PopularityTracker(new PopularByCountAndLastVoteTime());
        Assert.assertNull(popularityTracker.mostPopular());
    }

    @Test
    public void testPopularityByCount() {
        IMostPopular popularityTracker = new PopularityTracker(new PopularByCountAndLastVoteTime());
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(8);
        Integer actual = popularityTracker.mostPopular();
        Assert.assertEquals("7",String.valueOf(actual));
    }

    @Test
    public void testPopularityByCountAndLastTime() {
        IMostPopular popularityTracker = new PopularityTracker(new PopularByCountAndLastVoteTime());
        popularityTracker.increasePopularity(7);
        popularityTracker.increasePopularity(8);
        popularityTracker.increasePopularity(8);
        popularityTracker.increasePopularity(7);
        Integer actual = popularityTracker.mostPopular();
        Assert.assertEquals("7",String.valueOf(actual));
    }
}
