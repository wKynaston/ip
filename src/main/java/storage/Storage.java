package storage;

import tasks.*;
import exceptions.CommandException;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles reading and writing of tasks to a persistent flat-file storage.
 * Tasks are stored one per line in a pipe-delimited format.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage instance pointing to the given file path.
     *
     * @param filePath the path to the data file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file.
     * Creates the file and any necessary parent directories if they do not exist.
     * Lines that cannot be parsed are silently skipped.
     *
     * @return list of tasks loaded from disk
     * @throws CommandException if an I/O error occurs while reading the file
     */
    public ArrayList<Task> load() throws CommandException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            File parent = file.getParentFile();

            if (parent != null && !parent.exists()) {
                boolean folderCreated = parent.mkdirs();
                if (!folderCreated) {
                    System.out.println("Even Coke couldn't build this folder properly.");
                }
            }

            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                if (!fileCreated) {
                    System.out.println("Coke probably messed up creating the data file.");
                }
                return tasks;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            reader.close();

        } catch (IOException e) {
            throw new CommandException("Storage error. Must be Coke sabotaging the system.");
        }

        return tasks;
    }

    /**
     * Saves the given list of tasks to the data file, overwriting existing content.
     *
     * @param tasks the list of tasks to save
     */
    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task t : tasks) {
                writer.write(serialize(t));
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    /**
     * Parses a single line from the data file into a Task.
     * Returns {@code null} if the line is malformed or unrecognised.
     *
     * @param line the raw line string from the file
     * @return the parsed {@link Task}, or {@code null} if parsing fails
     */
    private Task parseLine(String line) {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String desc = parts[2];

            Task task = null;
            switch (type) {
            case "T":
                task = new Todo(desc);
                break;
            case "D":
                task = new Deadline(desc, parts[3]);
                break;
            case "E":
                task = new Event(desc, parts[3], parts[4]);
                break;
            }

            if (task != null) {
                task.setDone(isDone);
            }
            return task;

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Serialises a Task into a pipe-delimited string for storage.
     *
     * @param t the task to serialise
     * @return the formatted storage string, or an empty string if the type is unknown
     */
    private String serialize(Task t) {
        String status = t.isDone() ? "1" : "0";

        if (t instanceof Todo) {
            return "T | " + status + " | " + t.getDescription();
        }
        if (t instanceof Deadline d) {
            return "D | " + status + " | " + d.getDescription() + " | " + d.getBy();
        }
        if (t instanceof Event e) {
            return "E | " + status + " | " + e.getDescription()
                    + " | " + e.getFrom() + " | " + e.getTo();
        }
        return "";
    }
}