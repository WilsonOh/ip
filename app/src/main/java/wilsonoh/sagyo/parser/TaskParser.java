package wilsonoh.sagyo.parser;

import java.util.regex.Matcher;

import wilsonoh.sagyo.commands.CommandType;
import wilsonoh.sagyo.exceptions.InvalidTaskException;
import wilsonoh.sagyo.tasks.DeadlineTask;
import wilsonoh.sagyo.tasks.EventTask;
import wilsonoh.sagyo.tasks.Task;
import wilsonoh.sagyo.tasks.TodoTask;

/**
 * A helper class which constructs the appropriate Task class
 * based on the task type and regex matcher given
 *
 */
public class TaskParser {

    /**
     * Returns the appropriate Task object based on the given `Matcher` and task type
     *
     * @param matcher the `Matcher` object which contains information about the task
     * @param taskType the type of the task to construct
     * @return the task of type `taskType`
     * @throws InvalidTaskException throws if an the matcher does not have the required groups
     */
    public static Task generateTaskFromMatcher(Matcher matcher, CommandType taskType) throws InvalidTaskException {
        Task task;
        String taskName = matcher.group("name");
        if (taskName == null) {
            throw new InvalidTaskException("The description of a task cannot be empty.");
        }
        switch (taskType) {
        case DEADLINE:
            String by = matcher.group("by");
            if (by == null) {
                throw new InvalidTaskException("Invalid syntax. deadline command must contain the /by keyword");
            }
            task = new DeadlineTask(taskName, matcher.group("by"));
            break;
        case EVENT:
            String from = matcher.group("from");
            String to = matcher.group("to");
            if (from == null || to == null) {
                throw new InvalidTaskException("Invalid syntax. event command must contain the /from and /to keywords");
            }
            task = new EventTask(taskName, matcher.group("from"), matcher.group("to"));
            break;
        case TODO:
            task = new TodoTask(taskName);
            break;
        default:
            throw new IllegalArgumentException("Should not reach here as the CommandType must be a Task type.");
        }
        return task;
    }
}
