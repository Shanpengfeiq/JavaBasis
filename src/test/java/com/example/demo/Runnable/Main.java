package com.example.demo.Runnable;

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        Thread  thread2 = new Thread(new MyRunnable02());
        thread.start();
        thread2.start();
        System.out.println(thread.getName()+thread2.getName());
        for( int i = 0;i < 100; i++){
            System.out.println("主线程-------->"+i);
        }

    }
}
