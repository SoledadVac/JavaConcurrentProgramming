package concurrenttool;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Map;
import java.util.concurrent.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/1/23
 * \* Time: 下午3:47
 * \* Description: 汇总
 * \
 */
public class BankWaterService implements Runnable {

    private CyclicBarrier cyclicBarrier=new CyclicBarrier(4,this);//到大屏障时候，先执行this线程方法

    private Executor executor= Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String,Integer> sheetBlankWaterCount=new ConcurrentHashMap<>();

    private void count(){
        for(int i=0;i<4;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheetBlankWaterCount.put(Thread.currentThread().getName(),1);
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result=0;
        for(Map.Entry<String,Integer> sheet : sheetBlankWaterCount.entrySet()){
            result+=sheet.getValue();
        }
        sheetBlankWaterCount.put("result",result);
        System.out.println("result="+ result);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterService=new BankWaterService();
        bankWaterService.count();
    }
}
