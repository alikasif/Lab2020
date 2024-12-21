package atlassian2024.ratelimiter.policy;

public class SlidingWindowSecondConfig extends RateLimitConfig {

    public SlidingWindowSecondConfig(int numRequest, int windowLength) {
        super(numRequest, windowLength);
    }

    @Override
    public long getWindowKey(long millisTime) {
        return millisTime - (getWindowLength() * 1000L);
    }
}
