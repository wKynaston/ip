package commands;

import tasks.Task;
import exceptions.CommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {

    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        Task removed = tasks.delete(taskNumber);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(removed, tasks.size());
    }
}
