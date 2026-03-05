package tasklist;

import tasks.Task;
import exceptions.CommandException;

import java.util.ArrayList;

/**
 * Manages the in-memory list of tasks.
 * Provides operations to add, delete, mark, unmark, search, and retrieve tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList pre-populated with the given list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the given one-based index.
     *
     * @param oneBasedIndex the position of the task (starting from 1)
     * @return the removed task
     * @throws CommandException if the index is out of range
     */
    public Task delete(int oneBasedIndex) throws CommandException {
        int idx = oneBasedIndex - 1;
        validateIndex(idx);
        return tasks.remove(idx);
    }

    /**
     * Marks the task at the given one-based index as done.
     *
     * @param oneBasedIndex the position of the task (starting from 1)
     * @return the updated task
     * @throws CommandException if the index is out of range
     */
    public Task mark(int oneBasedIndex) throws CommandException {
        int idx = oneBasedIndex - 1;
        validateIndex(idx);
        Task t = tasks.get(idx);
        t.setDone(true);
        return t;
    }

    /**
     * Marks the task at the given one-based index as not done.
     *
     * @param oneBasedIndex the position of the task (starting from 1)
     * @return the updated task
     * @throws CommandException if the index is out of range
     */
    public Task unmark(int oneBasedIndex) throws CommandException {
        int idx = oneBasedIndex - 1;
        validateIndex(idx);
        Task t = tasks.get(idx);
        t.setDone(false);
        return t;
    }

    /**
     * Searches for tasks whose descriptions contain the given keyword (case-insensitive).
     *
     * @param keyword the search term
     * @return a list of matching tasks
     */
    public ArrayList<Task> search(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(t);
            }
        }
        return matches;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether the task list is empty.
     *
     * @return {@code true} if no tasks exist
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a defensive copy of the current task list.
     *
     * @return new {@code ArrayList} containing all current tasks
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Validates that a zero-based index is within the bounds of the task list.
     *
     * @param zeroBasedIdx the index to validate
     * @throws CommandException if the index is out of range
     */
    private void validateIndex(int zeroBasedIdx) throws CommandException {
        if (zeroBasedIdx < 0 || zeroBasedIdx >= tasks.size()) {
            throw new CommandException(
                    "Invalid task number. Even Coke could count better than that.");
        }
    }
}