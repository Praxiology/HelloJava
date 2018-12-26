package com.dongnaoedu.deadlock.bank.serivice;

import com.dongnaoedu.deadlock.bank.Account;

import java.util.Random;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/12/14
 * 创建时间: 20:45
 * 定时轮询获取锁
 */
public class TryLockTransfer implements ITransfer {
    @Override
    public void transfer(Account from, Account to, int amount)
            throws InterruptedException {
        Random r = new Random();
        while(true){
            if(from.getLock().tryLock()){
                try{
                    System.out.println(Thread.currentThread().getName()
                            +" get from "+from.getName());

                    if(to.getLock().tryLock()){
                        try{
                            System.out.println(Thread.currentThread().getName()
                                    +" get to "+to.getName());
                            from.flyMoney(amount);
                            to.addMoney(amount);
                            System.out.println(from);
                            System.out.println(to);
                            break;
                        }finally {
                            to.getLock().unlock();
                        }
                    }

                }finally {
                   from.getLock().unlock();
                }
            }
            Thread.sleep(r.nextInt(5));//防止产生活锁
        }
    }
}
