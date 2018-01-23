package concurrenttool;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/1/23
 * \* Time: 下午6:04
 * \* Description:ExchangerTest
 * \
 */
public class ExchangerTest {

    private static Exchanger<String> exchanger=new Exchanger<>();

    private static ExecutorService threadPool= Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String exchangeA="exchange a watercount";
                try {
                    String get=exchanger.exchange(exchangeA);
                    System.out.println("a get="+get);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                String exchangeB="exchange b watercount";
                try {
                    String get=exchanger.exchange(exchangeB);
                    System.out.println("b get="+get);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadPool.shutdown();
    }


}
