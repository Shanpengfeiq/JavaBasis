package com.example.demo.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws Exception {
        //3.创建Callable接口实现类的对象
        MyCallable myCallable = new MyCallable();
        //4.将此Callable接口实现类的对象作为参数传递到FutureTask构造器中，创建FutureTask对象
        FutureTask<Integer> futureTask = new FutureTask(myCallable);
        //5.将FutureTask的对象作为参数传递到Thread类的构造器中，创建Thread对象，并调用start()
        new Thread(futureTask).start();
        try {
            //6.获取Callable中Call方法的返回值
            Integer sum = futureTask.get();
            System.out.println("总和为"+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
