package java11;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MyLock implements Runnable {

    Lock l;
    public MyLock(Lock lock) {
        l=lock;
    }


    @Override
    public void run() {
        try {
            l.lock();
            System.out.println("thread " + Thread.currentThread().getName());
        }finally {
           // l.unlock();
        }
    }
}

public class LocksPractise {

    static void lockTest(Lock lock) {
        try {
            lock.lock();
            System.out.println("testing lock");
        }finally {

        }
     //   lock.unlock();
    }

    static void testLock() {
        Lock lock = new ReentrantLock();
        MyLock lock1 = new MyLock(lock);
        new Thread(lock1).start();
        new Thread(lock1).start();
    }

    public static void main(String[] args) {

    }

}