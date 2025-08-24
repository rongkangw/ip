import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Acts as storage manager for managing task history of the TaskManager on the disk.
 * Allows for reading and writing only.
 */
public class TaskHistoryManager {
    private static final String PATH_DIR = "./src/data/TaskHistory.txt";
    private final File file;

    private Task getTaskFromLine(String line) throws InvalidFormatException {
        //1. Initial check for all lines to have commas
        if (!line.contains(",")) {
            throw new InvalidFormatException("File has incorrect format: \",\" missing");
        }

        String[] taskInfo = line.split(",");

        //2. Check that lines have at least 3 params and isDone is of valid format (0 or 1)
        if (taskInfo.length < 3 || !taskInfo[1].matches("^[01]$")) {
            throw new InvalidFormatException("File has incorrect format: Wrong number of task params");
        }

        String type = taskInfo[0];
        boolean isDone = taskInfo[1].equals("1");
        String name = taskInfo[2];

        String[] additionalParams = Arrays.copyOfRange(taskInfo, 3, taskInfo.length);

        //3. Check that task type is of valid format (T, D, E) and has correct num of additional params
        return switch (type) {
            case "T" -> {
                if (additionalParams.length > 0) {
                    throw new InvalidFormatException("File has incorrect format: Wrong additional params");
                }
                yield new ToDo(name, isDone);
            }
            case "D" -> {
                if (additionalParams.length < 1) {
                    throw new InvalidFormatException("File has incorrect format: Wrong additional params");
                }
                yield new Deadline(name, isDone, additionalParams[0]);
            }
            case "E" -> {
                if (additionalParams.length < 2) {
                    throw new InvalidFormatException("File has incorrect format: Wrong additional params");
                }
                yield new Event(name, isDone, additionalParams[0], additionalParams[1]);
            }
            default -> throw new InvalidFormatException("File has incorrect format: Wrong task type");
        };
    }

    public TaskHistoryManager() {
        this.file = new File(PATH_DIR);
    }

    /**
     * Retrieve task history from file specified in PATH_DIR.
     * If file not found or invalid format, creates new file instead.
     *
     * @param taskList The taskList provided by the TaskManager for inserting retrieved history
     * @return true if task history exists, false if task history file not found or invalid.
     * @throws Error If file unable to be created at PATH_DIR
     */
    public boolean retrieveTaskHistory(ArrayList<Task> taskList) {
        try {
            taskList.clear();
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                Task task = getTaskFromLine(s.nextLine());
                taskList.add(task);
            }

            return true;

        } catch (FileNotFoundException | InvalidFormatException e) {
            try {
                //creates a new history file if it does not exist // is invalid format
                //result added to suppress return
                boolean result = file.createNewFile();

                return false;

            } catch (IOException ioe) {
                throw new Error("File could not be created");
            }
        }
    }

    /**
     * Updates task history in file specified in PATH_DIR
     *
     * @param history The taskList provided by the TaskManager for updating history
     * @throws Error If unable to write to file
     */
    public void updateHistory(ArrayList<Task> history) {
        try (FileWriter fw = new FileWriter(file)) {
            for (Task task: history) {
                fw.write(task.saveTask() + "\n");
            }

        } catch (IOException e) {
            throw new Error("Unable to write to File");
        }
    }
}
