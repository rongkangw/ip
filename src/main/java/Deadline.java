/**
 * Subclass of Task with additional completion time field.
 */
public class Deadline extends Task {
    private final String completeBy;

    public Deadline(String name, String completeBy) {
        super(name);
        this.completeBy = completeBy;
    }

    @Override
    public String showTask() {
        return String.format("[D]%s (by: %s)", super.showTask(), this.completeBy);
    }
}
