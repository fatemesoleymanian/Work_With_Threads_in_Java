import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ReportLog {

    private final List<PreparedOrder> preparedOrders = new CopyOnWriteArrayList<>();

    public void log(PreparedOrder order){
        preparedOrders.add(order);
    }
    public void printReport(){
        System.out.println("\n📄 Final Report:");
        System.out.println(preparedOrders.size());
        for (PreparedOrder order : preparedOrders) {
            System.out.printf("✅ %s | %d ms%n", order.getDescription(), order.getDuration());
        }
    }
}
