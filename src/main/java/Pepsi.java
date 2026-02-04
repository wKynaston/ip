import java.util.Scanner;

public class Pepsi {

    public static final String LONGLINE =
            "____________________________________________________________\n";

    // storage
    private static final String[] catalogue = new String[100];
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
                        + "Stop wasting my time this is not Coca-Cola!\n"
                        + LONGLINE);
                continue;
            }

            String[] parts = input.split("\\s+");

            if (isMarkCommand(parts)) {
                handleMarking(parts);
            } else {
                boolean keepGoing = handleActions(input);
                if (!keepGoing) {
                    break;
                }
            }
        }
    }

    private static boolean handleActions(String input) {
        switch (input.toLowerCase()) {
        case "bye":
            System.out.println(LONGLINE
                    + "Bye. Hope to see you again soon!\n"
                    + LONGLINE);
            return false;

        case "list":
            listTasks();
            return true;

        default:
            addTask(input);
            return true;
        }
    }

    private static boolean isMarkCommand(String[] parts) {
        return (parts[0].equalsIgnoreCase("mark")
                || parts[0].equalsIgnoreCase("unmark"));
    }

    private static void handleMarking(String[] parts) {
        if (parts.length != 2) {
            System.out.println(LONGLINE
                    + "I know in Coca-Cola there is nothing to mark but not Pepsi!\n"
                    + LONGLINE);
            return;
        }

        try {
            int number = Integer.parseInt(parts[1]);

            if (parts[0].equalsIgnoreCase("mark")) {
                Marker.mark(catalogue, fill, number, LONGLINE);
            } else {
                Marker.unmark(catalogue, fill, number, LONGLINE);
            }

        } catch (NumberFormatException e) {
            System.out.println(LONGLINE
                    + "Stop giving me scraps I am not Coke!\n"
                    + LONGLINE);
        }
    }

    private static void addTask(String input) {
        catalogue[fill++] = "[ ] " + input;
        System.out.println(LONGLINE
                + "added: " + input + "\n"
                + LONGLINE);
    }

    private static void listTasks() {
        System.out.println(LONGLINE);
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
        System.out.println("Hello! I'm Pepsi\nWhat can I do for you?\n" + LONGLINE);
    }
}