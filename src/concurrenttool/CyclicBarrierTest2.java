package concurrenttool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/1/23
 * \* Time: 下午3:26
 * \* Description:
 * \
 */
public class CyclicBarrierTest2 {
    static CyclicBarrier cyclicBarrier=new CyclicBarrier(2,new BeforeClass());

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("0000000000000");
            }
        }).start();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("111111111");

    }

    static class BeforeClass implements Runnable{
        @Override
        public void run() {
            System.out.println("到达屏障，先执行这个方法-------");
        }
    }
}
