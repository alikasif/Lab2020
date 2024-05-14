package atlassian2024.ratelimiter.dto;

import java.time.Instant;

public class ResourceRequest {

    private final String id;
    private final Instant requestInstance;

    public ResourceRequest(String id, Instant requestInstance){
        this.id = id;
        this.requestInstance = requestInstance;
    }

    public String getId() {
        return id;
    }

    public Instant getRequestInstance() {
        return requestInstance;
    }
}
