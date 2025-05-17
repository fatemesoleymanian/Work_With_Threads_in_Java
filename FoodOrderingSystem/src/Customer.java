

public class Customer implements Runnable{
    private String name;
    private OrderQueue orderQueue;

    public Customer(String name, OrderQueue orderQueue) {
        this.name = name;
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0 ; i < 2 ; i++){
                String[] meals = {"Pizza","Burger","Salad","Sushi","Pasta"};
                String meal = meals[ (int)(Math.random() * meals.length)];
                String order = name + " ordered " + meal;
                orderQueue.placeOrder(order);
                Thread.sleep((int)Math.random() * 4000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
