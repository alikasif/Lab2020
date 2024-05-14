package atlassian2024.ratelimiter.impl;

import atlassian2024.ratelimiter.RateLimiter;
import atlassian2024.ratelimiter.dto.Result;
import atlassian2024.ratelimiter.policy.RateLimitConfig;

import java.util.Queue;

public class SlidingWindow2 extends RateLimiter {

    public SlidingWindow2(RateLimitConfig rateLimitConfig) {
        super(rateLimitConfig);
    }

    @Override
    public Result allow(Queue<Long> queue, Long requestTime) {

        clearExpiredRequests(requestTime, queue); // Clear expired requests from the queue

        Result result = null;

        if (queue.size() < policy.getNumRequest()) {
            queue.offer(requestTime); // Add current request timestamp to the queue
            result =  new Result(Result.ResultEnum.ACCEPTED, null);
        } else {
            result =  new Result(Result.ResultEnum.DENIED, null);
        }
        return result;
    }


    private void clearExpiredRequests(long currentTimeMillis, Queue<Long> queue) {
        Long windowStart = policy.getWindowKey(currentTimeMillis);
        System.out.println("expiring values older than :: " + windowStart);
        while (!queue.isEmpty() && queue.element() < windowStart) {
            queue.poll(); // Remove expired requests from the queue
        }
    }
}
