package wilsonoh.sagyo.tasklist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import wilsonoh.sagyo.exceptions.InvalidTaskException;
import wilsonoh.sagyo.storage.Storage;
import wilsonoh.sagyo.tasks.Task;

/**
 * A wrapper class over `ArrayList<Task>` to facilitate
 * the various operations on tasks
 *
 */
public class TaskList implements Iterable<Task> {

    private final ArrayList<Task> tasks = new ArrayList<>();

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Add a task to the task list
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets a task from the task list by index
     *
     * @param index the index of the task
     * @return the task with index `index`
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task as done by index
     *
     * @param index index of the task
     */
    public void markTask(int index) {
        tasks.get(index).markDone();
    }

    /**
     * Marks a task as not done by index
     *
     * @param index index of the task
     */
    public void unMarkTask(int index) {
        tasks.get(index).unMarkDone();
    }

    /**
     * Deletes a task by index
     *
     * @param index the index of the task
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the task list
     *
     * @return the number of tasks in the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns `true` if the task list is empty
     *
     * @return `true` if the task list is empty, else `false`
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds all the tasks found in the storage to the current task list
     *
     * @param storage the storage object to extend from
     */
    public void extendFromStorage(Storage storage) {
        try {
            tasks.addAll(storage.getTaskListFromJSON());
        } catch (InvalidTaskException e) {
            // Do not extend from corrupted storage
            tasks.clear();
        }
    }

    /**
     * Get a formatted view of the tasks in the task list
     *
     * @return a formatted view of the tasks in the task list
     */
    public String[] getTasksString() {
        String[] taskStrings = new String[tasks.size()];
        int idx = 0;
        for (Task task : tasks) {
            taskStrings[idx] = String.format("%d: %s", idx + 1, task);
            idx++;
        }
        return taskStrings;
    }

    /**
     * Returns a filtered tasks string array where the tasks matches a keyword
     *
     * @param filter the keyword to match against
     * @return the filtered tasks string array where the tasks matches a keyword
     */
    public String[] getFilteredTasksString(String filter) {
        String[] tasksString = getTasksString();
        return Arrays.stream(tasksString)
            .filter(s -> s.toLowerCase().contains(filter.toLowerCase()))
            .toArray(String[] ::new);
    }
}
