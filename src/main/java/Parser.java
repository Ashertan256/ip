import java.util.Locale;

public class Parser {

    public static boolean isByeCommand(String fullCommand) {
        return fullCommand.equalsIgnoreCase("bye");
    }


    public static void handleCommand(String fullCommand, TaskList tasks, Ui ui) {
        String input = fullCommand.trim().toLowerCase(Locale.ROOT);

        if (input.equals("list")) {
            tasks.listTasks(ui);
        } else if (input.startsWith("mark")) {
            CommandHandlers.handleMark(input, tasks, ui);
        } else if (input.startsWith("unmark")) {
            CommandHandlers.handleUnmark(input, tasks, ui);
        } else if (input.startsWith("todo")) {
            CommandHandlers.handleTodo(input, tasks, ui);
        } else if (input.startsWith("deadline")) {
            CommandHandlers.handleDeadline(input, tasks, ui);
        } else if (input.startsWith("event")) {
            CommandHandlers.handleEvent(input, tasks, ui);
        } else if (input.startsWith("delete")) {
            CommandHandlers.handleDelete(input, tasks, ui);
        } else {
            ui.showMessage("    I'm sorry about that! Could you please rephrase or give me more details?");
        }
    }
}
