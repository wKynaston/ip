package commands;

import tasks.Task;
import exceptions.CommandException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class MarkCommand extends Command {

    private final int taskNumber;
    private final boolean markAsDone;

    public MarkCommand(int taskNumber, boolean markAsDone) {
        this.taskNumber = taskNumber;
        this.markAsDone = markAsDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CommandException {
        if (markAsDone) {
            Task t = tasks.mark(taskNumber);
            storage.save(tasks.getTasks());
            ui.showTaskMarked(t);
        } else {
            Task t = tasks.unmark(taskNumber);
            storage.save(tasks.getTasks());
            ui.showTaskUnmarked(t);
        }
    }
}
