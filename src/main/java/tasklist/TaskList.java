package tasklist;

import actions.Task;
import exceptions.commandException;

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

    public Task delete(int oneBasedIndex) throws commandException {
        int idx = oneBasedIndex - 1;
        validateIndex(idx);
        return tasks.remove(idx);
    }

    public Task mark(int oneBasedIndex) throws commandException {
        int idx = oneBasedIndex - 1;
        validateIndex(idx);
        Task t = tasks.get(idx);
        t.setDone(true);
        return t;
    }

    public Task unmark(int oneBasedIndex) throws commandException {
        int idx = oneBasedIndex - 1;
        validateIndex(idx);
        Task t = tasks.get(idx);
        t.setDone(false);
        return t;
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

    private void validateIndex(int zeroBasedIdx) throws commandException {
        if (zeroBasedIdx < 0 || zeroBasedIdx >= tasks.size()) {
            throw new commandException(
                    "Invalid task number. Even Coke could count better than that.");
        }
    }
}