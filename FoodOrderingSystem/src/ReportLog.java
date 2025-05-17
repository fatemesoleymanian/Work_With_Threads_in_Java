import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class ReportLog {

    private final List<PreparedOrder> preparedOrders = new CopyOnWriteArrayList<>();

    public void log(PreparedOrder order){
        preparedOrders.add(order);
    }
    public void printReport(){
        saveToFile();
        System.out.println("\n📄 Final Report:");
        System.out.println(preparedOrders.size());
        for (PreparedOrder order : preparedOrders) {
            System.out.printf("✅ %s | %d ms%n", order.getDescription(), order.getDuration());
        }
    }
    private void saveToFile(){
        List<String> lines = preparedOrders.stream().map(order -> order.getDescription() + " | " + order.getDuration()).collect(Collectors.toList());
        Path logFile = Paths.get("orders.txt");
        try {
            Files.write(logFile,lines, StandardCharsets.UTF_8,StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
