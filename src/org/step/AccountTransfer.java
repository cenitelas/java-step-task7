package org.step;

import org.step.exceptions.InsufficientFundsException;
import org.step.exceptions.LockWaitingException;

import java.util.concurrent.TimeUnit;

public class AccountTransfer {

    private static final int WAIT_SEC = 1;

    public static void transfer(Account firstAccount, Account secondAccount, int amount) throws InterruptedException {
        if (firstAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Not enough money");
        }

            try {
                firstAccount.getLock().lockInterruptibly();
                    try {
                        secondAccount.getLock().lockInterruptibly();
                        firstAccount.withdraw(amount);
                        secondAccount.deposit(amount);
                        System.out.println("GOOD");
                    }
                    catch (LockWaitingException e) {
                        throw new LockWaitingException("Can't lock now, resource is busy");
                    }
                    finally {
                        secondAccount.getLock().unlock();
                    }
            }
            catch (LockWaitingException e) {
                throw new LockWaitingException("Can't lock now, resource is busy");
            }
            finally {
                firstAccount.getLock().unlock();
            }
    }
}
