public class Task {

  private String name;
  boolean done;

  Task(String taskName) {
    this.name = taskName;
    this.done = false;
  }

  public void markDone() {
    this.done = true;
  }

  public void unMarkDone() {
    this.done = false;
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", this.done ? "X" : " ", this.name);
  }
}
