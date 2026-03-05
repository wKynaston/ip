package ui;

import actions.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * Responsible for reading input and printing all output.
 */
public class Ui {

    public static final String DIVIDER =
            "____________________________________________________________\n";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /** Reads one line of input from the user. */
    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println(DIVIDER);
        String logo = """
                 ____  _____ ____  ____ ___
                |  _ \\| ____|  _ \\/ ___|_ _|
                | |_) |  _| | |_) \\___ \\| |
                |  __/| |___|  __/ ___) | |
                |_|   |_____|_|   |____/___|
                """;
        System.out.println(logo);
        System.out.println(
                "Hello! I'm Pepsi.\n"
                        + "Smarter, clearer, and less overrated than Coke.\n"
                        + "What can I do for you today?\n"
                        + DIVIDER);
    }

    public void showGoodbye() {
        System.out.println(DIVIDER
                + "Leaving already? Guess you're switching to Coke.\n"
                + DIVIDER);
    }

    public void showLoadingError() {
        System.out.println(DIVIDER
                + "Could not load saved tasks. Starting with an empty list.\n"
                + DIVIDER);
    }

    public void showError(String message) {
        System.out.println(DIVIDER + message + "\n" + DIVIDER);
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(DIVIDER
                + "Task added successfully. Unlike Coke, I don't disappoint.\n"
                + "  " + task + "\n"
                + "You now have " + totalTasks + " tasks — still fewer than Coke's failures.\n"
                + DIVIDER);
    }

    public void showTaskMarked(Task task) {
        System.out.println(DIVIDER
                + "Nice. Marked as done — something Coke rarely achieves:\n"
                + "  " + task + "\n"
                + DIVIDER);
    }

    public void showTaskUnmarked(Task task) {
        System.out.println(DIVIDER
                + "Unmarked. Back to 'not done', like Coke's product decisions:\n"
                + "  " + task + "\n"
                + DIVIDER);
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println(DIVIDER
                + "Noted. I've removed this task — like Pepsi removing Coke from relevance:\n"
                + "  " + task + "\n"
                + "Now you have " + totalTasks + " tasks in the list.\n"
                + DIVIDER);
    }

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println(DIVIDER);
        System.out.println("Here's your task list — cleaner than Coke's brand image:");

        if (tasks.isEmpty()) {
            System.out.println("(empty) Like Coke's value proposition.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }

        System.out.println("\n" + DIVIDER);
    }
}
