import java.util.Scanner;

public class Alex {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println("Hello! I'm Alex");
        System.out.println("What can I do for you?");
        System.out.println();

        String input;
        do {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("list")) {
                if (taskCount == 0) {
                    System.out.println("No tasks");
                } else {
                    System.out.println("    ____________________________________________________________");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println("    "+(i + 1) + ". " + tasks[i]);
                    }
                    System.out.println("    ____________________________________________________________");
                }
            } else if (!input.equalsIgnoreCase("bye")) {
                if(taskCount < 100){
                    tasks[taskCount] = input;
                    taskCount++;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("    added: " + input);
                    System.out.println("    ____________________________________________________________");
                }
                
            }
        } while (!input.equalsIgnoreCase("bye"));
        System.out.println("    ____________________________________________________________");

        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        scanner.close();
    }
}
