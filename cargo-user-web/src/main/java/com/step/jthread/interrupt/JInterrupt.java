package com.step.jthread.interrupt;

import com.step.jthread.utls.ThreadUtils;

/**
 * 安全终止线程
 * */
public class JInterrupt {

    public static void main(String[] args) {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadUtils.sleep(1000l);
                //1: 线程中断方法被调用,状态依然是 RUNNABLE
                System.err.println("thread interrupted , would run here? state is : " + Thread.currentThread().getState());
                while (true){
                    // 2:线程中断方法被调用,状态依然是 RUNNABLE,依然可以继续执行逻辑;除非直接将中断异常抛出,异常被捕获,线程的逻辑依然会走下去
                    ThreadUtils.sleep(1000l);
                    System.err.println("thread interrupted,can run? state is : " + Thread.currentThread().getState());
                }
            }
        },"interruptTest");

        t.start();

        //主线程调用线程t 的 中断
        t.interrupt();

    }

}
