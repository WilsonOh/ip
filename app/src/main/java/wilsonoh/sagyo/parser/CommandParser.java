package wilsonoh.sagyo.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wilsonoh.sagyo.commands.AddTaskCommand;
import wilsonoh.sagyo.commands.ByeCommand;
import wilsonoh.sagyo.commands.Command;
import wilsonoh.sagyo.commands.CommandType;
import wilsonoh.sagyo.commands.DeleteTaskCommand;
import wilsonoh.sagyo.commands.FindCommand;
import wilsonoh.sagyo.commands.ListCommand;
import wilsonoh.sagyo.commands.MarkTaskCommand;
import wilsonoh.sagyo.commands.UnMarkTaskCommand;
import wilsonoh.sagyo.exceptions.InvalidCommandException;
import wilsonoh.sagyo.exceptions.InvalidTaskException;
import wilsonoh.sagyo.tasklist.TaskList;
import wilsonoh.sagyo.tasks.Task;

public class CommandParser {

    private final TaskList tasks;

    /**
     * Constructs a CommandParser object
     *
     * @param tasks the TaskList to be used for the commands
     */
    public CommandParser(TaskList tasks) {
        this.tasks = tasks;
    }

    private int getValidatedIndex(String commandName, String idxGroup) throws InvalidCommandException {
        if (idxGroup == null) {
            throw new InvalidCommandException(
                String.format("The %s command must be followed by the index of a task", commandName));
        }
        int index = Integer.parseInt(idxGroup) - 1;
        if (index < 0 || index > tasks.size() - 1) {
            throw new InvalidCommandException(
                String.format("Task index %d is invalid for task list of size %d", index + 1, tasks.size()));
        }
        return index;
    }

    /**
     * Parses and returns a `Command` object from the given input string
     *
     * @param input the input string to be parsed
     * @throws InvalidCommandException the input string does not match any valid command
     * @throws InvalidTaskException the input string does not match any valid task
     */
    public Command parseCommand(String input) throws InvalidCommandException, InvalidTaskException {
        CommandType[] commands = CommandType.values();
        for (CommandType command : commands) {
            Pattern pattern = Pattern.compile(command.getRegexPattern(), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                switch (command) {
                case MARK:
                case UNMARK: {
                    boolean isMark = command.equals(CommandType.MARK);
                    String commandName = isMark ? "mark" : "unmark";
                    int idx = getValidatedIndex(commandName, matcher.group("idx"));
                    return isMark ? new MarkTaskCommand(tasks, idx) : new UnMarkTaskCommand(tasks, idx);
                }
                case BYE:
                    return new ByeCommand();
                case LIST:
                    return new ListCommand(tasks);
                case FIND:
                    String filter = matcher.group("filter");
                    if (filter == null) {
                        throw new InvalidCommandException("The find command must be followed by a search term");
                    }
                    return new FindCommand(filter, tasks);
                case DELETE: {
                    String commandName = "delete";
                    int idx = getValidatedIndex(commandName, matcher.group("idx"));
                    return new DeleteTaskCommand(tasks, idx);
                }
                case DEADLINE:
                case EVENT:
                case TODO:
                    // Fallthrough for all 3 task types
                    Task toAdd = TaskParser.generateTaskFromMatcher(matcher, command);
                    return new AddTaskCommand(tasks, toAdd);
                default:
                    throw new InvalidCommandException(
                        "Should not reach here because the matched regex pattern must belong to a valid command");
                }
            }
        }
        throw new InvalidCommandException();
    }
}
