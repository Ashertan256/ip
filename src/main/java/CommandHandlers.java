public class CommandHandlers {

    public static void handleMark(String input, TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(input.substring(4).trim()) - 1;
            tasks.markTask(index, ui);
        } catch (Exception e) {
            ui.showMessage("   Specify a valid task number to mark.");
        }
    }

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

    public static void handleDelete(String input, TaskList tasks, Ui ui) {
        try {
            int index = Integer.parseInt(input.substring(6).trim()) - 1;
            tasks.deleteTask(index, ui);
        } catch (Exception e) {
            ui.showMessage("    Oops! Specify a valid task number to delete.");
        }
    }
}
