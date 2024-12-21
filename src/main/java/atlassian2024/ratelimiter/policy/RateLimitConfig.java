package atlassian2024.ratelimiter.policy;

import lombok.Getter;

public abstract class RateLimitConfig {

    @Getter
    final private int numRequest;

    @Getter
    final private int windowLength;

    protected RateLimitConfig(int numRequest, int windowLength) {
        this.numRequest = numRequest;
        this.windowLength = windowLength;
    }

    public abstract long getWindowKey(long millisTime);
}
