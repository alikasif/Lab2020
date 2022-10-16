package java11;

import java.util.concurrent.*;

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

    public static void main(String[] args) throws Exception {
  //      latchTest();
        barrierTest();
    }
}
