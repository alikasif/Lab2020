package java11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

class ExecutorTest implements Executor {
    @Override
    public void execute(Runnable r) {
        r.run();
    }
}

class MyRunnableLatch implements Runnable {

    int y;
    CountDownLatch cdll;
    public MyRunnableLatch(int x, CountDownLatch cdl){
        y=x;
        cdll = cdl;
    }

    @Override
    public void run() {
        System.out.println("just starting executor "+this.y);
        try {
            cdll.countDown();
            cdll.await();
            System.out.println("just stopping executor "+this.y);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class MyRunnableBarrier implements Runnable {

    int y;
    CyclicBarrier cbll;
    public MyRunnableBarrier(int x, CyclicBarrier cbl){
        y=x;
        cbll = cbl;
    }

    @Override
    public void run() {
        System.out.println("just starting executor "+this.y);
        try {
            cbll.await();
            System.out.println("just stopping executor "+this.y);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


public class ConcurrencyPractise {

    static void concurrencyTest() throws Exception{

        CountDownLatch cdl = new CountDownLatch(3);
        new ExecutorTest().execute(new MyRunnableLatch(1,cdl));
        new ExecutorTest().execute(new MyRunnableLatch(2, cdl));
        new ExecutorTest().execute(new MyRunnableLatch(3, cdl));

        ExecutorService exs = Executors.newFixedThreadPool(2);
        exs.execute(new MyRunnableLatch(4,cdl));
        Future<?> submit = exs.submit(new MyRunnableLatch(5,cdl));
        System.out.println(submit.get());
        exs.shutdown();
    }

    static void singleThreadExecutorsTest() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<Future<?>> futures = new ArrayList<>();
        for(int i=0; i<5; i++) {
            final int j=i;
            Future<?> submit = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(j +" single thread executor sleeping !!" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("single thread executor !!");
                }
            });
            futures.add(submit);
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("submitting after shutdown !!");
            }
        });
    }

    public static void scheduledThreadPoolExecutorTest() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("helo");
            }
        }, 3,2,TimeUnit.SECONDS);
    }

    static void latchTest() {
        CountDownLatch cdl = new CountDownLatch(3);
        Runnable r = new MyRunnableLatch(1,cdl);

        new Thread(r).start();
        new Thread(r).start();
     //   new Thread(r).start();
        cdl.countDown();

        CyclicBarrier cbl = new CyclicBarrier(3);
    }


    static void barrierTest() {
        CyclicBarrier cbl = new CyclicBarrier(3);
        Runnable r = new MyRunnableBarrier(1,cbl);

        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
    }

    static void completableFutureTest() {
        CompletableFuture<String> helloWorld = CompletableFuture.completedFuture("hello world");
        CompletableFuture<Void> asyncRunnable = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("async runnable");
            }
        });
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "async supplier";
            }
        });

        CompletableFuture<String> future = CompletableFuture.completedFuture("hello").thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + " world";
            }
        });

        CompletableFuture<String> future2 = CompletableFuture.completedFuture("hello").thenCompose(new Function<String, CompletionStage<String>>() {
            @Override
            public CompletionStage<String> apply(String s) {
                return CompletableFuture.completedStage(s+" world11");
            }
        });

        try {
            System.out.println(helloWorld.get());
            System.out.println(asyncRunnable.get());
            System.out.println(supplyAsync.get());
            System.out.println(future.get());
            System.out.println(future2.get());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        //latchTest();
        //barrierTest();
        // singleThreadExecutorsTest();
        //scheduledThreadPoolExecutorTest();
        completableFutureTest();
    }
}
