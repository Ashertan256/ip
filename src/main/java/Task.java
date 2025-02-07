public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;  // "T", "D", or "E"

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = ""; // default
    }

    public void setMark() {
        this.isDone = true;
    }

    public void setUnmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        // By default, just return the description
        return description;
    }
}
