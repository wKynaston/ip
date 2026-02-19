package storage;

import actions.*;
import java.io.*;
import java.util.ArrayList;
import actions.Task;

public class Storage {

    private static final String FILE_PATH = "data/pepsi.txt";

    public static ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            File parent = file.getParentFile();

            // Create folder if it doesn't exist
            if (parent != null && !parent.exists()) {
                boolean folderCreated = parent.mkdirs();
                if (!folderCreated) {
                    System.out.println("Even Coke couldn't build this folder properly.");
                }
            }

            // Create file if it doesn't exist
            if (!file.exists()) {
                boolean fileCreated = file.createNewFile();
                if (!fileCreated) {
                    System.out.println("Coke probably messed up creating the data file.");
                }
                return tasks; // first run → empty list
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
            System.out.println("Storage error. Must be Coke sabotaging the system.");
        }

        return tasks;
    }

    public static void save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task t : tasks) {
                writer.write(serialize(t));
                writer.write(System.lineSeparator());
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    // Convert file line → Task
    private static Task parseLine(String line) {
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
            // corrupted line → ignore
            return null;
        }
    }

    // Convert Task → file line
    private static String serialize(Task t) {
        String status = t.isDone() ? "1" : "0";

        if (t instanceof Todo) {
            return "T | " + status + " | " + t.getDescription();
        }
        if (t instanceof Deadline d) {
            return "D | " + status + " | "
                    + d.getDescription() + " | "
                    + d.getBy();
        }
        if (t instanceof Event e) {
            return "E | " + status + " | "
                    + e.getDescription() + " | "
                    + e.getFrom() + " | "
                    + e.getTo();
        }

        return "";
    }
}
