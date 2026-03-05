package commands;

import exceptions.CommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws CommandException;

    public boolean isExit() {
        return false;
    }
}