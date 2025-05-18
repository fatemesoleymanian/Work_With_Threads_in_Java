import java.util.Map;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        ConcurrentMap<String,Long> finishTimes = new ConcurrentHashMap<>();
        int numberOfPlayers = 5;
        /** Threads will start at the same time with CyclicBarrier*/
        CyclicBarrier startBarrier = new CyclicBarrier(numberOfPlayers, () ->
                System.out.println("🚦 All players ready! Race starts now!"));

        CountDownLatch finishLatch = new CountDownLatch(numberOfPlayers);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfPlayers);

        for (int i = 1; i <= numberOfPlayers; i++) {
            String name = "Player"+i;
            executorService.execute(new Player(name, startBarrier,finishLatch,finishTimes));
        }
        try {
            finishLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        executorService.shutdown();

        System.out.println("\n🏁 Race finished! Results:");

        finishTimes.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEach(entry ->{
                    System.out.printf("🎖 %s - finished in %d ms%n", entry.getKey(), entry.getValue());
                });
    }
}