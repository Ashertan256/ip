/**
 * Represents a Todo task, which is a basic task without a deadline or event time.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.taskType = "T";
    }
}