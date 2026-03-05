package pepsi;

import commands.Command;
import exceptions.CommandException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Entry point and main controller for the Pepsi task manager application.
 * Initialises all core components and runs the main input loop.
 */
public class Pepsi {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Constructs a Pepsi instance, loading saved tasks from the given file path.
     * If loading fails, starts with an empty task list.
     *
     * @param filePath the path to the persistent data file
     */
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

    /**
     * Starts the main application loop.
     * Reads user input, parses it into commands, and executes them until an exit command is given.
     */
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

    /**
     * Application entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        new Pepsi("data/pepsi.txt").run();
    }
}