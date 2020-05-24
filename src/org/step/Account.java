package org.step;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account extends Thread {

    @Override
    public void run() {
        super.run();
    }

    /*
        Можно использовать volatile переменную
         */
    private int balance;
    private int id;
    /*
    Создаем Lock для захвата ресурсов на трансфер
     */
    private Lock lock;

    public Account(int balance, int id) {
        this.balance = balance;
        this.id = id;
        lock = new ReentrantLock();
    }

    public void withdraw(int amount) {
        lock.lock();
        balance -= amount;
        System.out.println("Log: withdraw in id="+id);
       lock.unlock();
    }

    public void deposit(int amount) {
        lock.lock();
        balance += amount;
        System.out.println("Log: deposit in id="+id);
        lock.unlock();
    }

    public int getBalance() {
        return balance;
    }

    public Lock getLock() {
        return lock;
    }
}
