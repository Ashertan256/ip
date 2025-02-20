import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Alex {
    // Store tasks in an ArrayList
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_PATH = "tasks.txt";

    public static void main(String[] args) {
        // Load tasks from disk on startup
        loadTasks();
        
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
                saveTasks();
            } else if (input.startsWith("unmark")) {
                handleUnmark(input);
                saveTasks();
            } else if (input.startsWith("todo")) {
                handleTodo(input);
                saveTasks();
            } else if (input.startsWith("deadline")) {
                handleDeadline(input);
                saveTasks();
            } else if (input.startsWith("event")) {
                handleEvent(input);
                saveTasks();
            } else if (input.startsWith("delete")) {
                handleDelete(input);
                saveTasks();
            } else {
                // Unknown command
                System.out.println("    BAKA BAKA! I don't understand!!~");
            }
        }

        scanner.close();
    }

    /**
     * Loads tasks from the file on startup.
     */
    private static void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");
                // Trim each part
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                if (parts.length < 3) {
                    continue;
                }
                String taskType = parts[0];
                boolean isDone = Boolean.parseBoolean(parts[1]);
                String description = parts[2];
                Task task = null;
                if (taskType.equals("T")) {
                    task = new Todo(description);
                } else if (taskType.equals("D") && parts.length >= 4) {
                    String deadline = parts[3];
                    task = new Deadline(description, deadline);
                } else if (taskType.equals("E") && parts.length >= 5) {
                    String start = parts[3];
                    String end = parts[4];
                    task = new Event(description, start, end);
                }
                if (task != null) {
                    if (isDone) {
                        task.setMark();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Saves the current task list to the file.
     */
    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                StringBuilder sb = new StringBuilder();
                sb.append(task.getTaskType()).append("|")
                  .append(task.isDone).append("|")
                  .append(task.description);
                if (task instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    sb.append("|").append(d.getDeadline());
                } else if (task instanceof Event) {
                    Event e = (Event) task;
                    sb.append("|").append(e.getStart()).append("|").append(e.getEnd());
                }
                writer.write(sb.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
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
     */
    private static void handleUnmark(String input) {
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
     */
    private static void handleTodo(String input) {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            System.out.println("    BAKA! The description of a todo cannot be empty.");
            return;
        }
        Todo todo = new Todo(description);
        tasks.add(todo);
        printAddTaskMessage(todo);
    }

    /**
     * Adds a Deadline task.
     */
    private static void handleDeadline(String input) {
        String withoutCommand = input.substring(8).trim();
        int byIndex = withoutCommand.indexOf("/by");
        if (byIndex == -1) {
            System.out.println("    BAKA! You must specify '/by <deadline>'.");
            return;
        }
        String description = withoutCommand.substring(0, byIndex).trim();
        String by = withoutCommand.substring(byIndex + 3).trim();
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
     */
    private static void handleEvent(String input) {
        String withoutCommand = input.substring(5).trim();
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

    /**
     * Deletes a task (based on the number given after 'delete').
     */
    private static void handleDelete(String input) {
        try {
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
