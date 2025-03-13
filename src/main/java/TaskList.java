import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods to manage them,
 * including adding, deleting, listing, marking, unmarking, and searching for tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the list and displays a confirmation message.
     *
     * @param task The task to be added.
     * @param ui The UI object used to display messages.
     */
    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        ui.showMessage("    ____________________________________________________________");
        ui.showMessage("    I've added this task:");
        ui.showMessage("       [" + task.getTaskType() + "][" + task.getStatusIcon() + "] " + task.description);
        ui.showMessage("     Now you have " + tasks.size() + " tasks in the list.");
        ui.showMessage("    ____________________________________________________________");
    }


    /**
     * Deletes a task at the specified index and displays a confirmation message.
     *
     * @param index The index of the task to be deleted.
     * @param ui The UI object used to display messages.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void deleteTask(int index, Ui ui) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        ui.showMessage("    ____________________________________________________________");
        ui.showMessage("    The task has been removed. Let me know if you need anything else!");
        ui.showMessage("       [" + deletedTask.getTaskType() + "][" 
                       + deletedTask.getStatusIcon() + "] " 
                       + deletedTask.description);
        ui.showMessage("    ____________________________________________________________");
    }

    public void listTasks(Ui ui) {
        ui.showMessage("    ____________________________________________________________");
        if (tasks.isEmpty()) {
            ui.showMessage("     You have nothing in your Todo. Go and enjoy your day!");
        } else {
            ui.showMessage("     Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                ui.showMessage("     " + (i + 1) + ".[" + t.getTaskType() + "][" + t.getStatusIcon() + "] " + t);
            }
        }
        ui.showMessage("    ____________________________________________________________");
    }

    public void markTask(int index, Ui ui) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task t = tasks.get(index);
        t.setMark();
        ui.showMessage("    ____________________________________________________________");
        ui.showMessage("     Great~!! I've marked this task as done:");
        ui.showMessage("       [" + t.getTaskType() + "][" + t.getStatusIcon() + "] " + t.description);
        ui.showMessage("    ____________________________________________________________");
    }

    /**
     * Unmarks a task at the specified index as not done and displays a confirmation message.
     *
     * @param index The index of the task to be unmarked.
     * @param ui The UI object used to display messages.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void unmarkTask(int index, Ui ui) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task t = tasks.get(index);
        t.setUnmark();
        ui.showMessage("    ____________________________________________________________");
        ui.showMessage("     Good job, I've marked this task as not done yet:");
        ui.showMessage("       [" + t.getTaskType() + "][" + t.getStatusIcon() + "] " + t.description);
        ui.showMessage("    ____________________________________________________________");
    }
    
    /**
     * Finds and displays tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @param ui The UI object used to display messages.
     */
    public void findTasks(String keyword, Ui ui) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
    
        for (Task t : tasks) {
            if (t.description.toLowerCase().contains(keyword.toLowerCase())) {//lowercase to remove case sensitivity
                matchingTasks.add(t);
            }
        }
        ui.showMessage("    ____________________________________________________________");
        if (matchingTasks.isEmpty()) {
            ui.showMessage("    No tasks found matching \"" + keyword + "\".");
        } else {
            ui.showMessage("    Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task t = matchingTasks.get(i);
                ui.showMessage("     " + (i + 1) + ".[" + t.getTaskType() + "][" + t.getStatusIcon() + "] " + t);
            }
        }
        ui.showMessage("    ____________________________________________________________");
    }
    
}
