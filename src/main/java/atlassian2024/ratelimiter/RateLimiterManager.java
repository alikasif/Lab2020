package atlassian2024.ratelimiter;

import atlassian2024.ratelimiter.dto.ResourceRequest;
import atlassian2024.ratelimiter.dto.Result;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiterManager {

    private final RateLimiter rateLimiter;
    private Map<String, Queue<Long>> idToRequestMap = new ConcurrentHashMap();

    public RateLimiterManager(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    public Result isAllowed(ResourceRequest resourceRequest) {
        Queue<Long> requestQueue = idToRequestMap.getOrDefault(resourceRequest.getId(), new LinkedList<>());

        idToRequestMap.putIfAbsent(resourceRequest.getId(), requestQueue);

        Result result = rateLimiter.allow(requestQueue, resourceRequest.getRequestInstance().toEpochMilli());
        System.out.println("request at "+ resourceRequest.getRequestInstance().toEpochMilli() + " processed "+result + " requestQ "+requestQueue);
        return result;
    }
}
