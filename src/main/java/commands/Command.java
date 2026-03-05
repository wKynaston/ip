package commands;

import exceptions.CommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Abstract base class for all commands in the application.
 * Each command encapsulates a single user action and is executed against
 * the current {@link TaskList}, {@link Ui}, and {@link Storage}.
 */
public abstract class Command {

    /**
     * Executes this command.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler for persistence
     * @throws CommandException if the command cannot be executed successfully
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws CommandException;

    /**
     * Returns whether this command should terminate the application.
     * Defaults to {@code false}; only {@code ExitCommand} overrides this.
     *
     * @return {@code true} if the app should exit after this command
     */
    public boolean isExit() {
        return false;
    }
}