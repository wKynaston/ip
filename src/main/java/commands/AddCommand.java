package commands;

import tasks.Task;
import exceptions.CommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Command that adds a new task to the task list and persists the change.
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Constructs an AddCommand with the task to be added.
     *
     * @param task the task to add
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the list, saves to storage, and notifies the UI.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler for persistence
     * @throws CommandException if saving fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }
}
