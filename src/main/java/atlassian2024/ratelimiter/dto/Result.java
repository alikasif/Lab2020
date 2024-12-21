package atlassian2024.ratelimiter.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

    public enum ResultEnum{
        ACCEPTED,
        DENIED
    }

    public Result(ResultEnum result, Long reqTime){
        this.result = result;
        this.requestTime = reqTime;
    }

    private ResultEnum result;
    private Long requestTime;
    private String resourcePath;
    private String id;

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                ", requestTime=" + requestTime +
                ", resourcePath='" + resourcePath + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
