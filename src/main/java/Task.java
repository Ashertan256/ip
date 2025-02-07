public class Task {
    protected String description;
    protected boolean isDone;
<<<<<<< HEAD
    protected String taskType;  // "T", "D", or "E"
=======
>>>>>>> 2fcc2a0a2a91ae0b29838bbf662114246c13e86a

    public Task(String description) {
        this.description = description;
        this.isDone = false;
<<<<<<< HEAD
        this.taskType = ""; // default
    }

=======
    }
    
>>>>>>> 2fcc2a0a2a91ae0b29838bbf662114246c13e86a
    public void setMark() {
        this.isDone = true;
    }

    public void setUnmark() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

<<<<<<< HEAD
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        // By default, just return the description
        return description;
    }
}
=======
}
>>>>>>> 2fcc2a0a2a91ae0b29838bbf662114246c13e86a
