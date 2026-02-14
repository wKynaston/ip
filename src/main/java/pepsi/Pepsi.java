package pepsi;

import actions.Task;
import exceptions.commandException;
import exceptions.commandParser;

import java.util.Scanner;

public class Pepsi {

    public static final String LONGLINE =
            "____________________________________________________________\n";

    private static final Task[] catalogue = new Task[100];
    private static int fill = 0;

    public static void main(String[] args) {
        greetings();
        manageList();
    }

    private static void manageList() {
        Scanner in = new Scanner(System.in);

        while (true) {
            String input = in.nextLine();

            try {
                commandParser.validateNotEmpty(input);

                input = input.trim();
                String[] parts = input.split("\\s+", 2);
                String cmd = parts[0].toLowerCase();
                String rest = (parts.length == 2) ? parts[1].trim() : "";

                if (cmd.equals("bye")) {
                    System.out.println(LONGLINE
                            + "Leaving already? Guess you’re switching to Coke.\n"
                            + LONGLINE);
                    break;
                }

                if (cmd.equals("list")) {
                    listTasks();
                    continue;
                }

                if (cmd.equals("mark") || cmd.equals("unmark")) {
                    int number = commandParser.parseTaskNumber(rest, cmd);
                    handleMarking(cmd, number);
                    continue;
                }

                if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
                    Task task = commandParser.parseTask(cmd, rest);
                    addTask(task);
                    continue;
                }

                throw new commandException("Unknown command. Must be something Coke invented.\n"
                        + "Try: todo / deadline / event / list / mark / unmark / bye");

            } catch (commandException e) {
                System.out.println(LONGLINE + e.getMessage() + "\n" + LONGLINE);
            }
        }
    }

    private static void addTask(Task task) {
        if (fill >= catalogue.length) {
            System.out.println(LONGLINE
                    + "Your list is full. Unlike Coke, I actually have limits.\n"
                    + LONGLINE);
            return;
        }

        catalogue[fill++] = task;

        System.out.println(LONGLINE
                + "Task added successfully. Unlike Coke, I don’t disappoint.\n"
                + "  " + task + "\n"
                + "You now have " + fill + " tasks — still fewer than Coke’s failures.\n"
                + LONGLINE);
    }

    private static void handleMarking(String cmd, int number) throws commandException {
        int idx = number - 1;

        if (idx < 0 || idx >= fill || catalogue[idx] == null) {
            throw new commandException("Invalid task number. Even Coke could count better than that.");
        }

        if (cmd.equals("mark")) {
            catalogue[idx].setDone(true);
            System.out.println(LONGLINE
                    + "Nice. Marked as done — something Coke rarely achieves:\n"
                    + "  " + catalogue[idx] + "\n"
                    + LONGLINE);
        } else {
            catalogue[idx].setDone(false);
            System.out.println(LONGLINE
                    + "Unmarked. Back to ‘not done’, like Coke’s product decisions:\n"
                    + "  " + catalogue[idx] + "\n"
                    + LONGLINE);
        }
    }

    private static void listTasks() {
        System.out.println(LONGLINE);
        System.out.println("Here’s your task list — cleaner than Coke’s brand image:");

        if (fill == 0) {
            System.out.println("(empty) Like Coke’s value proposition.");
        } else {
            for (int i = 0; i < fill; i++) {
                System.out.println((i + 1) + ". " + catalogue[i]);
            }
        }

        System.out.println("\n" + LONGLINE);
    }

    private static void greetings() {
        System.out.println(LONGLINE);
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
                        + LONGLINE);
    }
}