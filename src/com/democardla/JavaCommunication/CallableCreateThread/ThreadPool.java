package com.democardla.JavaCommunication.CallableCreateThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author democardla
 *
 * @version
 *
 * @since openjdk-18
 *
 * 创建线程的方式：线程池
 */
public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        //设置线程池的属性
        ThreadPoolExecutor se = (ThreadPoolExecutor) service;
        se.setCorePoolSize(15);


        service.execute(new ThreadCreatedByRunnable());
        service.shutdown();
//        service.submit(new ThreadCreatedByCallable());
    }
}

class ThreadCreatedByRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i%2 == 0) {
                System.out.println("Runnable output " + i);
            }
        }
    }
}

class ThreadCreatedByCallable implements Callable {
    @Override
    public Object call() throws Exception {

        return null;
    }
}