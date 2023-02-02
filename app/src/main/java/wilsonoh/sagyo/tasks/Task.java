package wilsonoh.sagyo.tasks;

public abstract class Task {

    private final String name;
    private boolean done;

    public Task(String taskName) {
        this.name = taskName;
        this.done = false;
    }

    public void markDone() {
        this.done = true;
    }

    public void unMarkDone() {
        this.done = false;
    }

    public abstract String getTaskType();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.done ? "X" : " ", this.name);
    }
}
