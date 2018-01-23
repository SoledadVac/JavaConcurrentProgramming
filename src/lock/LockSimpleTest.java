package lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/1/18
 * \* Time: 上午10:14
 * \* Description:LockSimpleTest
 * \
 */
public class LockSimpleTest {

    static Long count=1l;

    static int threadCount=100;

    static CountDownLatch end=new CountDownLatch(threadCount);

    public static void main(String[] args) throws InterruptedException {


        for(int i=0;i<threadCount;i++){
            new Thread(new CountThread(),"t-"+(i+1)).start();
        }
        end.await();
        System.out.println("count="+count);


    }

    /**
     * lock规范使用：
         Lock lock=new ReentrantLock();
         lock.lock();

         try{
            //数据操作。。。
         }finally{
         lock.unlock();
         }
     */
    static class CountThread implements Runnable{

        @Override
        public void run() {
            end.countDown();
            Lock lock=new ReentrantLock();
            lock.lock();
            count++;
            try{

            }finally{
                lock.unlock();
            }
        }
    }
}
