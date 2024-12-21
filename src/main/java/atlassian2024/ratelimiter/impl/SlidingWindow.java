package atlassian2024.ratelimiter.impl;

import atlassian2024.ratelimiter.RateLimiter;
import atlassian2024.ratelimiter.dto.Result;
import atlassian2024.ratelimiter.policy.RateLimitConfig;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindow extends RateLimiter {

    private Map<String, Queue<Long>> idToRequestMap;
    public SlidingWindow(RateLimitConfig rateLimitConfig) {
        super(rateLimitConfig);
        idToRequestMap = new ConcurrentHashMap<>();
    }

    @Override
    public Result allow(String id, Long requestTime) {

        idToRequestMap.putIfAbsent(id, new LinkedList<>());
        Queue<Long> queue = idToRequestMap.get(id);

        clearExpiredRequests(requestTime, queue); // Clear expired requests from the queue

        Result result = null;

        if (queue.size() < config.getNumRequest()) {
            queue.offer(requestTime); // Add current request timestamp to the queue
            result =  new Result(Result.ResultEnum.ACCEPTED, null);
        }
        else {
            result =  new Result(Result.ResultEnum.DENIED, null);
        }

        System.out.println("Result :: "+ result +" Queue ::" + queue);
        return result;
    }

    private void clearExpiredRequests(long currentTimeMillis, Queue<Long> queue) {
        Long windowStart = config.getWindowKey(currentTimeMillis);
        System.out.println("expiring values older than :: " + windowStart);
        while (!queue.isEmpty() && queue.element() < windowStart) {
            queue.poll(); // Remove expired requests from the queue
        }
    }
}
