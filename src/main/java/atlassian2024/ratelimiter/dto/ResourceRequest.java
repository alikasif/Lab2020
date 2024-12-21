package atlassian2024.ratelimiter.dto;

import lombok.Getter;

import java.time.Instant;

@Getter
public class ResourceRequest {

    private final String id;
    private final Instant requestInstance;
    private final String resourcePath;

    public ResourceRequest(String id, Instant requestInstance, String resourcePath){
        this.id = id;
        this.resourcePath = resourcePath;
        this.requestInstance = requestInstance;
    }
}
