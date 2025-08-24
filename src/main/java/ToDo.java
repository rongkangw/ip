/**
 * Subclass of Task for clarity in naming.
 * Does not have additional functionality on top of the identifier when displaying task.
 */
public class ToDo extends Task {
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String showTask() {
        return String.format("[T]%s", super.showTask());
    }

    @Override
    public String saveTask() {
        return String.format("%s,%s", "T", super.saveTask());
    }
}
