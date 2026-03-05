package pepsi;

import commands.Command;
import exceptions.CommandException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Pepsi {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    public Pepsi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList loaded;
        try {
            loaded = new TaskList(storage.load());
        } catch (CommandException e) {
            ui.showLoadingError();
            loaded = new TaskList();
        }
        tasks = loaded;
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (CommandException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Pepsi("data/pepsi.txt").run();
    }
}