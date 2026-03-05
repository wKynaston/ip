package tasks;

import exceptions.CommandException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that must be completed by a specific date.
 * The date must be provided in {@code yyyy-MM-dd} format.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    private final LocalDate by;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description the text description of the task
     * @param by          the due date string in {@code yyyy-MM-dd} format
     * @throws CommandException if the date string is not in the expected format
     */
    public Deadline(String description, String by) throws CommandException {
        super(description);
        this.by = parseDate(by);
    }

    /**
     * Parses a date string into a {@link LocalDate}.
     *
     * @param dateStr the date string to parse
     * @return the parsed {@code LocalDate}
     * @throws CommandException if the format is invalid
     */
    private static LocalDate parseDate(String dateStr) throws CommandException {
        try {
            return LocalDate.parse(dateStr.trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new CommandException(
                    "Invalid date format. Use yyyy-MM-dd e.g. 2019-10-15");
        }
    }

    /**
     * Returns the due date as a string in {@code yyyy-MM-dd} format, used for storage serialisation.
     *
     * @return due date string
     */
    public String getBy() {
        return by.format(INPUT_FORMAT);
    }

    /**
     * Returns the type icon for a deadline task.
     *
     * @return {@code "D"}
     */
    @Override
    protected String typeIcon() {
        return "D";
    }

    /**
     * Returns a formatted string with the due date appended.
     *
     * @return task string with {@code (by: MMM d yyyy)} suffix
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
