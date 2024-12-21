package ratelimiter;

import atlassian2024.ratelimiter.*;
import atlassian2024.ratelimiter.dto.ResourceRequest;
import atlassian2024.ratelimiter.dto.Result;
import atlassian2024.ratelimiter.impl.FixedWindowCounter;
import atlassian2024.ratelimiter.impl.SlidingWindow;
import atlassian2024.ratelimiter.policy.MinuteConfig;
import atlassian2024.ratelimiter.policy.SlidingWindowSecondConfig;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RateLimiterTest {

    @Test
    public void testFixedWindow() {
        RateLimiter rateLimiter = new FixedWindowCounter(new MinuteConfig(3, 2));
    }

    @Test
    public void testSlidingWindow() {

        RateLimiter rateLimiter = new SlidingWindow(new SlidingWindowSecondConfig(5, 2));
        RateLimiterManager manager = new RateLimiterManager(new HashMap<>());
        manager.addPathToRateLimiterMapping("getHotels", rateLimiter);
        manager.addPathToRateLimiterMapping("getRooms", rateLimiter);

        sendRequest3(manager, 30);
    }

    private void sendRequest3(RateLimiterManager rateLimiter, int totalCnt) {

        int i=0;
        while (totalCnt > 0) {
            totalCnt--;
            try {
                TimeUnit.MILLISECONDS.sleep(getSleepInterval());
                if ( i++ > 10){
                    System.out.println("1 sec done");
                    i=0;
                }
            } catch (Exception e) {
            }
            Result result = rateLimiter.isAllowed(new ResourceRequest("KASIF", Instant.now(), getPath()));
            //System.out.println("time " + TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()) + " isAllowed " + isAllowed);
        }
    }

    private String getPath() {
        if ( getSleepInterval()%2 == 0)
            return "getHotels";
        return "getRooms";
    }

    private int getSleepInterval(){
        Random r = new Random();
        int low = 100;
        int high = 200;
        int result = r.nextInt(high-low) + low;
        return result;
    }

    @Test
    public void testManager() {
        RateLimiter rateLimiter = new SlidingWindow(new SlidingWindowSecondConfig(5, 2));
        RateLimiterManager manager = new RateLimiterManager(new HashMap<>());
        Result result = manager.isAllowed(new ResourceRequest("KASIF", Instant.now(), getPath()));
        Assert.assertEquals(Result.ResultEnum.ACCEPTED, result.getResult());
    }

    @Test
    public void testManagerWithoutPathSetAndRequestLoad() {
        RateLimiter rateLimiter = new SlidingWindow(new SlidingWindowSecondConfig(3, 1));
        RateLimiterManager manager = new RateLimiterManager(new HashMap<>());
        for(int i=0; i<10; i++) {
            Result result = manager.isAllowed(new ResourceRequest("KASIF", Instant.now(), getPath()));
            Assert.assertEquals(Result.ResultEnum.ACCEPTED, result.getResult());
        }
    }

    @Test
    public void testRateLimitThrottle() {
        RateLimiter rateLimiter = new SlidingWindow(new SlidingWindowSecondConfig(3, 1));
        RateLimiterManager manager = new RateLimiterManager(new HashMap<>());
        manager.addPathToRateLimiterMapping("getHotels", rateLimiter);
        Instant requestInstance = Instant.now();
        for(int i=0; i<10; i++) {
            Result result = manager.isAllowed(new ResourceRequest("KASIF", requestInstance , "getHotels"));
            if (i < 3)
                Assert.assertEquals(Result.ResultEnum.ACCEPTED, result.getResult());
            else
                Assert.assertEquals(Result.ResultEnum.DENIED, result.getResult());
        }
    }

    @Test
    public void testSameRateLimitForMultiplePath() {
        RateLimiter rateLimiter = new SlidingWindow(new SlidingWindowSecondConfig(3, 1));
        RateLimiterManager manager = new RateLimiterManager(new HashMap<>());
        manager.addPathToRateLimiterMapping("getHotels", rateLimiter);
        manager.addPathToRateLimiterMapping("getRooms", rateLimiter);
        Instant requestInstance = Instant.now();

        for (int i = 0; i < 10; i++) {
            String path = i < 5 ? "getHotels" : "getRooms";
            Result result = manager.isAllowed(new ResourceRequest("KASIF", requestInstance, path));

            if (i < 3 && path.equals("getHotels"))
                Assert.assertEquals(Result.ResultEnum.ACCEPTED, result.getResult());

            else if (i >= 3 && i < 5 && path.equals("getHotels"))
                Assert.assertEquals(Result.ResultEnum.DENIED, result.getResult());

            else if (i >= 5 && i < 8 && path.equals("getRooms"))
                Assert.assertEquals(Result.ResultEnum.DENIED, result.getResult());

            else
                Assert.assertEquals(Result.ResultEnum.DENIED, result.getResult());
        }
    }

        @Test
        public void testSameRateLimitForSomePath() {
            RateLimiter rateLimiter = new SlidingWindow(new SlidingWindowSecondConfig(3, 1));
            RateLimiterManager manager = new RateLimiterManager(new HashMap<>());
            manager.addPathToRateLimiterMapping("getHotels", rateLimiter);
            Instant requestInstance = Instant.now();

            for(int i=0; i<10; i++) {
                String path = i < 5 ? "getHotels" :"getRooms";
                Result result = manager.isAllowed(new ResourceRequest("KASIF", requestInstance, path));

                if (i < 3 && path.equals("getHotels"))
                    Assert.assertEquals(Result.ResultEnum.ACCEPTED, result.getResult());

                else if (i >= 3 && i < 5 && path.equals("getHotels"))
                    Assert.assertEquals(Result.ResultEnum.DENIED, result.getResult());

                else if ( path.equals("getRooms"))
                    Assert.assertEquals(Result.ResultEnum.ACCEPTED, result.getResult());

                else
                    Assert.assertEquals(Result.ResultEnum.DENIED, result.getResult());
            }
        }


}
