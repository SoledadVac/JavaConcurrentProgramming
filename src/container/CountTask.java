package container;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/1/18
 * \* Time: 下午6:09
 * \* Description:  fork/join计算 1+2+3+4
 * \
 */
public class CountTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD=2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        //生成一个计算任务，负责计算1+2+3+4
        CountTask countTask=new CountTask(1,4);
        //执行一个计算任务
        Future<Integer> result=forkJoinPool.submit(countTask);
        try {
            System.out.println("计算结果为:"+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Integer compute() {
        int sum=0;
        //如果任务足够小就计算任务
        boolean canCompute=(end-start)<=THRESHOLD;
        if(canCompute){
            for(int i=start;i<=end;i++){
                sum+=i;
            }
        }else{
            //如果任务大于阙值，就会分割成两个任务
            int middle=(start+end)/2;
            CountTask leftTask=new CountTask(start,middle);
            CountTask rightTask=new CountTask(middle+1,end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            //等待子任务完成
            int leftResult=leftTask.compute();
            int rightResult=rightTask.compute();
            //合并任务
            sum=leftResult+rightResult;
        }
        return sum;

    }
}
