/**
 * Subclass of Task with additional completion time field.
 */
public class Deadline extends Task {
    private final String completeBy;

    public Deadline(String name, boolean isDone, String completeBy) {
        super(name, isDone);
        this.completeBy = completeBy;
    }

    @Override
    public String showTask() {
        return String.format("[D]%s (by: %s)", super.showTask(), this.completeBy);
    }

    @Override
    public String saveTask() {
        return String.format("%s,%s,%s", "D", super.saveTask(), completeBy);
    }
}
