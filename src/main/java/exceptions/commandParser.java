package exceptions;

import actions.Deadline;
import actions.Event;
import actions.Task;
import actions.Todo;


public class commandParser {

    public static void validateNotEmpty(String input) throws commandException {
        if (input == null || input.trim().isEmpty()) {
            throw new commandException("Typing nothing? That’s very Coke behaviour.");
        }
    }

    public static int parseTaskNumber(String rest, String cmd) throws commandException {
        if (rest == null || rest.trim().isEmpty()) {
            throw new commandException("Mark what? Coke-level instructions detected.\n"
                    + "Use: " + cmd + " <task number>");
        }

        String s = rest.trim();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') {
                throw new commandException("That’s not a number. Coke maths strikes again.");
            }
        }

        return Integer.parseInt(s);
    }

    public static Task parseTask(String cmd, String rest) throws commandException {
        if (cmd.equals("todo")) {
            return parseTodo(rest);
        }
        if (cmd.equals("deadline")) {
            return parseDeadline(rest);
        }
        if (cmd.equals("event")) {
            return parseEvent(rest);
        }

        throw new commandException("Unknown command. Must be something Coke invented.\n"
                + "Try: todo / deadline / event / list / mark / unmark / bye");
    }

    private static Task parseTodo(String rest) throws commandException {
        if (rest == null || rest.trim().isEmpty()) {
            throw new commandException("A todo with no description? Classic Coke quality.\n"
                    + "Use: todo <description>");
        }
        return new Todo(rest.trim());
    }

    private static Task parseDeadline(String rest) throws commandException {
        if (rest == null) rest = "";

        String[] split = rest.split("\\s+/by\\s+", 2);
        if (split.length != 2 || split[0].trim().isEmpty() || split[1].trim().isEmpty()) {
            throw new commandException("That deadline format is as vague as Coke’s ingredients.\n"
                    + "Use: deadline <description> /by <when>");
        }

        return new Deadline(split[0].trim(), split[1].trim());
    }

    private static Task parseEvent(String rest) throws commandException {
        if (rest == null) rest = "";

        String[] splitFrom = rest.split("\\s+/from\\s+", 2);
        if (splitFrom.length != 2 || splitFrom[0].trim().isEmpty()) {
            throw new commandException("An event without timing? That’s very Coke-planned.\n"
                    + "Use: event <description> /from <start> /to <end>");
        }

        String desc = splitFrom[0].trim();
        String[] splitTo = splitFrom[1].split("\\s+/to\\s+", 2);

        if (splitTo.length != 2 || splitTo[0].trim().isEmpty() || splitTo[1].trim().isEmpty()) {
            throw new commandException("Start time but no end time? Coke scheduling again.\n"
                    + "Use: event <description> /from <start> /to <end>");
        }

        return new Event(desc, splitTo[0].trim(), splitTo[1].trim());
    }
}
