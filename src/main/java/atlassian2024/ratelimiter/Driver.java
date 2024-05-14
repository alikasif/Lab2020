package atlassian2024.ratelimiter;

import java.util.concurrent.TimeUnit;

public class Driver {

    public static void main(String[] args) throws InterruptedException {
        final int MAX_REQUESTS_PER_SEC = 10;

        for(int i=0; i<100; i++) {
            long time = System.currentTimeMillis();
            System.out.println(i  +" :: "+ " 1 Minute :: " + TimeUnit.MILLISECONDS.toMinutes(time) +" | "+"2 Minute :: " + (TimeUnit.MILLISECONDS.toMinutes(time)/2));
            // System.out.println("2 Minute :: " + (TimeUnit.MILLISECONDS.toMinutes(time)/2));
            TimeUnit.SECONDS.sleep(10);
        }
//
//        RateLimiter rateLimiter = new FixedWindowCounter(new MinutePolicy(3, 1)); // new a RateLimiter here
//
//        sendRequest2(rateLimiter, 20, 1);


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                sendRequest2(rateLimiter, 30, 1);
//                //sendRequest2(rateLimiter, 20, 2);
//            }
//        }).start();

//        Thread requestThread = new Thread(() -> {
//            sendRequest(rateLimiter, 10, 1);
//            sendRequest(rateLimiter, 20, 2);
//            sendRequest(rateLimiter,50, 5);
//            sendRequest(rateLimiter,100, 10);
//            sendRequest(rateLimiter,200, 20);
//            sendRequest(rateLimiter,250, 25);
//            sendRequest(rateLimiter,500, 50);
//            sendRequest(rateLimiter,1000, 100);
//        });
//
//        requestThread.start();

        //requestThread.join(); // join main thread
        System.out.println("End :: "+TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()));

    }

    private static void sendRequest2(RateLimiter rateLimiter, int totalCnt, int requestPerSec) {

        while (totalCnt > 0) {
            totalCnt--;

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (Exception e) {
            }

        }


//    private static void sendRequest(RateLimiter rateLimiter, int totalCnt, int requestPerSec) {
//        long startTime = System.currentTimeMillis();
//        CountDownLatch doneSignal = new CountDownLatch(totalCnt);
//        for (int i = 0; i < totalCnt; i++) {
//            try {
//                new Thread(() -> {
//                    while (!rateLimiter.allow()) {
//                        try {
//                            TimeUnit.MILLISECONDS.sleep(10);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    doneSignal.countDown();
//                }).start();
//                TimeUnit.MILLISECONDS.sleep(1000 / requestPerSec);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            doneSignal.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        double duration = (System.currentTimeMillis() - startTime) / 1000.0;
//        System.out.println(totalCnt + " requests processed in " + duration + " seconds. "
//                + "Rate: " + (double) totalCnt / duration + " per second");
//    }
    }
}
