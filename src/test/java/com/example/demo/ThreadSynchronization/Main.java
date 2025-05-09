package com.example.demo.ThreadSynchronization;

public class Main {
    public static void main(String[] args) {
        Bank bank=new Bank();

        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bank.addMoney(100);
                    bank.selectMoney();
                }
            }
        });
        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bank.subMoney(100);
                    bank.selectMoney();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
