package actions;

import java.util.ArrayList;

public class Delete {

    public static void delete(ArrayList<Task> catalogue,
                                 int position,
                                 String line) {

        int idx = position - 1;

        if (idx < 0 || idx >= catalogue.size()) {
            System.out.println(line
                    + "Invalid task number. Even Coke could count better than that.\n"
                    + line);
        }

        Task removed = catalogue.remove(idx);

        System.out.println(line
                + "Boom. Deleted faster than Coke deleting bad reviews.\n"
                + "  " + removed + "\n"
                + "Now you only have " + catalogue.size()
                + " task(s) left — still more organised than Coke’s marketing team.\n"
                + line);

    }
}