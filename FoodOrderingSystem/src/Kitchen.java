public class Kitchen implements Runnable {
    private final OrderQueue orderQueue;

    private final ReportLog reportLog ;
    public Kitchen(OrderQueue orderQueue, ReportLog reportLog) {
        this.orderQueue = orderQueue;
        this.reportLog = reportLog;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String order = orderQueue.takeOrder();

                if (order.equals("DONE")) {
                    System.out.println("👨‍🍳 Kitchen closing.");
                    break;
                }

                long startTime = System.currentTimeMillis();
                System.out.println("Preparing " + order);
                Thread.sleep(1500);
                long endTime = System.currentTimeMillis();
                System.out.println("Finished " + order);
                reportLog.log(new PreparedOrder(order,startTime,endTime));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
