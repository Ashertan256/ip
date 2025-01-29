import java.util.Scanner;

public class Alex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Alex");
        System.out.println("What can I do for you?");
        System.out.println();

        String input;
        do {
            input = scanner.nextLine();

            if (!input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(input); // Echo the user input
                System.out.println("____________________________________________________________");
            }
        } while (!input.equalsIgnoreCase("bye"));
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        scanner.close();
    }
}
