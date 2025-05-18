import java.util.concurrent.CountDownLatch;

public class Main {
    public static void main(String[] args) {
        /** With CountDownLatch */
//        int passangerCount = 5;
//        CountDownLatch boardingSignal = new CountDownLatch(1);
//
//        for (int i =0 ; i < passangerCount ; i++){
//            final int id = i;
//            new Thread(()->{
//                System.out.println("🧍 Passenger " + id + " waiting for boarding...");
//
//                try {
//                    boardingSignal.await(); //wait to be allowed
//                    System.out.println("🚪 Passenger " + id + " is boarding now!");
//
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//            }).start();
//        }
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("\n📢 Gate opened! Passengers may board.");
//        boardingSignal.countDown();//all passengers are allowed


        /** With wait/notify */
        Platform platform = new Platform();
        for (int i = 1 ; i <= 5 ; i++){
            final int id = i;
            new Thread(()->{

                try {
                    platform.enterPlatform("Passemger "+id);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        //one by one
        new Thread(()->{
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(2000); // between passengers
                    platform.allowNext();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}