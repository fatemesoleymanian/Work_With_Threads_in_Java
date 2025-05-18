import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Player implements Runnable{
    private final String name;
    private final CyclicBarrier barrier;
    private final CountDownLatch countDownLatch;
    private final ConcurrentMap<String,Long> results;

    public Player(String name, CyclicBarrier barrier,CountDownLatch count,ConcurrentMap<String,Long> list) {
        this.name = name;
        this.barrier = barrier;
        this.countDownLatch = count;
        this.results = list;
    }

/**    CyclicBarrier ensures that all players remain at their waiting points until a common start is achieved.
    runnable + barrier → Synchronize start.*/

    @Override
    public void run() {
        try {
            System.out.println(name + " is getting ready...");
            Thread.sleep((int) (Math.random() * 2000) + 1000);

            System.out.println(name + " is at the start line...");
            barrier.await();

            long start = System.currentTimeMillis();
            int runTime = (int) (Math.random() * 3000) + 2000; // running time simulation

            Thread.sleep(runTime);

            long finish = System.currentTimeMillis();

            results.put(name,finish-start);

            System.out.println("✅ " + name + " finished in " + (finish - start) + " ms");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown(); // finish race announcement
        }

    }
}
