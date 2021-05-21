package concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * (JDK)ReentrantLock手撕AQS
 * from https://zhuanlan.zhihu.com/p/54297968
 *
 */
public class ReentrantLockForIncrease {
    static Integer cnt = 0;


    public static void main(String[] args) {
        Object lock = new Object();
        ReentrantLock reentrantLock = new ReentrantLock();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                int n = 10000;
                while(n>0){
                    //reentrantLock.lock();
                    synchronized (lock) {
                        cnt++;
                    }
                    //reentrantLock.unlock();

                    n--;
                }
            }
        };
        Thread t1  = new Thread(r);
        Thread t2  = new Thread(r);
        Thread t3  = new Thread(r);
        Thread t4  = new Thread(r);
        Thread t5  = new Thread(r);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            //等待足够长的时间 确保上述线程均执行完毕
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cnt);
    }
}
