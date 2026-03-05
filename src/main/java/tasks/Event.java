package tasks;

/**
 * Represents a task that occurs over a specific time period, with a start and end time.
 */
public class Event extends Task {

    private final String from;
    private final String to;

    /**
     * Constructs an Event task with the given description, start time, and end time.
     *
     * @param description the text description of the event
     * @param from        the start time or date of the event
     * @param to          the end time or date of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return start time string
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return end time string
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns the type icon for an event task.
     *
     * @return {@code "E"}
     */
    @Override
    protected String typeIcon() {
        return "E";
    }

    /**
     * Returns a formatted string with the start and end times appended.
     *
     * @return task string with {@code (from: ... to ...)} suffix
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to " + to + ")";
    }
}