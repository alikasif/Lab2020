package atlassian2024.ratelimiter;

import atlassian2024.ratelimiter.dto.Result;
import atlassian2024.ratelimiter.policy.RateLimitConfig;

import java.util.Queue;

public abstract class RateLimiter {

    protected final RateLimitConfig policy;

    protected RateLimiter(RateLimitConfig policy) {
        this.policy = policy;
    }

    public abstract Result allow(Queue<Long> queue, Long requestTime);
}