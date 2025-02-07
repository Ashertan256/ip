public class Deadline extends Task {
    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.taskType = "D";
    }

    @Override
    public String toString() {
        // e.g. "return book (by: Sunday)"
        return super.description + " (by: " + deadline + ")";
    }
}
