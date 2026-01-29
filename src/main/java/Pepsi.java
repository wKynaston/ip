import java.util.Scanner;

public class Pepsi {
    public static final String longline = "____________________________________________________________\n";
    public static boolean repeat(String input){
        if(input.toLowerCase().equals("bye")) {
            System.out.println(longline + "Bye. Hope to see you again soon!\n" + longline);
            return false;
        }
        else{
            System.out.println(longline + input + "\n" + longline);
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
