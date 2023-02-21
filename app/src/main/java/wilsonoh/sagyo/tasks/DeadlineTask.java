package wilsonoh.sagyo.tasks;

import wilsonoh.sagyo.commands.CommandType;

/**
 * Represents a task that has to be done by a certain deadline
 *
 */
public class DeadlineTask extends Task {

    private String by;

    /**
     * Constructs a DeadlineTask object
     *
     * @param taskName the description of the task
     * @param by the deadline by which the task has to be done
     */
    public DeadlineTask(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return CommandType.DEADLINE.name().toLowerCase();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
