package com.democardla.JavaCommunication;

public class ProductAndCustomerTest {
    public static void main(String[] args) {
       Clerk clerk = new Clerk();
       Thread producer = new Producer(clerk);
       Thread customer = new Customer(clerk);
       producer.start();
       customer.start();
    }
}

class Producer extends Thread{
    Clerk Lock;
    public Producer(Clerk clerk){
        Lock = clerk;
    }

    public Producer() {
    }

    @Override
    public void run() {
//        while (Lock.isFull()){
//            synchronized (Lock){
//                Lock.notifyAll();
//                try {
//                    Lock.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        int account = Lock.account++;
//        System.out.println("productor:" + account);
        System.out.println("生产者开始生产！");
        while (true){
            //这里是为了确保每个线程都有机会执行
            try {
                sleep(100);
            } catch (Exception e ) {

            }
            Lock.produce();
        }
    }
}

class Customer extends Thread{
    Clerk Lock;
    public Customer(Clerk clerk){
        Lock = clerk;
    }

    public Customer() {
    }

    @Override
    public void run() {
//        while (Lock.isEmpty()){
//            synchronized (Lock){
//                Lock.notifyAll();
//                try {
//                    Lock.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        int account = Lock.account--;
//        System.out.println("customer:" + account);
        while (true){
            Lock.consume();
        }
    }
}

class Clerk {
    private final int MAXIMUM = 20;
    int account = 0;
    public boolean isEmpty(){
        if(account == 0)
            return true;
        else
            return false;
    }
    public boolean isFull(){
        if(account == MAXIMUM)
            return true;
        else
            return false;
    }
    //生产产品
    public synchronized void produce(){
        if (!isFull()) {
            account++;
            System.out.println("Producing the " + account + " product.");
        }
    }
    //消费产品
    public synchronized  void consume(){
        if (!isEmpty()){
            System.out.println("Consuming the " + account + " product.");
            account--;
        }
    }
}
