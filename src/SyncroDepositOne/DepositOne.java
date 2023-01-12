package SyncroDepositOne;

import java.io.*;

/*
MatÃ­as -> Banco_Valpo -- delay -\
                                 \
                                   =>  Bank's DB (Patricio)
                                 /
TomÃ¡s -> Banco_ViÃ±a --- delay --/
*/
public class DepositOne {
    public static void main (String argc[]) {
        PrintWriter out = new PrintWriter(System.out, true);
        Account patricio = new Account (out, 1000);   // One patricio
        DepositThread matias, tomas;           // two independent deposits
        matias = new DepositThread (patricio,  1000, "#1");
        tomas = new DepositThread(patricio, 1000, "\t\t\t\t#2");
        // start the transactions
        matias.start();
        tomas.start();
        // wait for both transactions to finish
        try {
            matias.join();
            tomas.join();
        } catch (InterruptedException e) {}
        // print the final balance
        out. println("***** Final Balance is " + patricio.getBalance());
    }

}

class Account {
    PrintWriter out;
    int balanceInDB;  // simulate balance kept remotely (Bank's DB)
    Account (PrintWriter out, int initialBalance) {
        this.out = out;
        balanceInDB = initialBalance;
    }

    // synchronized void deposit (int amount, String name) {  // enable to make it work
    void deposit (int amount, String name) {  //enable to see inconsistent result
        int balance;
        out. println(name + " trying to deposit " + amount);
        out. println(name + " getting balance...");
        balance = getBalance();
        out. println(name + " balance got is " + balance);
        balance+= amount;
        out. println(name + " setting balance...");
        setBalance(balance);
        out. println(name + " new balance set to " + balanceInDB);
    }

    int getBalance() {
        try {  // simulated delay in getting balance remotely
            Thread.sleep(2000);
        } catch (InterruptedException e) {}
        return balanceInDB;
    }

    void setBalance (int balance) {
        try {  // simulate the delay in setting new balance remotely
            Thread.sleep(3000);
        } catch(InterruptedException e) {}
        balanceInDB = balance;
    }
}


class DepositThread extends Thread
{
    Account account;
    int depositAmount;
    String message;
    DepositThread ( Account account, int amount, String message) {
        this.message = message;
        this.account = account;
        this.depositAmount = amount;
    }
    public void run () {
        account.deposit (depositAmount, message);
    }
}