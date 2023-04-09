package atlassian;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class RateLimiter {
    public static void main(String[] args) {
        testSlidingWindowWithCounter();
    }

    static void testSlidingWindowWithCounter() {
        SlidingWindowCounter slidingWindowCounter = new SlidingWindowCounter(3, 10);
        for(int i =0; i< 50; i++) {
            long l = System.currentTimeMillis() / (500);
            boolean allowed = slidingWindowCounter.isAllowed(String.valueOf(i), l);
            System.out.println(allowed);
            //System.out.println(l);
            try {
                Thread.sleep(1000);
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

    static void testFixedWindow() {
        IRateLimiter rateLimiter = new FixedWindow(3);
        int i=0;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(rateLimiter.isAllowed(String.valueOf(++i), 0));
        }
    }

    static void testLeakyBucket() {
        IRateLimiter rateLimiter = new LeakyBucket(3);
        for(int i = 0; i<10; i++) {
            System.out.println(rateLimiter.isAllowed(String.valueOf(i), 0));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void testTokenBucket() {
        IRateLimiter rateLimiter = new TokenBucket(3);
        for(int i = 0; i<10; i++) {
            System.out.println(rateLimiter.isAllowed(String.valueOf(i), 0));
            try {
                Thread.sleep(2000);
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

    public SlidingWindowCounter(int b, long millisToGoBack) {
        counter = new HashMap<>();
        this.millisToGoBack = millisToGoBack;
    }

    @Override
    public boolean isAllowed(String s, long ts) {
        System.out.println(counter);
        long l = System.currentTimeMillis();
        long millisTOGoBack = l - 12000;
        //System.out.println(millisTOGoBack);

        int r=0;
        while ( l >= millisTOGoBack ) {
            l = l - 4000;
            if(counter.get(l) != null)
                r+=counter.get(l);
        }

        if (r+1 < 10) {
            long ll = System.currentTimeMillis() / (4 * 1000);// 4 sec window
            Integer integer = counter.putIfAbsent(ll, 1);
            if(integer != null)
                counter.put(ll, integer+1);
            return true;
        }
        else
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
        int c = 0;
        for(long t : tsQueue) {
            if (t > oldestTS) {
                System.out.println(t +" " + oldestTS);
                c++;
            }
            else {
                System.out.println(t +" " + oldestTS);
                break;
            }
        }
        if (c >= capacity)
            return false;
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

    public FixedWindow(int capacity) {
        this.capacity = capacity;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(60 * 1000); // 0 seconds
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    currentTime = System.currentTimeMillis();
                    requests = 0;
                }
            }
        }).start();
    }

    @Override
    public boolean isAllowed(String s, long ts) {
        if (requests < capacity) {
            requests++;
            System.out.println(System.currentTimeMillis());
            return  true;
        }
        return false;
    }
}
class LeakyBucket implements IRateLimiter {

    Queue<String> bucket;
    int capacity;

    public LeakyBucket(int capacity) {
        bucket = new ArrayDeque<>(capacity);
        this.capacity = capacity;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if(bucket.size() > 0) {
                        String poll = bucket.poll();
                        System.out.println("processing " + poll);
                    }
                    else{
                        System.out.println("nothing to process");
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
    @Override

    public boolean isAllowed(String s, long ts) {
        if(bucket.size() < this.capacity && bucket.offer(s))
            return true;
        else
            return false;
    }
}

class TokenBucket implements IRateLimiter {

    int capacity;
    int token;
    public TokenBucket(int c) {
        this.capacity = c;
        this.token = c;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (token < capacity) {
                        System.out.println("adding token");
                        token++;
                    }
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
