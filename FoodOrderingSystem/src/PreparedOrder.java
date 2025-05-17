public class PreparedOrder {
    private String description;
    private long startTime;
    private long endTime;

    public String getDescription() {
        return description;
    }

    public PreparedOrder(String description, long startTime, long endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public long getDuration(){
        return endTime - startTime;
    }
}
