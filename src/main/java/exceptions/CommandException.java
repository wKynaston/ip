package exceptions;

/**
 * Represents a checked exception thrown when a user command is invalid or cannot be executed.
 * Used throughout the application to signal input errors in a controlled, recoverable way.
 */
public class CommandException extends Exception {

    /**
     * Constructs a CommandException with the given error message.
     *
     * @param message the error message to display to the user
     */
    public CommandException(String message) {
        super(message);
    }
}
