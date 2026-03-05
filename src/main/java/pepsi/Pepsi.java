package pepsi;

import actions.Task;
import exceptions.commandException;
import parser.Parser;
import parser.Parser.ParsedCommand;
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
        } catch (commandException e) {
            ui.showLoadingError();
            loaded = new TaskList();
        }
        tasks = loaded;
    }

    public void run() {
        ui.showWelcome();
        manageList();
    }

    private void manageList() {
        while (true) {
            try {
                String input = ui.readCommand();
                ParsedCommand parsed = Parser.parse(input);
                String cmd = parsed.cmd;
                String rest = parsed.rest;

                if (cmd.equals("bye")) {
                    ui.showGoodbye();
                    break;
                }

                if (cmd.equals("list")) {
                    ui.showTaskList(tasks.getTasks());
                    continue;
                }

                if (cmd.equals("mark") || cmd.equals("unmark")) {
                    int number = Parser.parseTaskNumber(rest, cmd);
                    handleMarking(cmd, number);
                    continue;
                }

                if (cmd.equals("delete")) {
                    int number = Parser.parseTaskNumber(rest, cmd);
                    handleDelete(number);
                    continue;
                }

                if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
                    Task task = Parser.parseTask(cmd, rest);
                    addTask(task);
                    continue;
                }

                throw new commandException(
                        "Unknown command. Must be something Coke invented.\n"
                                + "Try: todo / deadline / event / list / mark / unmark / delete / bye"
                );

            } catch (commandException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private void addTask(Task task) {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }

    private void handleMarking(String cmd, int number) throws commandException {
        if (cmd.equals("mark")) {
            Task t = tasks.mark(number);
            storage.save(tasks.getTasks());
            ui.showTaskMarked(t);
        } else {
            Task t = tasks.unmark(number);
            storage.save(tasks.getTasks());
            ui.showTaskUnmarked(t);
        }
    }

    private void handleDelete(int number) throws commandException {
        Task removed = tasks.delete(number);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(removed, tasks.size());
    }

    public static void main(String[] args) {
        new Pepsi("data/pepsi.txt").run();
    }
}