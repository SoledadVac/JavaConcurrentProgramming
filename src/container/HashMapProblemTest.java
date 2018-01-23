package container;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/1/17
 * \* Time: 下午3:56
 * \* Description:测试hashmap在并发访问时候会出现的问题
 * \
 */
public class HashMapProblemTest {

    static HashMap<String,String> map=new HashMap<>(2);

    static CountDownLatch start=new CountDownLatch(1);
    static int threadCount=5000;
    static CountDownLatch end=new CountDownLatch(threadCount);

    public static void main(String[] args) throws InterruptedException {

        for(int i=0;i<threadCount;i++){
            Thread t=new Thread(new PutHashMap());
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
            end.countDown();
        }
    }


}
