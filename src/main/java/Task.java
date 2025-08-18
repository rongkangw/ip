public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    public String showTask() {
        String doneIcon = isDone ? "X" : " ";
        return String.format("[%s] %s", doneIcon, name);
    }
}
