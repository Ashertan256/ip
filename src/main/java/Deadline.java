/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private String deadline;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline for the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.taskType = "D";
    }

    @Override
    public String toString() {
        return super.description + " (by: " + deadline + ")";
    }
    
    /**
     * Gets the deadline of the task.
     *
     * @return The deadline as a string.
     */
    public String getDeadline() {
        return deadline;
    }
}