package concurrenttool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/1/23
 * \* Time: 下午10:04
 * \* Description: CyclicBarrier test
 * \
 */
public  class CyclicBarrierCountTest implements Runnable{

    Integer threadCount=4;

    AtomicInteger total=new AtomicInteger();

    CyclicBarrier cyclicBarrier=new CyclicBarrier(threadCount,this);

    @Override
    public void run() {
        System.out.println("total="+total.get());
    }

    /**
     * 计数线程，传入初始值代表此线程的计数
     */
     class CountThread implements Runnable{
        int initValue;
        CountThread(int initValue){
            this.initValue=initValue;
        }
        @Override
        public void run() {
            total.addAndGet(initValue);
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

    private void count(){
        for(int i=1;i<threadCount+1;i++){
            new Thread(new CountThread(i)).start();
        }
    }

    public static void main(String[] args) {
        CyclicBarrierCountTest test=new CyclicBarrierCountTest();
        test.count();
    }


}
