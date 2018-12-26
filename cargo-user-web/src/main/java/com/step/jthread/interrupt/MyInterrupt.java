package com.step.jthread.interrupt;

public class MyInterrupt extends Thread {

    public MyInterrupt(String name) {
        super(name);

    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (!isInterrupted()) {
                Thread.sleep(4000L); // 休眠100ms
                i++;
                System.out.println(Thread.currentThread().getName() + " 1-> (" + this.getState() + ") loop " + i);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " 2-> ("+ this.getState() + ") catch InterruptedException.");
        }
    }

    public static void main(String[] args) {
        try {
            Thread t1 = new MyInterrupt("t1");  // 新建“线程t1”
            System.out.println(t1.getName() + " 3 -> (" + t1.getState() + ") is new.");

            t1.start();                      // 启动“线程t1”
            System.out.println(t1.getName() + " 4 -> (" + t1.getState() + ") is started.");

            // 主线程休眠300ms，然后主线程给t1发“中断”指令。
            Thread.sleep(300);
            //t1.interrupt();
            System.out.println(t1.getName() + " 5 -> (" + t1.getState() + ") is interrupted.");

            for (; ; ) {
                // 主线程休眠300ms，然后查看t1的状态。
                Thread.sleep(100);
                System.out.println(t1.getName() + " 6 -> (" + t1.getState() + ").");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
