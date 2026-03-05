package parser;

import commands.*;
import exceptions.CommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Parses raw user input strings into executable {@link Command} objects.
 * Also validates input format and task field structure before constructing tasks.
 */
public class Parser {

    /**
     * Parses a full user input string and returns the corresponding command.
     *
     * @param fullCommand the raw input string from the user
     * @return the parsed {@link Command}
     * @throws CommandException if the input is empty, malformed, or unrecognised
     */
    public static Command parse(String fullCommand) throws CommandException {
        validateNotEmpty(fullCommand);

        String trimmed = fullCommand.trim();
        String[] parts = trimmed.split("\\s+", 2);
        String cmd = parts[0].toLowerCase();
        String rest = (parts.length == 2) ? parts[1].trim() : "";

        switch (cmd) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseTaskNumber(rest, cmd), true);
        case "unmark":
            return new MarkCommand(parseTaskNumber(rest, cmd), false);
        case "delete":
            return new DeleteCommand(parseTaskNumber(rest, cmd));
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(parseTask(cmd, rest));
        case "find":
            if (rest.isEmpty()) {
                throw new CommandException(
                        "Find what exactly? Even Coke knows you need a search term.\n"
                                + "Use: find <keyword>");
            }
            return new FindCommand(rest);
        default:
            throw new CommandException(
                    "Unknown command. Must be something Coke invented.\n"
                            + "Try: todo / deadline / event / list / mark / unmark / delete / find / bye"
            );
        }
    }

    /**
     * Validates that the input string is not null or blank.
     *
     * @param input the input to validate
     * @throws CommandException if the input is null or empty
     */
    public static void validateNotEmpty(String input) throws CommandException {
        if (input == null || input.trim().isEmpty()) {
            throw new CommandException("Typing nothing? That's very Coke behaviour.");
        }
    }

    /**
     * Parses a task number from the argument string of a command.
     *
     * @param rest the argument portion of the user input
     * @param cmd  the command name, used in error messages
     * @return the parsed one-based task number
     * @throws CommandException if the argument is missing or not a valid integer
     */
    public static int parseTaskNumber(String rest, String cmd) throws CommandException {
        if (rest == null || rest.trim().isEmpty()) {
            throw new CommandException("What? Coke-level instructions detected.\n"
                    + "Use: " + cmd + " <task number>");
        }

        String s = rest.trim();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') {
                throw new CommandException("That's not a number. Coke maths strikes again.");
            }
        }

        return Integer.parseInt(s);
    }

    /**
     * Dispatches task parsing to the appropriate method based on command type.
     *
     * @param cmd  the task type command ({@code "todo"}, {@code "deadline"}, or {@code "event"})
     * @param rest the remaining input after the command keyword
     * @return the constructed {@link Task}
     * @throws CommandException if the task arguments are invalid
     */
    public static Task parseTask(String cmd, String rest) throws CommandException {
        if (cmd.equals("todo")) {
            return parseTodo(rest);
        }
        if (cmd.equals("deadline")) {
            return parseDeadline(rest);
        }
        if (cmd.equals("event")) {
            return parseEvent(rest);
        }

        throw new CommandException("Unknown command. Must be something Coke invented.\n"
                + "Try: todo / deadline / event / list / mark / unmark / delete / bye");
    }

    private static Task parseTodo(String rest) throws CommandException {
        if (rest == null || rest.trim().isEmpty()) {
            throw new CommandException("A todo with no description? Classic Coke quality.\n"
                    + "Use: todo <description>");
        }
        return new Todo(rest.trim());
    }

    private static Task parseDeadline(String rest) throws CommandException {
        if (rest == null) rest = "";

        String[] split = rest.split("\\s+/by\\s+", 2);
        if (split.length != 2 || split[0].trim().isEmpty() || split[1].trim().isEmpty()) {
            throw new CommandException("That deadline format is as vague as Coke's ingredients.\n"
                    + "Use: deadline <description> /by <when>");
        }

        return new Deadline(split[0].trim(), split[1].trim());
    }

    private static Task parseEvent(String rest) throws CommandException {
        if (rest == null) rest = "";

        String[] splitFrom = rest.split("\\s+/from\\s+", 2);
        if (splitFrom.length != 2 || splitFrom[0].trim().isEmpty()) {
            throw new CommandException("An event without timing? That's very Coke-planned.\n"
                    + "Use: event <description> /from <start> /to <end>");
        }

        String desc = splitFrom[0].trim();
        String[] splitTo = splitFrom[1].split("\\s+/to\\s+", 2);

        if (splitTo.length != 2 || splitTo[0].trim().isEmpty() || splitTo[1].trim().isEmpty()) {
            throw new CommandException("Start time but no end time? Coke scheduling again.\n"
                    + "Use: event <description> /from <start> /to <end>");
        }

        return new Event(desc, splitTo[0].trim(), splitTo[1].trim());
    }
}