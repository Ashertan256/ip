import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    //scanner for reading stuff
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    //welcome msg
    public void showWelcome() {
        System.out.println("Hello! I'm Alex");
        System.out.println("What can I do for you?");
        System.out.println();
    }

    //bye msg
    public void showGoodbye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Goodbye! Have a great day ahead!");
        System.out.println("    ____________________________________________________________");
    }

    //error handling
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
