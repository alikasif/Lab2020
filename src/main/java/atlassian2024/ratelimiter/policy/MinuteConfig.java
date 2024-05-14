package atlassian2024.ratelimiter.policy;

import java.util.concurrent.TimeUnit;

public class MinuteConfig extends RateLimitConfig {

    public MinuteConfig(int numRequest, int windowLength) {
        super(numRequest, windowLength);
    }

    @Override
    public long getWindowKey(long millisTime) {

        return TimeUnit.MILLISECONDS.toMinutes(millisTime)/getWindowLength();
    }
}
