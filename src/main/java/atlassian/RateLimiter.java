package atlassian;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class RateLimiter {
    public static void main(String[] args) {
        //testTokenBucket();
        // testLeakyBucket();
        //testFixedWindow();
        //testSlidingWindow();
        testSlidingWindowWithCounter();
    }

    static void testRateLimiter() {
    }

    static void testSlidingWindowWithCounter() {
        SlidingWindowCounter slidingWindowCounter = new SlidingWindowCounter(7, 10);
        int i=0;
        while (true){
            i++;
            boolean allowed = slidingWindowCounter.isAllowed(String.valueOf(i), System.currentTimeMillis());
            System.out.println(allowed);
            try {
                Thread.sleep((long)(1 * 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void testSlidingWindow() {

        /*
            ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
            arrayDeque.offerFirst(1);
            arrayDeque.offerFirst(2);
            arrayDeque.offerFirst(3);
            System.out.println(arrayDeque.poll());
            for(long t : arrayDeque)
            System.out.println(t);
        */

        IRateLimiter rateLimiter = new SlidingWindowLogs(3, 10*1000);
        int i = 0;
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(rateLimiter.isAllowed(String.valueOf(++i), System.currentTimeMillis()));
        }
    }

    static void testFixedWindow() {
        IRateLimiter rateLimiter = new FixedWindow(3);
        int i=0;
        while (true) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(rateLimiter.isAllowed(String.valueOf(++i), System.currentTimeMillis()));
        }
    }

    static void testLeakyBucket() {
        IRateLimiter rateLimiter = new LeakyBucket(5, 2);
        int i=0;
        while (true){
            System.out.println(rateLimiter.isAllowed(String.valueOf(i++), 0));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void testTokenBucket() {

        IRateLimiter rateLimiter = new TokenBucket(3, 1);
        int i=0;
        while(true) {
            System.out.println(rateLimiter.isAllowed(String.valueOf(i++), System.currentTimeMillis()));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class RateLimiterService {
    IRateLimiter rateLimiter;
    public RateLimiterService(int requestThreshold){
        rateLimiter = new FixedWindow(requestThreshold);
    }
}

class SlidingWindowCounter implements IRateLimiter {

    long millisToGoBack;
    Map<Long, Integer> counter;
    long currentWindow;
    long prevWindow;
    int capacity;
    int windowLength;
    int bucketSizeInSeconds = 2;

    public SlidingWindowCounter(int capacity, int seconds) { // (7 request / 10 seconds)

        this.counter = new HashMap<>();
        this.capacity = capacity;
        this.millisToGoBack = seconds * 1000L;
        this.windowLength = seconds;

        currentWindow = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        prevWindow = TimeUnit.MILLISECONDS.toSeconds(currentWindow -  millisToGoBack);

        counter.put(currentWindow,0);
        counter.put(prevWindow, 0);

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(60 * 1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("starting new window");
                    prevWindow = currentWindow;
                    currentWindow = System.currentTimeMillis();
                    counter.put(currentWindow,0);
                }
            }
        }).start();*/
    }

    @Override
    public boolean isAllowed(String s, long ts) {

        System.out.println("checking request at " + ts);

        long currentWindow = TimeUnit.MILLISECONDS.toSeconds(ts)/bucketSizeInSeconds;
        Integer currentCount = counter.get(this.currentWindow);
        if(currentCount == null)
            currentCount = 0;

        int totalReqs = 0;
        long oldestTime = 0;
        for(Long times : counter.keySet()) {
            if(currentWindow - times < windowLength) {
                totalReqs += counter.get(times);
                oldestTime = times;
            }
        }
        System.out.println("currentWindow " + currentWindow +" currentCount "+ currentCount);
        System.out.println( "prevCount "+ totalReqs);

        long elapsedTime = currentWindow - oldestTime;
        long prevWindowWeightedCount = totalReqs * (this.currentWindow - elapsedTime)/ this.currentWindow;

        System.out.println("previousWindow " + prevWindow +" weightedCount "+ prevWindowWeightedCount);

        if(currentCount + prevWindowWeightedCount < capacity) {
            counter.put(currentWindow, currentCount+1);
            return true;
        }
        return false;
    }
}

class SlidingWindowLogs implements IRateLimiter {

    ArrayDeque<Long> tsQueue;
    int capacity;
    int millisToGoBack;

    public SlidingWindowLogs(int c, int millisToGoBack) {
        this.capacity = c;
        this.millisToGoBack = millisToGoBack;
        this.tsQueue = new ArrayDeque<>();
    }

    @Override
    public boolean isAllowed(String s, long ts) {

        long oldestTS = ts - millisToGoBack;
        System.out.println("will check until "+ oldestTS);
        int c = 0;

        for(long t : tsQueue) {
            if (t > oldestTS) {
                System.out.println("request until "+ t +" are "+ c);
                c++;
            }
            else {
                System.out.println("breaking. request until "+ t +" are "+ c);
                break;
            }
        }

        if (c >= capacity) {
            return false;
        }
        else {
            tsQueue.offerFirst(ts);
            return true;
        }
    }
}
class FixedWindow implements IRateLimiter {

    long currentTime;
    int requests;
    int capacity;

    Map<Long, Integer> requestCounter;

    /*
        3 request per 5 second. sleep every second and then reset the counter
     */
    public FixedWindow(int capacity) {
        this.capacity = capacity;
        this.requestCounter = new HashMap<>();

        currentTime = System.currentTimeMillis();
        requestCounter.put(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()),0);

/*        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000); // 1 seconds
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    currentTime = System.currentTimeMillis();
                    requestCounter.put(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()),0);
                }
            }
        }).start();*/
    }

    @Override
    public boolean isAllowed(String s, long ts) {
        //long timeInSeconds = ts / 1000;
        long timeInSeconds = TimeUnit.MILLISECONDS.toSeconds(ts);
        Integer currentReqCount = requestCounter.get(timeInSeconds);
        if(currentReqCount == null) {
            System.out.println("added new window "+ timeInSeconds + " count "+ currentReqCount);
            requestCounter.put(timeInSeconds, 1);
            return true;
        }
        else if(currentReqCount < capacity) {
            System.out.println("incremented count of window "+ timeInSeconds + " count "+ currentReqCount);
            requestCounter.put(timeInSeconds, currentReqCount+1);
            return true;
       }
        else {
            System.out.println("count exceeded for current window "+ timeInSeconds + " count "+ currentReqCount);
            return false;
        }
    }
}
class LeakyBucket implements IRateLimiter {

    Queue<String> bucket;
    int capacity;
    int leakRate;

    public LeakyBucket(int capacity, int leakRate) {
        bucket = new ArrayDeque<>(capacity);
        this.capacity = capacity;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if(bucket.size() > 0) {
                        int i = leakRate;
                        while (i>0) {
                            String poll = bucket.poll();
                            if(poll == null) {
                                System.out.println(Thread.currentThread().getName() +" nothing to process..");
                                break;
                            }
                            System.out.println(Thread.currentThread().getName() +" processing " + poll);
                            i--;
                        }
                    }
                    else {
                        System.out.println(Thread.currentThread().getName() +" nothing to process...");
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public boolean isAllowed(String s, long ts) {
        if(bucket.size()+1 < this.capacity && bucket.offer(s)) {
            System.out.println(Thread.currentThread().getName() + " added bucket size "+bucket.size());
            return true;
        }
        else {
            System.out.println(Thread.currentThread().getName() +" bucket size overflow " + bucket.size());
            return false;
        }
    }
}

class TokenBucket implements IRateLimiter {

    long capacity;
    long token;
    long lastRefillTime;
    long refillCountPerSecond;

    public TokenBucket(int c, int refillCountPerSecond) {

        this.capacity = c;
        this.token = c;
        this.lastRefillTime = System.currentTimeMillis();
        this.refillCountPerSecond = refillCountPerSecond;// capacity/windowTimeInSeconds;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    refillTokens();
                }
            }
        }).start();
    }

    private void refillTokens() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastRefillTime;
        long tokensToAdd = (elapsedTime / 1000) * refillCountPerSecond;
        System.out.println("token to add " + tokensToAdd +" current count " + token);
        token = Math.min(capacity, token + tokensToAdd);
        lastRefillTime = now;
    }

    @Override
    public boolean isAllowed(String s, long ts) {
        if(token > 0) {
            System.out.println("token: " + token);
            token--;
            return true;
        }
        return false;
    }
}

interface IRateLimiter {
    boolean isAllowed(String s, long ts);
}
