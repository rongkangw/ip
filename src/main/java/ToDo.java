public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String showTask() {
        return String.format("[T]%s", super.showTask());
    }
}
