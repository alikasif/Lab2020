package atlassian2024.ratelimiter.policy;

public abstract class RateLimitConfig {

    final private int numRequest;
    final private int windowLength;

    protected RateLimitConfig(int numRequest, int windowLength) {
        this.numRequest = numRequest;
        this.windowLength = windowLength;
    }

    public int getNumRequest() {
        return numRequest;
    }

    public int getWindowLength() {
        return windowLength;
    }

    public abstract long getWindowKey(long millisTime);
}
