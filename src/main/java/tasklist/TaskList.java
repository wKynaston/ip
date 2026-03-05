package tasklist;

import tasks.Task;
import exceptions.CommandException;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int oneBasedIndex) throws CommandException {
        int idx = oneBasedIndex - 1;
        validateIndex(idx);
        return tasks.remove(idx);
    }

    public Task mark(int oneBasedIndex) throws CommandException {
        int idx = oneBasedIndex - 1;
        validateIndex(idx);
        Task t = tasks.get(idx);
        t.setDone(true);
        return t;
    }

    public Task unmark(int oneBasedIndex) throws CommandException {
        int idx = oneBasedIndex - 1;
        validateIndex(idx);
        Task t = tasks.get(idx);
        t.setDone(false);
        return t;
    }

    public ArrayList<Task> search(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(t);
            }
        }
        return matches;
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    private void validateIndex(int zeroBasedIdx) throws CommandException {
        if (zeroBasedIdx < 0 || zeroBasedIdx >= tasks.size()) {
            throw new CommandException(
                    "Invalid task number. Even Coke could count better than that.");
        }
    }
}