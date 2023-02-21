package wilsonoh.sagyo.commands;

import java.util.ArrayList;
import java.util.List;

import wilsonoh.sagyo.tasklist.TaskList;

/**
 * A command which displays the list of tasks which
 * matches a certain keyword
 *
 */
public class FindCommand extends Command {

    private final String filter;
    private final TaskList tasks;

    /**
     * Constructs a FindCommand object
     *
     * @param filter the keyword to be matched against
     * @param tasks the TaskList object to be operated on
     */
    public FindCommand(String filter, TaskList tasks) {
        this.filter = filter;
        this.tasks = tasks;
    }

    @Override
    public String[] getCommandMessage() {
        ArrayList<String> ret = new ArrayList<>(List.of(tasks.getFilteredTasksString(this.filter)));
        ret.add(0, String.format("Here are the tasks matching %s:", this.filter));
        return ret.toArray(String[] ::new);
    }

    @Override
    public void executeCommand() {
    }
}
