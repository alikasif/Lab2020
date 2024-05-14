package atlassian2024.ratelimiter.impl;

import atlassian2024.ratelimiter.RateLimiter;
import atlassian2024.ratelimiter.dto.Result;
import atlassian2024.ratelimiter.policy.RateLimitConfig;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowCounter extends RateLimiter {

    // TODO: Clean up stale entries
    private final ConcurrentMap<Long, AtomicInteger> windows = new ConcurrentHashMap<>();

    public FixedWindowCounter(RateLimitConfig policy) {
        super(policy);
    }

    @Override
    public Result allow(Queue<Long> queue, Long requestTime) {
        return null;
    }

    public Result allow2() {
        long windowKey = policy.getWindowKey(System.currentTimeMillis());
        windows.putIfAbsent(windowKey, new AtomicInteger(0));
        windows.get(windowKey).incrementAndGet();
        var result =  windows.get(windowKey).intValue() <= policy.getNumRequest();
        System.out.println(windows + " :: " + result);
        return new Result(result? Result.ResultEnum.ACCEPTED: Result.ResultEnum.DENIED, windowKey);
    }
}