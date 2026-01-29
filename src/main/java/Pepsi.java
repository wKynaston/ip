import java.util.Scanner;

public class Pepsi {
    public static final String longline = "____________________________________________________________\n";
    //creates a string of 100 indexes
    public static String[] catalogue = new String[100];
    //Stores the position that needs to be filled next
    public static int fill = 0;
    public static boolean repeat(String input){
        switch(input.toLowerCase()) {
        case ("bye"):
            System.out.println(longline + "Bye. Hope to see you again soon!\n" + longline);
            return false;
        case ("list"):
            System.out.println(longline);
            for (int i = 0; i < 100; i++) {
                System.out.println(i+1 + "." + catalogue[i] + "\n");
                if(catalogue[i+1] == null){
                    System.out.println(longline);
                    return true;
                }
            }
        default:
            catalogue[fill] = input;
            System.out.println(longline+ "added: " + input + "\n" + longline);
            fill++;
            return true;
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
        while (true){ //this line will keep running no matter what
            String input = in.nextLine(); //This will wait for user input
            boolean keepGoing = repeat(input);
            if(!keepGoing){
                //if repeat returns false will break the while loop and exit
                break;
            }
        }

    }

}
