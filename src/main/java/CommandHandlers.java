/**
 * Provides static methods to handle various user commands related to task management.
 * Commands include marking, unmarking, adding, deleting, and searching for tasks.
 */
public class CommandHandlers {

    /**
     * Handles the command to mark a task as completed.
     *
     * @param input The user input containing the task number.
     * @param tasks The TaskList containing the tasks.
     * @param ui The UI object used to display messages.
     */
    public static void handleMark(String input, TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(input.substring(4).trim()) - 1;
            tasks.markTask(index, ui);
        } catch (Exception e) {
            ui.showMessage("   Specify a valid task number to mark.");
        }
    }

    /**
     * Handles the command to unmark a task as not completed.
     *
     * @param input The user input containing the task number.
     * @param tasks The TaskList containing the tasks.
     * @param ui The UI object used to display messages.
     */
    public static void handleUnmark(String input, TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(input.substring(6).trim()) - 1;
            tasks.unmarkTask(index, ui);
        } catch (Exception e) {
            ui.showMessage("    Oops! Please specify a valid task number to unmark.");
        }
    }

    public static void handleTodo(String input, TaskList tasks, Ui ui) {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            ui.showMessage("    Oops! The description of a todo cannot be empty.");
            return;
        }
        Todo todo = new Todo(description);
        tasks.addTask(todo, ui);
    }

    /**
     * Handles the command to add a new deadline task.
     *
     * @param input The user input containing the task description and deadline.
     * @param tasks The TaskList where the new task will be added.
     * @param ui The UI object used to display messages.
     */
    public static void handleDeadline(String input, TaskList tasks, Ui ui) {
        String withoutCommand = input.substring(8).trim();
        int byIndex = withoutCommand.indexOf("/by");
        if (byIndex == -1) {
            ui.showMessage("    Oops! You must specify '/by <deadline>'.");
            return;
        }
        String description = withoutCommand.substring(0, byIndex).trim();
        String by = withoutCommand.substring(byIndex + 3).trim();
        if (description.isEmpty() || by.isEmpty()) {
            ui.showMessage("    Oops! The description or deadline is invalid.");
            return;
        }
        Deadline deadlineTask = new Deadline(description, by);
        tasks.addTask(deadlineTask, ui);
    }

    /**
     * Handles the command to add a new event task.
     *
     * @param input The user input containing the task description, start time, and end time.
     * @param tasks The TaskList where the new task will be added.
     * @param ui The UI object used to display messages.
     */
    public static void handleEvent(String input, TaskList tasks, Ui ui) {
        String withoutCommand = input.substring(5).trim();
        int fromIndex = withoutCommand.indexOf("/from");
        int toIndex = withoutCommand.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            ui.showMessage("    Oops! You must specify '/from <start>' and '/to <end>'.");
            return;
        }
        String description = withoutCommand.substring(0, fromIndex).trim();
        String start = withoutCommand.substring(fromIndex + 5, toIndex).trim();
        String end = withoutCommand.substring(toIndex + 3).trim();
        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            ui.showMessage("    Oops! The description, start, or end time is invalid.");
            return;
        }
        Event eventTask = new Event(description, start, end);
        tasks.addTask(eventTask, ui);
    }

    /**
     * Handles the command to delete a task.
     *
     * @param input The user input containing the task number to delete.
     * @param tasks The TaskList containing the tasks.
     * @param ui The UI object used to display messages.
     */
    public static void handleDelete(String input, TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(input.substring(6).trim()) - 1;
            tasks.deleteTask(index, ui);
        } catch (Exception e) {
            ui.showMessage("    Oops! Specify a valid task number to delete.");
        }
    }

    /**
     * Handles the command to find tasks based on a keyword.
     *
     * @param input The user input containing the keyword to search for.
     * @param tasks The TaskList containing the tasks.
     * @param ui The UI object used to display messages.
     */
    public static void handleFind(String input, TaskList tasks, Ui ui) {
        // Extract the search keyword
        String keyword = input.substring(4).trim(); // remove the word "find"
        if (keyword.isEmpty()) {
            ui.showMessage("    Oops! Please specify a keyword to search for.");
            return;
        }
    
        tasks.findTasks(keyword, ui);
    }
}
