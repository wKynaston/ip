package commands;

import exceptions.CommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        ui.showMatchingTasks(tasks.search(keyword));
    }
}
