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

    static void testSlidingWindowWithCounter() {
        SlidingWindowCounter slidingWindowCounter = new SlidingWindowCounter(3, 10);
        int i=0;
        while (true){
            i++;
            boolean allowed = slidingWindowCounter.isAllowed(String.valueOf(i), System.currentTimeMillis());
            System.out.println(allowed);
            try {
                Thread.sleep(15 * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void testSlidingWindow() {

        /*        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.offerFirst(1);
        arrayDeque.offerFirst(2);
        arrayDeque.offerFirst(3);
        System.out.println(arrayDeque.poll());
        for(long t : arrayDeque)
            System.out.println(t);*/

        IRateLimiter rateLimiter = new SlidingWindow(3, 10*1000);
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
        while(true){
            System.out.println(rateLimiter.isAllowed(String.valueOf(i++), 0));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class SlidingWindowCounter implements IRateLimiter {

    int bucket;
    int windowSize;
    long millisToGoBack;
    Map<Long, Integer> counter;
    long currentWindow;
    long prevWindow;
    int capacity;

    public SlidingWindowCounter(int capacity, long millisToGoBack) {
        counter = new HashMap<>();
        this.capacity = capacity;
        this.millisToGoBack = millisToGoBack;
        currentWindow= System.currentTimeMillis();
        prevWindow = currentWindow - 60 *1000;

        counter.put(currentWindow,0);
        counter.put(prevWindow, 2);

        new Thread(new Runnable() {
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
        }).start();
    }

    @Override
    public boolean isAllowed(String s, long ts) {

        long elapsedTime = ts - currentWindow;
        Integer currentCount = counter.get(currentWindow);
        int prevCount = counter.get(prevWindow);

        long weightedCount = prevCount * (currentWindow-elapsedTime)/currentWindow;

        System.out.println("current count " + currentCount + " for "+ currentWindow);
        System.out.println("weighted count " + weightedCount + " for "+ prevWindow);

        if(currentCount+weightedCount < capacity) {
            counter.put(currentWindow, counter.get(currentWindow)+1);
            return true;
        }
        return false;
    }
}

class SlidingWindow implements IRateLimiter {

    ArrayDeque<Long> tsQueue;
    int capacity;
    int millisToGoBack;

    public SlidingWindow(int c, int millisToGoBack) {
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
        3 request per second. sleet every second and then reset the counter
     */
    public FixedWindow(int capacity) {
        this.capacity = capacity;
        this.requestCounter = new HashMap<>();

        new Thread(new Runnable() {
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
        }).start();
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

    public TokenBucket(int c, long windowTimeInSeconds) {

        this.capacity = c;
        this.token = c;
        this.lastRefillTime = System.currentTimeMillis();
        this.refillCountPerSecond = capacity/windowTimeInSeconds;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    long now = System.currentTimeMillis();
                    long elapsedTime = now - lastRefillTime;
                    long tokensToAdd = (elapsedTime / 1000) * refillCountPerSecond;
                    System.out.println("token to add " + tokensToAdd +" current count " + token);
                    token = Math.min(capacity, token + tokensToAdd);
                    lastRefillTime = now;
                }
            }
        }).start();
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
