import java.time.LocalDateTime;

/**
 * Subclass of Task with additional completion time field.
 */
public class Deadline extends Task {
    private final LocalDateTime completeBy;

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
                this.completeBy.format(DATETIME_OUTPUT_FORMAT)
        );
    }
}
