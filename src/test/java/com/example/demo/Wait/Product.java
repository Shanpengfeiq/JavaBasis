package com.example.demo.Wait;

import java.util.List;

public class Product implements Runnable{
    private List list;

    public Product(List list){
        this.list=list;
    }

    @Override
    public void run() {

        while (true){
            synchronized (list){
                //集合大于0，说明有数据
                if(list.size()>0){
                    try {
                        //停止生产
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Object o = new Object();
                list.add(o);
                System.out.println(Thread.currentThread().getName() + "--->" + o);
                //唤醒消费者消费
                list.notifyAll();
            }
        }
    }
}
