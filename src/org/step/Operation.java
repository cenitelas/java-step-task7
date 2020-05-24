package org.step;


import org.step.util.ThreadSupportUtil;

import static org.step.AccountTransfer.transfer;

public class Operation {

    public static void main(String[] args) throws InterruptedException {
        final Account firstAccount = new Account(1000,1);
        final Account secondAccount = new Account(2000,2);

        Account account = new Account(10,3);
        account.start();

        new Thread(() -> {
            try {
                transfer(firstAccount, secondAccount, 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        transfer(secondAccount, firstAccount, 300);
    }
}
