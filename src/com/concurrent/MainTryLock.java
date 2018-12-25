package com.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainTryLock {
    private Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        final MainTryLock mainTryLock = new MainTryLock();

        new Thread(){
            @Override
            public void run() {
                mainTryLock.insert(Thread.currentThread());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                mainTryLock.insert(Thread.currentThread());
            }
        }.start();
    }

    public void insert(Thread thread){
        if(lock.tryLock()){
            try{
                System.out.println(thread.getName() + "获取锁");
                for(int i=0;i<5;i++){
                    Thread.sleep(200);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                System.out.println(thread.getName() + "释放锁");
                lock.unlock();//释放锁
            }
        }else{
            System.out.println(thread.getName()+"未获取到锁");
        }
    }
}
