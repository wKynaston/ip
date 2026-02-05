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
            String input = in.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println(LONGLINE
                        + "Typing nothing? That’s very Coke behaviour.\n"
                        + LONGLINE);
                continue;
            }

            String[] parts = input.split("\\s+", 2);
            String cmd = parts[0].toLowerCase();
            String rest = (parts.length == 2) ? parts[1].trim() : "";

            if (cmd.equals("mark") || cmd.equals("unmark")) {
                handleMarking(cmd, rest);
                continue;
            }

            boolean keepGoing = handleActions(cmd, rest);
            if (!keepGoing) {
                break;
            }
        }
    }

    private static boolean handleActions(String cmd, String rest) {
        switch (cmd) {
        case "bye":
            System.out.println(LONGLINE
                    + "Leaving already? Guess you’re switching to Coke.\n"
                    + LONGLINE);
            return false;

        case "list":
            listTasks();
            return true;

        case "todo":
            addTodo(rest);
            return true;

        case "deadline":
            addDeadline(rest);
            return true;

        case "event":
            addEvent(rest);
            return true;

        default:
            System.out.println(LONGLINE
                    + "Unknown command. Must be something Coke invented.\n"
                    + "Try: todo / deadline / event / list / mark / unmark / bye\n"
                    + LONGLINE);
            return true;
        }
    }

    private static void addTodo(String rest) {
        if (rest.isEmpty()) {
            System.out.println(LONGLINE
                    + "A todo with no description? Classic Coke quality.\n"
                    + "Use: todo <description>\n"
                    + LONGLINE);
            return;
        }

        Task task = new Todo(rest);
        catalogue[fill++] = task;
        printAdded(task);
    }

    private static void addDeadline(String rest) {
        // expected: <desc> /by <when>
        String[] split = rest.split("\\s+/by\\s+", 2);

        if (split.length != 2 || split[0].trim().isEmpty() || split[1].trim().isEmpty()) {
            System.out.println(LONGLINE
                    + "That deadline format is as vague as Coke’s ingredients.\n"
                    + "Use: deadline <description> /by <when>\n"
                    + LONGLINE);
            return;
        }

        String desc = split[0].trim();
        String by = split[1].trim();

        Task task = new Deadline(desc, by);
        catalogue[fill++] = task;
        printAdded(task);
    }

    private static void addEvent(String rest) {
        // expected: <desc> /from <start> /to <end>
        String[] splitFrom = rest.split("\\s+/from\\s+", 2);

        if (splitFrom.length != 2 || splitFrom[0].trim().isEmpty()) {
            System.out.println(LONGLINE
                    + "An event without timing? That’s very Coke-planned.\n"
                    + "Use: event <description> /from <start> /to <end>\n"
                    + LONGLINE);
            return;
        }

        String desc = splitFrom[0].trim();
        String[] splitTo = splitFrom[1].split("\\s+/to\\s+", 2);

        if (splitTo.length != 2 || splitTo[0].trim().isEmpty() || splitTo[1].trim().isEmpty()) {
            System.out.println(LONGLINE
                    + "Start time but no end time? Coke scheduling again.\n"
                    + "Use: event <description> /from <start> /to <end>\n"
                    + LONGLINE);
            return;
        }

        String from = splitTo[0].trim();
        String to = splitTo[1].trim();

        Task task = new Event(desc, from, to);
        catalogue[fill++] = task;
        printAdded(task);
    }

    private static void printAdded(Task task) {
        System.out.println(LONGLINE
                + "Task added successfully. Unlike Coke, I don’t disappoint.\n"
                + "  " + task + "\n"
                + "You now have " + fill + " tasks — still fewer than Coke’s failures.\n"
                + LONGLINE);
    }


    private static void handleMarking(String cmd, String rest) {
        if (rest.isEmpty()) {
            System.out.println(LONGLINE
                    + "Mark what? Coke-level instructions detected.\n"
                    + "Use: " + cmd + " <task number>\n"
                    + LONGLINE);
            return;
        }

        if (!isAllDigits(rest)) {
            System.out.println(LONGLINE
                    + "That’s not a number. Coke maths strikes again.\n"
                    + LONGLINE);
            return;
        }

        int number = Integer.parseInt(rest);
        int idx = number - 1;

        if (idx < 0 || idx >= fill || catalogue[idx] == null) {
            System.out.println(LONGLINE
                    + "Invalid task number. Even Coke could count better than that.\n"
                    + LONGLINE);
            return;
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

    private static boolean isAllDigits(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;
            }
        }
        return true;
    }


    private static void listTasks() {
        System.out.println(LONGLINE);
        System.out.println("Here’s your task list — cleaner than Coke’s brand image:");
        for (int i = 0; i < fill; i++) {
            System.out.println((i + 1) + ". " + catalogue[i]);
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