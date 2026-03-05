package tasks;

/**
 * Represents a basic to-do task with no date or time constraints.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the given description.
     *
     * @param description the text description of the to-do item
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type icon for a to-do task.
     *
     * @return {@code "T"}
     */
    @Override
    protected String typeIcon() {
        return "T";
    }
}