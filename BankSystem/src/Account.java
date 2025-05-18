import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private int id;
    private double balance;
    private final Lock lock = new ReentrantLock();

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public Lock getLock() {
        return lock;
    }



    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }
    public void deposit(double amount){
        balance += amount;
    }
    public boolean withdraw(double amount){
        if (balance > amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
