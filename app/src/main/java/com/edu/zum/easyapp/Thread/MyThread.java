package com.edu.zum.easyapp.Thread;

/**
 * Created by lwh on 2016/7/13.
 */
public class MyThread implements Runnable {
    int times;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            times++;
            try {
                Thread.sleep(300);
                System.out.println("MyThread:" + times);
            } catch (InterruptedException e) {
                //改变线程状态，使循环结束
                Thread.currentThread().interrupt();
            }

        }
    }
}
