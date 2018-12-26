package com.step.jthread.cas.look.rw;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 * ReentrantLock 锁的是 自己内部维护 的 资源 : state
 * 所有线程 争夺的 是  ReentrantLock 内部的 state 属性的占有权
 * */
public class TestReentrantLock extends Thread {
    private  static  DB db = new DB();
    @Override
    public void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.add();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                db.buy();
                db.buy();
            }
        }).start();
        //db.buyNoLook();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new TestReentrantLock().start();
        }
    }

}

//代码锁,非数据库锁
class DB {
    private Lock lock = new ReentrantLock();
    private Lock lock_add = new ReentrantLock();
    // 用static表示该锁是该对象实例共享的

     int i = 100;
    // 用static表示该变量是对象实例共享的

    //加锁 减库存
    public void buy() {
        lock.lock();
        try{
            System.out.println("购买:"+(--i));
        } finally {
            lock.unlock();
        }
    }

     /**
      * 功能描述:
      *  必须是同一个 look 锁,才能对 i  有锁住效果
      *  look 可以用在不同的方法块中
      *  lock_add 和 look 的使用,相互没有影响
      * TODO
      * @CreateTime [2018/10/22 0022 11:18]
      * @Param
      */
    public void add() {
        lock.lock();
        //lock_add.lock();
        try{
            System.out.println("进货:"+(++i));
        } finally {
            lock.unlock();
           // lock_add.unlock();
        }
    }

    //无所 减库存
    public void buyNoLook() {
        i--;
        System.out.println(i);
    }
}

