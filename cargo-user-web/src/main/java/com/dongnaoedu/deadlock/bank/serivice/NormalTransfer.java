package com.dongnaoedu.deadlock.bank.serivice;

import com.dongnaoedu.deadlock.bank.Account;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/12/14
 * 创建时间: 20:25
 */
public class NormalTransfer implements ITransfer{
    @Override
    public void transfer(Account from, Account to, int amount)
            throws InterruptedException {
        synchronized (from){
            System.out.println(Thread.currentThread().getName()+" get "+from.getName());
            Thread.sleep(100);
            synchronized (to){
                System.out.println(Thread.currentThread().getName()
                        +" get "+to.getName());
                from.flyMoney(amount);
                to.addMoney(amount);
            }
        }
    }
}
