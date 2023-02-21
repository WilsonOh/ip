package wilsonoh.sagyo.tasks;

/**
 * An abstract class representing a task.
 * Contains all the methods a Task class should have
 *
 */
public abstract class Task {

    private final String name;
    private boolean done;

    public Task(String taskName) {
        this.name = taskName;
        this.done = false;
    }

    /**
     * Marks the task object as being done
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Marks the task object as not being done
     */
    public void unMarkDone() {
        this.done = false;
    }

    public abstract String getTaskType();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "X" : " ", this.name);
    }
}
