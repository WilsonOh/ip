package wilsonoh.sagyo.commands;

import wilsonoh.sagyo.tasklist.TaskList;

/**
 * A command which lists all the tasks in the task list
 * as a formatted string
 *
 */
public class ListCommand extends Command {

    private final TaskList tasks;

    /**
     * Constructs a ListCommand object
     *
     * @param taskList the TaskList to be operated on
     */
    public ListCommand(TaskList taskList) {
        this.tasks = taskList;
    }

    @Override
    public String[] getCommandMessage() {
        return tasks.isEmpty() ? new String[] {"Task list is currently empty.", "Try adding some tasks!"}
                               : tasks.getTasksString();
    }

    @Override
    public void executeCommand() {
    }
}
