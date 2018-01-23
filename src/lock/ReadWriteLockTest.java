package lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/1/18
 * \* Time: 下午3:22
 * \* Description:
 * \
 */
public class ReadWriteLockTest {
    static Map<String,String> data=new HashMap<>();
    static ReentrantReadWriteLock rrw=new ReentrantReadWriteLock();
    static Lock readLock=rrw.readLock();
    static Lock writeLock=rrw.writeLock();

    public static final String get(String key){
        readLock.lock();
        try{
            return data.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public static final void set(String key,String value){
        writeLock.lock();
        try{
            data.put(key,value);
        }finally {
            writeLock.unlock();
        }
    }


    public static void main(String[] args) {



    }
}
