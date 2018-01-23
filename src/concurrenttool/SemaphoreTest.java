package concurrenttool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/1/23
 * \* Time: 下午5:47
 * \* Description:SemaphoreTest
 * \
 */
public class SemaphoreTest {
    private static final int THREAD_COUNT=30;

    private static ExecutorService threadPool= Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore semaphore=new Semaphore(1);

    private static AtomicInteger count=new AtomicInteger();



    public static void main(String[] args) {
        for(int i=0;i<THREAD_COUNT;i++){
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("handle ------"+" "+ count.incrementAndGet());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    semaphore.release();
                }
            });
        }
        threadPool.shutdown();


    }

}
