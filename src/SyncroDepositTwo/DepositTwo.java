package SyncroDepositTwo;
import java.io.*;
public class DepositTwo {

    static int balance =1000;  // simulate balance kept remotely

    public static void main (String argc[]) {
        PrintWriter out = new PrintWriter(System.out, true);
        Account account = new Account (out);
        DepositThread first, second;
        first = new DepositThread (account, 1000, "#1");
        second = new DepositThread(account, 1000, "\t\t\t\t#2");
        // start the transactions
        first.start();
        second.start();
        // wait for both transactions to finish
        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {}
        // print the final balance
        out. println("***** Final Balance is " + balance);
    }

}

class Account {

    PrintWriter out;

    Account (PrintWriter out) {
        this.out = out;
    }

    void deposit (int amount, String name) {
        int balance;
        out. println(name + " trying to deposit " + amount);
        // {
        synchronized(this) {  // to reduce the exclusive access code
            out. println(name + " getting balance...");
            balance = getBalance();
            out. println(name + " balance got is " + balance);
            balance+= amount;
            out. println(name + " setting balance...");
            setBalance(balance);
        }
        out. println(name + " new balance set to " + DepositTwo.balance);
    }

    int getBalance() {
        try {  // simulated delay in getting balance remotely
            Thread.sleep(5000);
        } catch (InterruptedException e) {}
        return DepositTwo.balance;
    }

    void setBalance (int balance) {
        try {  // simulate the delay in setting new balance remotely
            Thread.sleep(5000);
        } catch(InterruptedException e) {}
        DepositTwo.balance = balance;
    }
}


class DepositThread extends Thread
{
    Account account;
    int depositAmount;
    String message;
    DepositThread (Account account, int amount, String message) {
        this.message = message;
        this.account = account;
        this.depositAmount = amount;
    }
    public void run () {
        account.deposit (depositAmount, message);
    }
}