package atlassian2024.ratelimiter.dto;


public class Result {

    public enum ResultEnum{
        ACCEPTED,
        DENIED
    }

    public Result(ResultEnum result, Long reqTime){
        this.result = result;
        this.requestTime = reqTime;
    }

    ResultEnum result;
    Long requestTime;

    @Override
    public String toString() {
        return "Result{" +
                "result=" + result +
                ", requestTime=" + requestTime +
                '}';
    }
}
