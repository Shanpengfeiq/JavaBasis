package com.example.demo.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock {
    private Lock lock = new ReentrantLock();

    //需要参与同步的方法
    public void method(Thread thread) throws InterruptedException {
//        lock.lock();
//        try {
//            System.out.println("线程名" + thread.getName() + "获得了锁");
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("线程名" + thread.getName() + "释放了锁");
//            lock.unlock();
//        }
        //判断是否能获得锁
        if(lock.tryLock()){
            try {
                System.out.println("线程名"+thread.getName() + "获得了锁");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                System.out.println("线程名"+thread.getName() + "释放了锁");
                lock.unlock();
            }
        }else {
            System.out.println("我是"+Thread.currentThread().getName()+"有人占着锁，我就不要啦");
        }
    }
}
