public class Event extends Task{
    private final String start;
    private final String end;

    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String showTask() {
        return String.format("[E]%s (from: %s, to: %s)", super.showTask(), this.start, this.end);
    }
}
