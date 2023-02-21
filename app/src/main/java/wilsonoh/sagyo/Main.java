package wilsonoh.sagyo;

import java.util.Scanner;

import wilsonoh.sagyo.commands.Command;
import wilsonoh.sagyo.exceptions.SagyoException;
import wilsonoh.sagyo.parser.CommandParser;
import wilsonoh.sagyo.storage.Storage;
import wilsonoh.sagyo.tasklist.TaskList;
import wilsonoh.sagyo.ui.TextFormatter;

public class Main {

    private static final String[] GREETING =
        new String[] {"Welcome to sagyo, your task manager!", "What can I do for you?"};

    private static final String PROMPT = "> ";

    private final TaskList tasks = new TaskList();

    /**
     * Runner function of the class, executes the main loop
     */
    public void run() {
        TextFormatter ui = new TextFormatter(2, 120);
        Storage storage = new Storage();
        this.tasks.extendFromStorage(storage);
        CommandParser cmdParser = new CommandParser(tasks);
        boolean isRunning = true;
        ui.printLinesInfo(GREETING);
        try (Scanner sc = new Scanner(System.in)) {
            while (isRunning) {
                System.out.print(PROMPT);
                String line = sc.nextLine();
                try {
                    Command cmd = cmdParser.parseCommand(line);
                    cmd.executeCommand();
                    ui.printLinesInfo(cmd.getCommandMessage());
                    if (cmd.isExit()) {
                        isRunning = false;
                    }
                } catch (SagyoException e) {
                    ui.printLinesError(e.getMessage());
                }
                storage.writeTasksToJSON(tasks);
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
