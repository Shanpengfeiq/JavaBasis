package com.example.demo.Lock;

public class Main {
    public static void main(String[] args) {
        MyLock myLock = new MyLock();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myLock.method(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myLock.method(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2");

        thread1.start();
        thread2.start();
    }
}
