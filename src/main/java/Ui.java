import java.util.Scanner;

/**
 * Handles user interactions by displaying messages and reading user input.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the user's command input.
     *
     * @return The trimmed command input as a string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }


    public void showWelcome() {
        System.out.println("Hello! I'm Alex");
        System.out.println("What can I do for you?");
        System.out.println();
    }


    public void showGoodbye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Goodbye! Have a great day ahead!");
        System.out.println("    ____________________________________________________________");
    }


    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Displays a general message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
