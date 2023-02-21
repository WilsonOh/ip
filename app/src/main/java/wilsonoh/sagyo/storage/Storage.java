package wilsonoh.sagyo.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import wilsonoh.sagyo.exceptions.InvalidTaskException;
import wilsonoh.sagyo.tasklist.TaskList;
import wilsonoh.sagyo.tasks.DeadlineTask;
import wilsonoh.sagyo.tasks.EventTask;
import wilsonoh.sagyo.tasks.Task;
import wilsonoh.sagyo.tasks.TodoTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

/**
 * The class which handles the storing and retrieving of
 * tasks stored in the user's filesystem
 *
 */
public class Storage {

    private Path taskListPath;
    private final Gson gson;

    /**
     * Constructs a Storage object and creates a tasks.json file
     * if it does not exist
     *
     * The location of the file is at `$XDG_CONFIG_HOME/sagyo/tasks.json` for unix
     * systems and `$LOCALAPPDATA/sagyo/tasks.json` for windows
     */
    public Storage() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        Path xdgConfigHome;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            xdgConfigHome = Path.of(System.getenv("LOCALAPPDATA"));
        } else {
            xdgConfigHome = Path.of(System.getenv("HOME"), ".config");
        }
        Path sagyoHome = xdgConfigHome.resolve("sagyo");
        if (!Files.exists(sagyoHome)) {
            try {
                Files.createDirectory(sagyoHome);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        taskListPath = sagyoHome.resolve("tasks.json");
        if (!Files.exists(taskListPath)) {
            try {
                Files.createFile(taskListPath);
                Files.writeString(taskListPath, gson.toJson(new JsonArray()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Serializes a `TaskList` object into a JSON file
     *
     * @param tasks the task list to be serialized
     */
    public void writeTasksToJSON(TaskList tasks) {
        JsonArray jsonArray = new JsonArray();
        for (Task task : tasks) {
            JsonElement element = gson.toJsonTree(task);
            element.getAsJsonObject().addProperty("task_type", task.getClass().getSimpleName());
            jsonArray.add(element);
        }
        try {
            Files.writeString(taskListPath, gson.toJson(jsonArray));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserializes the `tasks.json` file into a `TaskList` object
     *
     * @return the deserialized TaskList object
     * @throws InvalidTaskException throws if there is an unexpected task type in the json file
     */
    public ArrayList<Task> getTaskListFromJSON() throws InvalidTaskException {
        ArrayList<Task> ret = new ArrayList<>();
        try {
            JsonArray arr = gson.fromJson(Files.readString(taskListPath), JsonArray.class);
            for (JsonElement element : arr) {
                String taskType = element.getAsJsonObject().get("task_type").getAsString();
                Task toAdd;
                switch (taskType) {
                case "TodoTask":
                    toAdd = gson.fromJson(element, TodoTask.class);
                    break;
                case "EventTask":
                    toAdd = gson.fromJson(element, EventTask.class);
                    break;
                case "DeadlineTask":
                    toAdd = gson.fromJson(element, DeadlineTask.class);
                    break;
                default:
                    throw new InvalidTaskException("Invalid task type");
                }
                ret.add(toAdd);
            }
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
