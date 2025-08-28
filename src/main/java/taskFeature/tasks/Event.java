package taskFeature.tasks;

import taskFeature.Task;

import java.time.LocalDateTime;

/**
 * Subclass of taskFeature.Task with additional start and end date/time field.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Initialises an Event task with a name, isDone flag, start datetime and end datetime.
     *
     * @param name Name of the event
     * @param isDone Boolean for if the Event task is done
     * @param start Starting datetime as a LocalDateTime object
     * @param end Ending datetime as a LocalDateTime object
     */
    public Event(String name, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(name, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String showTask() {
        return String.format(
                "[E]%s (from: %s, to: %s)",
                super.showTask(),
                start.format(DATETIME_OUTPUT_FORMAT),
                end.format(DATETIME_OUTPUT_FORMAT)
        );
    }

    @Override
    public String saveTask() {
        return String.format(
                "%s,%s,%s,%s", "E",
                super.saveTask(),
                start.format(DATETIME_INPUT_FORMAT),
                end.format(DATETIME_INPUT_FORMAT)
        );
    }
}
