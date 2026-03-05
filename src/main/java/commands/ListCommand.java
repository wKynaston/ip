package commands;

import exceptions.CommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        ui.showTaskList(tasks.getTasks());
    }
}
