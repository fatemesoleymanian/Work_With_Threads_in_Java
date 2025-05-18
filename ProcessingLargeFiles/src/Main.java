import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        List<String> lines = null;
        try {
            lines = readFileLines("bigFile.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ForkJoinPool pool = new ForkJoinPool();
        LineCountTask task = new LineCountTask(lines,0,lines.size());

        int result = pool.invoke(task);
        System.out.println("📄 Total lines: " + result);
    }
    static List<String> readFileLines(String path) throws IOException {
        return Files.readAllLines(new File(path).toPath());
    }
}