package wilsonoh.sagyo.tasks;

import wilsonoh.sagyo.commands.CommandType;

/**
 * Represents a task which spans a duration
 *
 */
public class EventTask extends Task {

    private final String from;
    private final String to;

    /**
     * Constructs a EventTask object
     *
     * @param taskName the description of the task
     * @param from the starting time of the task
     * @param to the ending time of the task
     */
    public EventTask(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTaskType() {
        return CommandType.EVENT.name().toLowerCase();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
