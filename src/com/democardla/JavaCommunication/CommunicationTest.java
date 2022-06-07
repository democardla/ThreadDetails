package com.democardla.JavaCommunication;

/*
 * 需求：使用两个线程来打印1-100，线程1，线程2交替打印
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
//                    try {
//                        Thread.sleep(1000);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }//这里可以加上Thread.sleep(1000)来放大线程安全问题

                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;
                    try{
                        wait(1000);//使当前线程暂时冻结，为其他线程的假如提供机会，实现线程的通信
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


