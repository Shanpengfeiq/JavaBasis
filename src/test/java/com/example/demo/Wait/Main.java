package com.example.demo.Wait;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List list = new ArrayList();

        Thread thread1 = new Thread(new Product(list));
        Thread thread2 = new Thread(new Consumer(list));
        thread1.setName("生产者");
        thread2.setName("消费者");
        thread1.start();
        thread2.start();
    }
}
