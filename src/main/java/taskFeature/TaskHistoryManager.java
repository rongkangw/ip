package taskFeature;

import exceptions.InvalidFormatException;
import taskFeature.tasks.Deadline;
import taskFeature.tasks.Event;
import taskFeature.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Acts as storage manager for managing task history of the TaskManager on the disk.
 * Allows for reading and writing only.
 */
public class TaskHistoryManager {
    private final File file;

    public TaskHistoryManager(String pathDir) {
        this.file = new File(pathDir);
    }

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

                //check that valid datetime format is provided
                try {
                    LocalDateTime parsed = LocalDateTime.parse(additionalParams[0], Task.DATETIME_INPUT_FORMAT);
                    yield new Deadline(name, isDone, parsed);
                } catch (DateTimeParseException e) {
                    throw new InvalidFormatException("File has incorrect format: Wrong datetime format");
                }
            }
            case "E" -> {
                if (additionalParams.length < 2) {
                    throw new InvalidFormatException("File has incorrect format: Wrong additional params");
                }

                //check that valid datetime format is provided
                try {
                    LocalDateTime parsedFrom = LocalDateTime.parse(additionalParams[0], Task.DATETIME_INPUT_FORMAT);
                    LocalDateTime parsedTo = LocalDateTime.parse(additionalParams[1], Task.DATETIME_INPUT_FORMAT);
                    yield new Event(name, isDone, parsedFrom, parsedTo);
                } catch (DateTimeParseException e) {
                    throw new InvalidFormatException("File has incorrect format: Wrong datetime format");
                }
            }
            default -> throw new InvalidFormatException("File has incorrect format: Wrong task type");
        };
    }

    /**
     * Retrieve task history from file specified in pathDir.
     * If file not found or invalid format, creates new file instead.
     *
     * @param taskList The taskList provided by the taskFeature.TaskManager for inserting retrieved history
     * @return true if task history exists, false if task history file not found, invalid, or empty.
     * @throws Error If file unable to be created at pathDir
     */
    public boolean retrieveTaskHistory(ArrayList<Task> taskList) {

        try (Scanner s = new Scanner(file)) {
            taskList.clear();

            while (s.hasNextLine()) {
                Task task = getTaskFromLine(s.nextLine());
                taskList.add(task);
            }

            //flag as no history found (false) if no tasks but file exists
            //else flag as true
            return !taskList.isEmpty();

        } catch (FileNotFoundException e) {
            try {
                //create directories for history file if not exist
                Files.createDirectories(Path.of(file.getPath()));

                //create new history file
                boolean result2 = file.createNewFile(); //result added to suppress return


                return false;

            } catch (IOException ioe) {
                throw new Error(ioe.getMessage());
            }
        } catch (InvalidFormatException e) {
            try {
                //delete file if is invalid format
                if (file.exists()) {
                    boolean result1 = file.delete(); //result added to suppress return
                }

                //create new history file
                boolean result2 = file.createNewFile(); //result added to suppress return

                return false;

            } catch (IOException ioe) {
                throw new Error(ioe.getMessage());
            }

        }
    }

    /**
     * Updates task history in file specified in pathDir
     *
     * @param history The taskList provided by the taskFeature.TaskManager for updating history
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
