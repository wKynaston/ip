import java.util.Scanner;

public class Pepsi {
    public static final String longline = "____________________________________________________________\n";
    //creates a string of 100 indexes
    public static String[] catalogue = new String[100];
    //Stores the position that needs to be filled next
    public static int fill = 0;

    public static boolean actionables(String input) {
        switch (input.toLowerCase()) {
        case ("bye"):
            System.out.println(longline + "Bye. Hope to see you again soon!\n" + longline);
            return false;
        case ("list"):
            System.out.println(longline);
            for (int i = 0; i < 100; i++) {
                System.out.println(i + 1 + "." + catalogue[i] + "\n");
                if (i == 99 || catalogue[i+1] == null) {
                    System.out.println(longline);
                    return true;
                }
            }
        default:
            catalogue[fill] = "[ ] " + input;
            System.out.println(longline + "added: " + input + "\n" + longline);
            fill++;
            return true;
        }
    }

    public static void actionables(String action, int position) {
        int idx = position - 1;
        if (idx >= 0 && idx < fill && catalogue[idx] != null) {
            if (action.equalsIgnoreCase("mark")) {
                catalogue[idx] = catalogue[idx].replaceFirst("\\[ ]", "[x]");
                System.out.println(longline + "Nice! I've marked this task as done:\n" + catalogue[idx] +
                        "\n" + longline);
            }
             else if (action.equalsIgnoreCase("unmark")) {
                catalogue[idx] = catalogue[idx].replaceFirst("\\[x]", "[ ]");
                System.out.println(longline + "Nice! I've marked this task as not done yet :\n" + catalogue[idx] +
                        "\n" + longline);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(longline);
        String logo = """
                 ____  _____ ____  ____ ___
                |  _ \\| ____|  _ \\/ ___|_ _|
                | |_) |  _| | |_) \\___ \\| |
                |  __/| |___|  __/ ___) | |
                |_|   |_____|_|   |____/___|
                """;
        System.out.println(logo);
        System.out.println("Hello! I'm Pepsi\n" + "What can I do for you?\n" + longline);
        //Create an object called in that can read input typed by the user from the keyboard.
        Scanner in = new Scanner(System.in);
        while (true) { //this line will keep running no matter what
            boolean keepGoing = true;
            String input = in.nextLine(); //This will wait for user input
            input = input.trim();
            // if user entered nothing (or only spaces)
            if (input.isEmpty()) {
                System.out.println(longline + "Stop wasting my time this is not Coca-cola!\n" + longline);
                continue;
            }
            //this splits the two characters into their own part
            String[] parts = input.split("\\s+");
            if (parts[0].equalsIgnoreCase("mark") || parts[0].equalsIgnoreCase("unmark")) {
                if (parts.length != 2) {
                    //checks if user inputs anything after mark/unmark
                    System.out.println(longline + "I know in Coca-Cola there is nothing to mark but not Pepsi!" +
                            ".\n" + longline);
                    continue;
                }
                try {
                    int number = Integer.parseInt(parts[1]);
                    actionables(parts[0], number);
                } catch (NumberFormatException e) {
                    //checks if the user input a valid number after unmark
                    System.out.println(longline + "Stop giving me scraps I am not Coke!.\n" + longline);
                }
            }
            else{
                keepGoing = actionables(input);
            }
            if (!keepGoing) {
                //if repeat returns false will break the while loop and exit
                break;
            }
        }
    }
}
