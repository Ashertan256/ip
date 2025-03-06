import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public void addTask(Task task, Ui ui) {
        tasks.add(task);
        ui.showMessage("    ____________________________________________________________");
        ui.showMessage("    I've added this task:");
        ui.showMessage("       [" + task.getTaskType() + "][" + task.getStatusIcon() + "] " + task.description);
        ui.showMessage("     Now you have " + tasks.size() + " tasks in the list.");
        ui.showMessage("    ____________________________________________________________");
    }

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
