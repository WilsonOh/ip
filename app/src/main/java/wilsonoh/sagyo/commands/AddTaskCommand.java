package wilsonoh.sagyo.commands;

import wilsonoh.sagyo.tasklist.TaskList;
import wilsonoh.sagyo.tasks.Task;

/**
 * A command which adds a task into a task list
 *
 */
public class AddTaskCommand extends Command {

    private final TaskList taskList;
    private final Task toAdd;

    /**
     * Constructs a AddTaskCommand
     *
     * @param taskList the TaskList to be operated on
     * @param toAdd the Task to add to the TaskList
     */
    public AddTaskCommand(TaskList taskList, Task toAdd) {
        this.taskList = taskList;
        this.toAdd = toAdd;
    }

    @Override
    public String[] getCommandMessage() {
        return new String[] {String.format("added %s: %s", toAdd.getTaskType(), toAdd)};
    }

    /**
     * Add `toAdd` into `taskList`
     */
    @Override
    public void executeCommand() {
        taskList.addTask(toAdd);
    }
}
