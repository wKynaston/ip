package tasks;
/**
 * Represents an abstract task with a description and completion status.
 * Subclasses should override {@link #typeIcon()} to return their specific type label,
 * and may override {@link #toString()} to append extra details.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Represents an abstract task with a description and completion status.
     * Subclasses should override {@link #typeIcon()} to return their specific type label,
     * and may override {@link #toString()} to append extra details.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Sets the completion status of this task.
     *
     * @param done {@code true} to mark as done, {@code false} to mark as not done
     */
    public void setDone(boolean done) {
        isDone = done;
    }
    /**
     * Returns the description of this task.
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Returns whether this task has been marked as done.
     *
     * @return {@code true} if done, {@code false} otherwise
     */
    public boolean isDone() {
        return isDone;
    }
    /**
     * Returns the status icon character for display.
     *
     * @return {@code "X"} if done, {@code " "} otherwise
     */
    protected String statusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the single-character type label for this task.
     * Subclasses should override this to return their specific type (e.g. "T", "D", "E").
     *
     * @return type icon string
     */
    protected String typeIcon() {
        return "?";
    }
    /**
     * Returns a formatted string representation of this task.
     *
     * @return task as {@code [type][status] description}
     */
    @Override
    public String toString() {
        return "[" + typeIcon() + "][" + statusIcon() + "] " + description;
    }
}
