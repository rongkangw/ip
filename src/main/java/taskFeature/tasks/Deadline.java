package taskFeature.tasks;

import java.time.LocalDateTime;

import taskFeature.Task;

/**
 * Subclass of taskFeature.Task with additional completion time field.
 */
public class Deadline extends Task {
    private final LocalDateTime completeBy;

    /**
     * Initialises a Deadline task with a name, isDone flag and completeBy datetime.
     *
     * @param name Name of the deadline task
     * @param isDone Boolean for if the Deadline task is done
     * @param completeBy Deadline as a LocalDateTime object
     */
    public Deadline(String name, boolean isDone, LocalDateTime completeBy) {
        super(name, isDone);
        this.completeBy = completeBy;
    }

    @Override
    public String showTask() {
        return String.format("[D]%s (by: %s)",
                super.showTask(),
                this.completeBy.format(DATETIME_OUTPUT_FORMAT)
        );
    }

    @Override
    public String saveTask() {
        return String.format(
                "%s,%s,%s", "D",
                super.saveTask(),
                this.completeBy.format(DATETIME_INPUT_FORMAT)
        );
    }
}
