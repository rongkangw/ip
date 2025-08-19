/**
 * Represents a task with a name and a editable completion flag of isDone.
 */
public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
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

    public String showTask() {
        String doneIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", doneIcon, name);
    }
}
