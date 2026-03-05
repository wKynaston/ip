package pepsi;

import actions.Task;
import exceptions.commandException;
import exceptions.commandParser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Pepsi {

    private static final Ui ui = new Ui();
    private static final TaskList tasks = new TaskList(Storage.load());

    public static void main(String[] args) {
        ui.showWelcome();
        manageList();
    }

    private static void manageList() {
        while (true) {
            String input = ui.readCommand();

            try {
                commandParser.validateNotEmpty(input);

                input = input.trim();
                String[] parts = input.split("\\s+", 2);
                String cmd = parts[0].toLowerCase();
                String rest = (parts.length == 2) ? parts[1].trim() : "";

                if (cmd.equals("bye")) {
                    ui.showGoodbye();
                    break;
                }

                if (cmd.equals("list")) {
                    ui.showTaskList(tasks.getTasks());
                    continue;
                }

                if (cmd.equals("mark") || cmd.equals("unmark")) {
                    int number = commandParser.parseTaskNumber(rest, cmd);
                    handleMarking(cmd, number);
                    continue;
                }

                if (cmd.equals("delete")) {
                    int number = commandParser.parseTaskNumber(rest, cmd);
                    handleDelete(number);
                    continue;
                }

                if (cmd.equals("todo") || cmd.equals("deadline") || cmd.equals("event")) {
                    Task task = commandParser.parseTask(cmd, rest);
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

    private static void addTask(Task task) {
        tasks.add(task);
        Storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }

    private static void handleMarking(String cmd, int number) throws commandException {
        if (cmd.equals("mark")) {
            Task t = tasks.mark(number);
            Storage.save(tasks.getTasks());
            ui.showTaskMarked(t);
        } else {
            Task t = tasks.unmark(number);
            Storage.save(tasks.getTasks());
            ui.showTaskUnmarked(t);
        }
    }

    private static void handleDelete(int number) throws commandException {
        Task removed = tasks.delete(number);
        Storage.save(tasks.getTasks());
        ui.showTaskDeleted(removed, tasks.size());
    }
}