import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Transfer implements Runnable{
    private final Account fromAccount;
    private final Account toAccount;
    private final double amount;

    public Transfer(Account fromAccount, Account toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        Account firstLock = fromAccount.getId() < toAccount.getId() ? fromAccount : toAccount ;
        Account secondLock = fromAccount.getId() < toAccount.getId() ? toAccount : fromAccount ;
        /** RentralLock way */
//        try {
//            if (fromAccount.getLock().tryLock(1, TimeUnit.SECONDS)){
//                try {
//                    Thread.sleep(10);
//                    if (toAccount.getLock().tryLock(1,TimeUnit.SECONDS)){
//                        try {
//                            if (fromAccount.withdraw(amount)){
//                                toAccount.deposit(amount);
//                                System.out.printf("✅ Transferred %.2f from Account %d to Account %d%n",
//                                        amount, fromAccount.getId(), toAccount.getId());
//                            }else {
//                                System.out.printf("❌ Insufficient funds in Account %d%n", fromAccount.getId());
//                            }
//                        }finally {
//                            toAccount.getLock().unlock();
//                        }
//                    }else {
//                        System.out.println("⚠️ Couldn't lock toAccount: " + toAccount.getId());
//                    }
//                }finally {
//                    fromAccount.getLock().unlock();
//                }
//            }else {
//                System.out.println("⚠️ Couldn't lock fromAccount: " + fromAccount.getId());
//            }
//        }catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            synchronized (firstLock) {
                Thread.sleep(10); // simulate delay
                synchronized (secondLock) {
                    if (fromAccount.withdraw(amount)) {
                        toAccount.deposit(amount);
                        System.out.printf("✅ Transferred %.2f from Account %d to Account %d%n",
                                amount, fromAccount.getId(), toAccount.getId());
                    } else {
                        System.out.printf("❌ Insufficient funds in Account %d%n", fromAccount.getId());
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
