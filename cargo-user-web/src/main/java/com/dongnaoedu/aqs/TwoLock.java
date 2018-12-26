package com.dongnaoedu.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/12/03
 * 创建时间: 21:09
 */
public class TwoLock implements Lock {

    static class Sync extends AbstractQueuedSynchronizer {
        Sync(int count){
            setState(count);
        }

        //  共享锁获取
        public int tryAcquireShared  (int arg){
            for(;;){
                int current = getState();
                int newCount = current - arg;
                if(newCount<0||compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

        //共享锁锁释放
        public boolean tryReleaseShared  (int arg){
            for(;;){
                int current = getState();
                int newCount = current + arg;
                if(compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }

        Condition newCondition(){
            return new ConditionObject();
        }

    }

    private final Sync sync = new Sync(2);

    @Override
    public void lock() {
        sync.acquireShared(1);

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);

    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1)>=0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);

    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
