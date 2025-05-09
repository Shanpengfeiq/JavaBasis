package com.example.demo.Wait;

import java.util.List;

public class Consumer implements Runnable{
    private List list;

    public Consumer(List list){
        this.list = list;
    }

    @Override
    public void run() {
        while (true){
            synchronized (list){
                //集合中没数据，就不消费
                if(list.size()==0){
                    try {
                        //停止线程
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //消费
                    Object o = list.remove(0);
                    System.out.println(Thread.currentThread().getName() + "--->" + o);
                    //唤醒生产者
                    list.notifyAll();
                }
            }
        }
    }
}
