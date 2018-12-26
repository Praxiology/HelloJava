package com.step.jthread.mxbean;

import com.step.jthread.utls.ThreadUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

/**
 * 线程管理信息获得
 * Monitor Ctrl-Break : 监听终端信号
 * Attach Listener : 获取内存dump,线程dump
 * Signal Dispatcher : 将信号分配给JVM线程
 * Finalizer : 调用对象finalizer方法
 * Reference Handler : 清除Reference
 * main : //用户程序入口
 * */
public class ThreadMxBeanDemo {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (;;){
                    System.err.println("hello");
                    ThreadUtils.sleep(500l);
                }
            }
        },"helloT").start();
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] infos = bean.dumpAllThreads(true,true);
        Arrays.stream(infos).forEach(e->{
            if (e != null) {
                System.out.println(e.toString());
            }
        });
    }

}
