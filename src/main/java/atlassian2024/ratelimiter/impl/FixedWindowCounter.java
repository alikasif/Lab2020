package atlassian2024.ratelimiter.impl;

import atlassian2024.ratelimiter.RateLimiter;
import atlassian2024.ratelimiter.dto.Result;
import atlassian2024.ratelimiter.policy.RateLimitConfig;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowCounter extends RateLimiter {

    final Map<Long, AtomicInteger> windows;
    public FixedWindowCounter(RateLimitConfig policy) {
        super(policy);
        windows = new ConcurrentHashMap<>();
    }

    @Override
    public Result allow(String id, Long requestTime) {
        long windowKey = config.getWindowKey(System.currentTimeMillis());
        windows.putIfAbsent(windowKey, new AtomicInteger(0));
        windows.get(windowKey).incrementAndGet();
        var result =  windows.get(windowKey).intValue() <= config.getNumRequest();
        System.out.println(windows + " :: " + result);
        return new Result(result? Result.ResultEnum.ACCEPTED: Result.ResultEnum.DENIED, windowKey);
    }
}