package feature;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a name and an editable completion flag of isDone.
 * Additional datetime information can be saved using DATETIME_OUTPUT_FORMAT and DATETIME_INPUT_FORMAT.
 */
public class Task {
    public static final DateTimeFormatter DATETIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
    public static final DateTimeFormatter DATETIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    private final String name;
    private boolean isDone;

    /**
     * Initialises a Task with a name and isDone flag.
     *
     * @param name Name of the task
     * @param isDone Boolean for if the task is done
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks task as done.
     *
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Unmarks task as done.
     *
     */
    public void unmarkDone() {
        isDone = false;
    }

    /**
     * Checks if the task name is similar to the search name provided, using String.contains()
     *
     * @return true if task name contains the search name, else false
     */
    public boolean isSimilarTaskName(String searchName) {
        return name.contains(searchName);
    }

    /**
     * Formats task information for displaying.
     *
     * @return Task information as a formatted String
     */
    public String showTask() {
        String doneIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", doneIcon, name);
    }

    /**
     * Formats task information for saving in history.
     *
     * @return Task information as a formatted String
     */
    public String saveTask() {
        int doneFlag = isDone ? 1 : 0;
        return String.format("%d,%s", doneFlag, name);
    }
}
