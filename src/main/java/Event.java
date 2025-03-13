/**
 * Represents an event task with a start and end time.
 */
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
        return super.description + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time as a string.
     */
    public String getStart() {
        return start;
    }
    
    /**
     * Gets the end time of the event.
     *
     * @return The end time as a string.
     */
    public String getEnd() {
        return end;
    }
}