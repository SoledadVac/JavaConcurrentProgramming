package container;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/1/17
 * \* Time: 下午6:09
 * \* Description:
 * \
 */
public class ConcurrentHashMapTest {

    static ConcurrentHashMap<String,String> map=new ConcurrentHashMap<>(16);
    static CountDownLatch start=new CountDownLatch(1);
    static int threadCount=10000;
    static CountDownLatch end=new CountDownLatch(threadCount);

    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<threadCount;i++){
            Thread t=new Thread(new PutHashMap(),"t-"+(i+1));
            t.start();
        }
        start.countDown();
        end.await();
    }

    static class PutHashMap implements Runnable{
        @Override
        public void run() {
             try {
               start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(UUID.randomUUID().toString(),"");
            System.out.println(Thread.currentThread().getName()+"  finish");
            end.countDown();
        }
    }
}
