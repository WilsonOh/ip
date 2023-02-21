package wilsonoh.sagyo.tasks;

import wilsonoh.sagyo.commands.CommandType;

/**
 * Represents a basic task which contains
 * only a task description
 *
 */
public class TodoTask extends Task {

    /**
     * Constructs a TodoTask object
     *
     * @param taskName the description of the task
     */
    public TodoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String getTaskType() {
        return CommandType.TODO.name().toLowerCase();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
