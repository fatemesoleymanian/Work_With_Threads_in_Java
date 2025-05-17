import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class OrderQueue {

//    private final BlockingQueue<String> orders = new LinkedBlockingQueue<>(3);
    private final BlockingQueue<String> orders = new LinkedBlockingQueue<>(3);

    public void placeOrder(String order) throws InterruptedException{
        System.out.println("📥 New Order: " + order);
//        orders.offer(order); //non blocking
        orders.put(order); // blocking
    }
    public String takeOrder() throws InterruptedException{
        return orders.take();
    }
}
