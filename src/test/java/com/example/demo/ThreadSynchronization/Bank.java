package com.example.demo.ThreadSynchronization;

public class Bank {
    private int count=0;//账户余额

    //存钱
    public synchronized void addMoney(int money){
        count+=money;
        System.out.println("存:"+money);
    }
    //取钱
    public synchronized void subMoney(int money){
        if(count<money){
            System.out.println("余额不足");
            return;
        }
        count-=money;
        System.out.println("取出:"+money);
    }
////存钱
//public  void addMoney(int money){
//    synchronized (this){
//        count+=money;
//        System.out.println("存:"+money);
//    }
//
//}
//    //取钱
//    public  void subMoney(int money){
//    synchronized (this){
//        if(count<money){
//            System.out.println("余额不足");
//            return;
//        }
//        count-=money;
//        System.out.println("取出:"+money);
//    }
//    }

    //查询
    public void selectMoney(){
        System.out.println("当前余额:"+count);
    }
}
