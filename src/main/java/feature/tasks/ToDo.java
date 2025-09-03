package feature.tasks;

import feature.Task;

/**
 * Subclass of taskFeature.Task for clarity in naming.
 * Does not have additional functionality on top of the identifier when displaying task.
 */
public class ToDo extends Task {
    /**
     * Initialises a ToDo task with a name and isDone flag.
     *
     * @param name Name of the ToDo task
     * @param isDone Boolean for if the ToDo task is done
     */
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
