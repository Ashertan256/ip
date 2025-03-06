import java.io.IOException;

public class Alex {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Alex(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            ui.showError("Error loading tasks: " + e.getMessage());
        }
    }


    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            if (Parser.isByeCommand(fullCommand)) {
                ui.showGoodbye();
                isExit = true;
            } else {
                try {
                    Parser.handleCommand(fullCommand, tasks, ui);
                    storage.saveTasks(tasks.getAllTasks());
                } catch (Exception e) {
                    //parsing errors
                    ui.showError(e.getMessage());
                }
            }
        }
    }


    public static void main(String[] args) {
        new Alex("tasks.txt").run();
    }
}
