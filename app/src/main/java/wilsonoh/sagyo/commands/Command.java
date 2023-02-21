package wilsonoh.sagyo.commands;

/**
 * An abstract base class for commands that allow
 * the user to interact with the program
 *
 */
public abstract class Command {

    protected boolean isExit = false;

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Gets the message associated with the command, if any.
     */
    public abstract String[] getCommandMessage();

    /**
     * Executes the commands
     */
    public abstract void executeCommand();
}
