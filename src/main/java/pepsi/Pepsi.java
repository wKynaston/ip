package pepsi;

import actions.Task;
import exceptions.commandException;
import exceptions.commandParser;
import storage.Storage;
import ui.Ui;

import java.util.ArrayList;

public class Pepsi {

    // Load tasks when program starts
    private static final ArrayList<Task> catalogue = Storage.load();
    private static final Ui ui = new Ui();

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
                    ui.showTaskList(catalogue);
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
        catalogue.add(task);
        Storage.save(catalogue);
        ui.showTaskAdded(task, catalogue.size());
    }

    private static void handleMarking(String cmd, int number) throws commandException {
        int idx = number - 1;

        if (idx < 0 || idx >= catalogue.size()) {
            throw new commandException("Invalid task number. Even Coke could count better than that.");
        }

        Task t = catalogue.get(idx);

        if (cmd.equals("mark")) {
            t.setDone(true);
            ui.showTaskMarked(t);
        } else {
            t.setDone(false);
            ui.showTaskUnmarked(t);
        }

        Storage.save(catalogue);
    }

    private static void handleDelete(int number) throws commandException {
        int idx = number - 1;

        if (idx < 0 || idx >= catalogue.size()) {
            throw new commandException("Invalid task number. Coke-level counting detected.");
        }

        Task removed = catalogue.remove(idx);
        Storage.save(catalogue);
        ui.showTaskDeleted(removed, catalogue.size());
    }
}