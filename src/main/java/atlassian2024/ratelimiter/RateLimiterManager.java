package atlassian2024.ratelimiter;

import atlassian2024.ratelimiter.dto.ResourceRequest;
import atlassian2024.ratelimiter.dto.Result;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
    Assumptions
    We have rate limiter against each resource
    Rate limit is per id level and not at resource level.
    Same id can access multiple resource as per the resource rate limit config
 */

public class RateLimiterManager {

    private final Map<String, RateLimiter> resourceToRateLimiterMapping;

    public RateLimiterManager(Map<String, RateLimiter> resourceToRateLimiterMapping) {
        this.resourceToRateLimiterMapping = new ConcurrentHashMap<>(resourceToRateLimiterMapping);
    }

    public void addPathToRateLimiterMapping(String path, RateLimiter rateLimiter) {

        if (StringUtils.isBlank(path) || rateLimiter == null)
            throw new IllegalArgumentException("incorrect config");

        this.resourceToRateLimiterMapping.put(path, rateLimiter);
    }

    public Result isAllowed(ResourceRequest resourceRequest) {

        RateLimiter rateLimiter = resourceToRateLimiterMapping.get(resourceRequest.getResourcePath());

        if (rateLimiter == null){
            System.out.println("resource is not protected through rate limiter !! Accepting the request");
            return new Result(Result.ResultEnum.ACCEPTED, resourceRequest.getRequestInstance().toEpochMilli());
        }

        System.out.print("request Path :: "+ resourceRequest.getResourcePath() +" ");
        Result result = rateLimiter.allow(resourceRequest.getId(), resourceRequest.getRequestInstance().toEpochMilli());
        result.setId(resourceRequest.getId());
        result.setResourcePath(resourceRequest.getResourcePath());
        return result;
    }
}
