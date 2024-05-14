package atlassian2024.ratelimiter.policy;

import java.util.concurrent.TimeUnit;

public class InstantSecondConfig extends RateLimitConfig {

    public InstantSecondConfig(int numRequest, int windowLength) {
        super(numRequest, windowLength);
    }

    @Override
    public long getWindowKey(long millisTime) {
        return TimeUnit.MILLISECONDS.toSeconds(millisTime)/getWindowLength();
    }
}

