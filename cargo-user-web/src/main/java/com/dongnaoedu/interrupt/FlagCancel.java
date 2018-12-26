package com.dongnaoedu.interrupt;

/**
 * 动脑学院-Mark老师
 * 创建日期：2017/11/26
 * 创建时间: 20:32
 * 使用自定义的取消标志位中断线程（不安全）
 */
public class FlagCancel {

    private static class TestRunable implements Runnable{

        private volatile boolean on = true;
        private long i =0;

        @Override
        public void run() {
            while(on){
                i++;
                //阻塞方法，on不起作用
                //wait,sleep,blockingqueue(put,take)
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("TestRunable is runing :"+i);
        }

        public void cancel(){
            on = false;
        }
    }

}
