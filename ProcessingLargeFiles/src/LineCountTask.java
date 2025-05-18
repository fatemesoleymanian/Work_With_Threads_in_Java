import java.util.List;
import java.util.concurrent.RecursiveTask;

public class LineCountTask extends RecursiveTask<Integer> {
    private final int TRESHOLD = 100;
    private List<String> lines;
    private int start,end;

    public LineCountTask(List<String> lines, int start, int end) {
        this.lines = lines;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= TRESHOLD) return end - start;
        int mid = (start + end) / 2;
        LineCountTask left = new LineCountTask(lines, start, mid);
        LineCountTask right = new LineCountTask(lines, mid, end);

        left.fork();
        int rightResult = right.compute();
        int leftResult = left.join();
        return leftResult + rightResult;
    }
}
