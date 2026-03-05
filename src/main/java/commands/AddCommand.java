package commands;

import tasks.Task;
import exceptions.CommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }
}
