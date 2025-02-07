public class Event extends Task {
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.taskType = "E";
    }

    @Override
    public String toString() {
        // e.g. "project meeting (from: Mon 2pm to: 4pm)"
        return super.description + " (from: " + start + " to: " + end + ")";
    }
}
