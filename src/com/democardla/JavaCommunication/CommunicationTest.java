package com.democardla.JavaCommunication;

/*
 * 需求：使用两个线程来打印1-100，线程1，线程2交替打印
 *
 * 方法：
 * wait() 执行次方法后将释放锁，并且使线程进入冻结状态
 * notify() 唤醒当前的冻结线程
 * notifyAll() 唤醒所有的冻结线程
 *
 * 注意：
 * 1。他们都只能在同步代码块或者同步方法中使用，即被 synchronized 声明的主体内部，不包括 ReentrantLock 加锁
 * 2。调用者必须是同步代码块终中的同步监视器：即充当锁的对象来调用这三个方法
 * 3。他们是被定义在object类包中的（#2）成立的前提
 *
 */


import org.junit.jupiter.api.Test;


public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread thread1 = new Thread(number);
        Thread thread2 = new Thread(number);
        thread1.start();
        thread2.start();
    }
}

class Number implements Runnable{
    private int number = 1;
    @Test
    public void method(){

    }

    @Override
    public void run() {
        while (true){
            synchronized (this){
                if (number <= 100) {
//                    notify();//唤醒线程
//                    notifyAll();//唤醒所有线程
//                    try {
//                        Thread.sleep(1000);//会抛出异常
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }//这里可以加上Thread.sleep(1000)来放大线程安全问题

                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;
                    try{
                        wait(1000);//使当前线程暂时冻结，为其他线程的假如提供机会，实现线程的通信
                        //wait();//在不输入冻结时间时，我们需要使用notify()或notifyAll()来解锁线程
                    } catch(InterruptedException e){
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}


