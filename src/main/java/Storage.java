import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving of tasks to a file.
 * Allows persistent storage of tasks between program runs.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks loaded from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        File file = new File(filePath);
        if (!file.exists()) {
            //if no exist, reurn empty
            return loadedTasks;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Task task = parseTaskLine(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
        }
        return loadedTasks;
    }


    /**
     * Saves the given list of tasks to the storage file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                writer.write(convertTaskToString(task));
                writer.newLine();
            }
        }
    }

    /**
     * Parses a line from the storage file into a Task object.
     *
     * @param line A line from the storage file.
     * @return The corresponding Task object, or {@code null} if the format is invalid.
     */
    private Task parseTaskLine(String line) {
        String[] parts = line.split("\\|");
        // Trim each part
        for (int i = 0; i < parts.length; i++) {
            parts[i] = parts[i].trim();
        }
        if (parts.length < 3) {
            return null;
        }
        String taskType = parts[0];
        boolean isDone = Boolean.parseBoolean(parts[1]);
        String description = parts[2];
        Task task = null;

        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length >= 4) {
                String deadline = parts[3];
                task = new Deadline(description, deadline);
            }
            break;
        case "E":
            if (parts.length >= 5) {
                String start = parts[3];
                String end = parts[4];
                task = new Event(description, start, end);
            }
            break;
        default:
            return null;
        }

        if (task != null && isDone) {
            task.setMark();
        }
        return task;
    }

    /**
     * Converts a Task object into a string format suitable for storage.
     *
     * @param task The task to be converted.
     * @return A formatted string representing the task.
     */
    private String convertTaskToString(Task task) {
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
        return sb.toString();
    }
}
