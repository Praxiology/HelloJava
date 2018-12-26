package com.step.jthread.cas;

import com.step.jthread.utls.ThreadUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomicIntger {

    public static void main(String[] args) {
        intAdd(20, 1000);// ev: 20*1000 = 20000 //基本上达不到目标结果
        atomicIntegerAdd(20, 1000);//永远是正确的答案

        //主线程休眠一段时间,保证逻辑执行完成
        ThreadUtils.sleep(2000L);
        System.err.println("int_a:" + int_a);
        System.err.println("atomicInteger:" + atomicInteger);
    }


    //不能保证原子操作
    public static volatile int int_a = 0;
    public static void intAdd(int threads, int loop) {
        for (int i = 0; i < threads; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < loop; j++) {
                        int_a++;
                    }
                }
            }).start();
        }
    }

    // AtomicInteger 可以保证原子的自加操作
    public static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void atomicIntegerAdd(int threads, int loop) {
        for (int i = 0; i < threads; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < loop; j++) {
                        atomicInteger.getAndIncrement();
                    }
                }
            }).start();
        }
    }

}
