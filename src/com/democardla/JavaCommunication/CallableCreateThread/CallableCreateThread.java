package com.democardla.JavaCommunication.CallableCreateThread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

class NumThread implements Callable{
    @Override
    public Object call() throws Exception {
        int a = 0;
        for (;a < 10;a++){
            System.out.println(a);
        }
        return a;
    }
}
public class CallableCreateThread {
    public static void main(String[] args) {
        NumThread NumberThread = new NumThread();
        FutureTask futureTask = new FutureTask(NumberThread);
        new Thread(futureTask).start();

        //`get()`方法用来获得`FutureTask`构造器中的`Callable`实现类的重写call()的返回值。
        try {
            Object sum = futureTask.get();
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


