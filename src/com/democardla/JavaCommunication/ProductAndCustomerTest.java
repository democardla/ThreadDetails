package com.democardla.JavaCommunication;

public class ProductAndCustomerTest {
    public static void main(String[] args) {
       Clerk clerk = new Clerk();
       Thread productor = new Productor(clerk);
       Thread customer = new Customer(clerk);
       productor.start();
       customer.start();
    }
}

class Productor extends Thread{
    Clerk Lock;
    public Productor(Clerk clerk){
        Lock = clerk;
    }

    public Productor() {
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
        while (true){
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
    public synchronized void produce(){
        if (!isFull()) {
            account++;
            System.out.println("Producing the " + account + " product.");
        }
    }
    public synchronized  void consume(){
        if (!isEmpty()){
            System.out.println("Consuming the " + account + " product.");
            account--;
        }
    }
}
