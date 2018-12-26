package com.step.jthread.utls;

public class ThreadUtils {

    public static int sleep(Long times){
        try {
            Thread.currentThread().sleep(times);
        } catch (InterruptedException e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public static void cancel(){
        Thread.currentThread().interrupt();
    }

}
