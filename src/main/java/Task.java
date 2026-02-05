public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    protected String statusIcon() {
        return isDone ? "X" : " ";
    }

    protected abstract String typeIcon();

    @Override
    public String toString() {
        return "[" + typeIcon() + "][" + statusIcon() + "] " + description;
    }
}
