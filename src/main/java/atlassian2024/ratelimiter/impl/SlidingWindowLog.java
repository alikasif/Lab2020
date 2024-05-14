package atlassian2024.ratelimiter.impl;

import atlassian2024.ratelimiter.RateLimiter;
import atlassian2024.ratelimiter.dto.Result;
import atlassian2024.ratelimiter.policy.RateLimitConfig;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowLog extends RateLimiter {

    private final Queue<Long> log = new LinkedList<>();
    private final Queue<Instant> log2 = new LinkedList<>();

    public SlidingWindowLog(RateLimitConfig policy) {
        super(policy);
    }

    @Override
    public Result allow(Queue<Long> queue, Long requestTime) {
        return null;
    }

    public Result allow() {
        long windowKey = policy.getWindowKey(System.currentTimeMillis());
        while (!log.isEmpty() && log.element() < windowKey) {
            log.poll();
        }
        log.add(windowKey);
        var result = log.size() <= policy.getNumRequest();
        System.out.println("log ::" + log + " result :: "+result);
        return new Result(result? Result.ResultEnum.ACCEPTED: Result.ResultEnum.DENIED, windowKey);
    }

}