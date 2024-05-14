package ratelimiter;

import atlassian2024.ratelimiter.*;
import atlassian2024.ratelimiter.dto.ResourceRequest;
import atlassian2024.ratelimiter.dto.Result;
import atlassian2024.ratelimiter.impl.FixedWindowCounter;
import atlassian2024.ratelimiter.impl.SlidingWindow2;
import atlassian2024.ratelimiter.impl.SlidingWindowLog;
import atlassian2024.ratelimiter.policy.MinuteConfig;
import atlassian2024.ratelimiter.policy.SecondConfig;
import atlassian2024.ratelimiter.policy.SlidingWindowSecondConfig;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RateLimiterTest {

    @Test
    public void testFixedWindow() {
        RateLimiter rateLimiter = new FixedWindowCounter(new MinuteConfig(3, 2));

    }

    @Test
    public void testSlidingWindowLog() {
        RateLimiter rateLimiter = new SlidingWindowLog(new SecondConfig(3, 1));

    }

    @Test
    public void testSlidingWindowLog2() {
        RateLimiter rateLimiter = new SlidingWindow2(new SlidingWindowSecondConfig(5, 2));
        RateLimiterManager manager = new RateLimiterManager(rateLimiter);
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
            Result result = rateLimiter.isAllowed(new ResourceRequest("KASIF", Instant.now()));
            //System.out.println("time " + TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()) + " isAllowed " + isAllowed);
        }
    }

    private int getSleepInterval(){
        Random r = new Random();
        int low = 100;
        int high = 200;
        int result = r.nextInt(high-low) + low;
        return result;
    }
}
