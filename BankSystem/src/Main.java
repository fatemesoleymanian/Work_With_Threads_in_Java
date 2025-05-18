import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(1, 10000);
        Account account1 = new Account(2, 20000);
        Account account2 = new Account(3, 30000);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Account> accounts = List.of(account, account1, account2);

        for (int i = 0; i < 10; i++) {
            Account from = accounts.get((int) (Math.random() * accounts.size()));
            Account to;
            do {
                to = accounts.get((int) (Math.random() * accounts.size()));
            } while (from == to);

            double amount = Math.random() * 500;
            executorService.submit(new Transfer(from, to, amount));
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Final Balances:");
        for (Account accountObj : accounts) {
            System.out.printf("Account %d: %.2f%n", accountObj.getId(), accountObj.getBalance());
        }
    }

}