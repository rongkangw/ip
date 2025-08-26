package taskFeature;

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
