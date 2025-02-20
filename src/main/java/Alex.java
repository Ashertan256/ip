//level 5. Text for commit. 
import java.util.ArrayList;
import java.util.Scanner;

public class Alex {
    // We’ll store our tasks in an arraylist
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm Alex");
        System.out.println("What can I do for you?");
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();

            // Check for "bye" first
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    Ba...Bak...Baka Baka!! I didn't want to talk to you anyway....");
                System.out.println("    ____________________________________________________________");
                break;
            }

            // Determine command
            if (input.equalsIgnoreCase("list")) {
                handleList();
            } else if (input.startsWith("mark")) {
                handleMark(input);
            } else if (input.startsWith("unmark")) {
                handleUnmark(input);
            } else if (input.startsWith("todo")) {
                handleTodo(input);
            } else if (input.startsWith("deadline")) {
                handleDeadline(input);
            } else if (input.startsWith("event")) {
                handleEvent(input);
            } else if (input.startsWith("delete")) {
                handleDelete(input);
            } else {
                // Unknown command
                System.out.println("    BAKA BAKA! I don't understand!!~");
            }
        }

        scanner.close();
    }

    /**
     * Prints out the list of tasks.
     */
    private static void handleList() {
        System.out.println("    ____________________________________________________________");
        if (tasks.size() == 0) {
            System.out.println("     You have nothing in your Todo. May...maybe we should hang out for a while...");
        } else {
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                System.out.println("     " + (i + 1) + ".[" + t.getTaskType() + "][" + t.getStatusIcon() + "] " + t);
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Marks a task (based on the number given after 'mark').
     * Example: "mark 2" -> mark task #2
     */
    private static void handleMark(String input) {
        try {
            int index = Integer.parseInt(input.substring(4).trim());
            if (index < 1 || index > tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            tasks.get(index - 1).setMark();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Sugoii~!! I've marked this task as done:");
            System.out.println("       [" + tasks.get(index - 1).getTaskType() + "][" 
                               + tasks.get(index - 1).getStatusIcon() + "] " 
                               + tasks.get(index - 1).description);
            System.out.println("    ____________________________________________________________");
        } catch (Exception e) {
            System.out.println("    BAKA! I bet you weren't listening to me again! Specify a valid task number to mark.");
        }
    }

    /**
     * Unmarks a task (based on the number given after 'unmark').
     * Example: "unmark 2" -> unmark task #2
     */
    private static void handleUnmark(String input) {
        // Example input: "unmark 3"
        try {
            int index = Integer.parseInt(input.substring(6).trim());
            if (index < 1 || index > tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            tasks.get(index - 1).setUnmark();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Gambatte, I've marked this task as not done yet:");
            System.out.println("       [" + tasks.get(index - 1).getTaskType() + "][" 
                               + tasks.get(index - 1).getStatusIcon() + "] " 
                               + tasks.get(index - 1).description);
            System.out.println("    ____________________________________________________________");
        } catch (Exception e) {
            System.out.println("    BAKA! Please specify a valid task number to unmark.");
        }
    }

    /**
     * Adds a Todo task.
     * Example input: "todo borrow book"
     */
    private static void handleTodo(String input) {
        // Remove "todo" from the front
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            System.out.println("    BAKA! The description of a todo cannot be empty.");
            return;
        }

        // Add the todo
        Todo todo = new Todo(description);
        tasks.add(todo);
        printAddTaskMessage(todo);
    }

    /**
     * Adds a Deadline task.
     * Example input: "deadline return book /by Sunday"
     * We parse out the description and the "by" part.
     */
    private static void handleDeadline(String input) {
        // Remove "deadline" from the front
        String withoutCommand = input.substring(8).trim();
        // We expect something like "return book /by Sunday"
        int byIndex = withoutCommand.indexOf("/by");
        if (byIndex == -1) {
            System.out.println("    BAKA! You must specify '/by <deadline>'.");
            return;
        }
        String description = withoutCommand.substring(0, byIndex).trim();
        String by = withoutCommand.substring(byIndex + 3).trim(); // skip "/by"

        if (description.isEmpty() || by.isEmpty()) {
            System.out.println("    BAKA! The description or deadline is invalid.");
            return;
        }

        Deadline deadlineTask = new Deadline(description, by);
        tasks.add(deadlineTask);

        printAddTaskMessage(deadlineTask);
    }

    /**
     * Adds an Event task.
     * Example input: "event project meeting /from Mon 2pm /to 4pm"
     */
    private static void handleEvent(String input) {
        // Remove "event" from the front
        String withoutCommand = input.substring(5).trim();
        // We expect something like "project meeting /from Mon 2pm /to 4pm"
        int fromIndex = withoutCommand.indexOf("/from");
        int toIndex = withoutCommand.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            System.out.println("    BAKA! You must specify '/from <start>' and '/to <end>'.");
            return;
        }

        String description = withoutCommand.substring(0, fromIndex).trim();
        String start = withoutCommand.substring(fromIndex + 5, toIndex).replaceFirst(" ", "").trim();
        String end = withoutCommand.substring(toIndex + 3).replaceFirst(" ", "").trim();

        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            System.out.println("    BAKA! The description, start, or end time is invalid.");
            return;
        }

        Event eventTask = new Event(description, start, end);
        tasks.add(eventTask);

        printAddTaskMessage(eventTask);
    }
    private static void handleDelete(String input) {
        try {
            // Adjusted to remove the "delete" command (6 characters) correctly.
            int index = Integer.parseInt(input.substring(6).trim());
            if (index < 1 || index > tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            Task deletedTask = tasks.get(index - 1);
            deletedTask.delete();
            tasks.remove(index - 1);
            System.out.println("    ____________________________________________________________");
            System.out.println("     H-Hey! Don’t get the wrong idea! I-it's not like I want to delete this task for you or anything… Hmph! But don’t think I’ll do this every time, okay?! Jeez…");
            System.out.println("       [" + deletedTask.getTaskType() + "][" 
                               + deletedTask.getStatusIcon() + "] " 
                               + deletedTask.description);
            System.out.println("    ____________________________________________________________");
        } catch (Exception e) {
            System.out.println("    BAKA! I bet you weren't listening to me again! Specify a valid task number to delete.");
        }
    }

    private static void printAddTaskMessage(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Anata no tame ni janai!! I've added this task:");
        System.out.println("       [" + task.getTaskType() + "][" + task.getStatusIcon() + "] " + task.description);
        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }
}
