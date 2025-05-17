import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        OrderQueue queue = new OrderQueue();
        ReportLog reportLog = new ReportLog();

        /** Creating kitchen thread manually */
//        Thread kitchen = new Thread(new Kitchen(queue, reportLog));
//        kitchen.start();
        /** Creating kitchen thread with handler */
        ExecutorService kitchenPool = Executors.newSingleThreadExecutor();
        kitchenPool.submit(new Kitchen(queue,reportLog));

        /** Creating customers threads manually */
//        Thread[] customers = new Thread[5];
//        for (int i = 0; i < 5; i++) {
//            customers[i] = new Thread(new Customer("customer " + i, queue));
//            customers[i].start();
//        }
        /** Creating customers threads with handler */
        ExecutorService customerPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            customerPool.submit(new Customer("customer " + i, queue));
        }

        customerPool.shutdown();
        customerPool.awaitTermination(5, TimeUnit.SECONDS);

        queue.placeOrder("DONE");  // poison pill

        kitchenPool.shutdown();
        kitchenPool.awaitTermination(5,TimeUnit.SECONDS);

        Thread.sleep(20000);
        reportLog.printReport();
    }
}
