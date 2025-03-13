/**
 * Represents a task with a description, completion status, and task type.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    public void setMark() {
        this.isDone = true;
    }

    public void setUnmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * The type of task (e.g., "T" for Todo, "D" for Deadline, "E" for Event).
     */
    public String getTaskType() {
        return this.taskType;
    }
    
    /**
     * Returns a string representation of the task.
     *
     * @return The task description.
     */
    @Override
    public String toString() {
        return description;
    }
}