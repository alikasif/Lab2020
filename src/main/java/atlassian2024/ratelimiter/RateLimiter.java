package atlassian2024.ratelimiter;

import atlassian2024.ratelimiter.dto.Result;
import atlassian2024.ratelimiter.policy.RateLimitConfig;

public abstract class RateLimiter {

    protected final RateLimitConfig config;

    protected RateLimiter(RateLimitConfig config) {
        this.config = config;
    }

    public abstract Result allow(String id, Long requestTime);
}