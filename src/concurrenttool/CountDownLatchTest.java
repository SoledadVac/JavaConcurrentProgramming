package concurrenttool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/1/23
 * \* Time: 下午9:43
 * \* Description:CountDownLatch test
 * \
 */
public class CountDownLatchTest {

    static Integer threadCount=4;

    static CountDownLatch countDownLatch=new CountDownLatch(threadCount);

    static AtomicInteger total=new AtomicInteger();



    public static void main(String[] args) throws InterruptedException {
        for(int i=1;i<threadCount+1;i++){
            new Thread(new CountThread(i)).start();
        }
        countDownLatch.await();
        System.out.println("total ="+total.get());

    }

    /**
     * 计数线程，传入初始值代表此线程的计数
     */
    static class CountThread implements Runnable{
        int initValue;
        CountThread(int initValue){
            this.initValue=initValue;
        }
        @Override
        public void run() {
            total.addAndGet(initValue);
            countDownLatch.countDown();
        }
    }
}
