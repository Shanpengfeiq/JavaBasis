package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        //启动线程
//        myThread.run(); // 不会启动线程，不会分配新的分支栈。（这种方式就是单线程。）
        myThread.start();
        for (int i = 0;i<100;i++){
            System.out.println("主线程------->"+i);
        }
    }
}
class MyThread extends Thread{
    public void run(){
        for (int i = 0; i<100;i++){
            System.out.println("分支线程--------->"+i);
        }
    }
}