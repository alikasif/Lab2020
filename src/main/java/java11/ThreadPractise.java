package java11;

class MR1 implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}

public class ThreadPractise {

    public static void main(String[] args) throws Exception {

        Runnable r = new MR1();
        Thread t1  = new Thread(r);
        Thread t2  = new Thread(r);
        t1.start();
        t1.join();
        System.out.println("all done");
    }
}