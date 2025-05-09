package com.example.demo.Runnable;

public class MyRunnable02 implements Runnable{
    @Override
    public void run() {
        for (int i = 0;i<100;i++){
            System.out.println("分线程2----->"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
