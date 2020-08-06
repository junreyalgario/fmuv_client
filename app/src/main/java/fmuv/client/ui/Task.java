package fmuv.client.ui;

public class Task {

    private String name;
    private boolean isDone;
    private int priority;

    public Task(String name, boolean isDone, int priority) {
        this.name = name;
        this.isDone = isDone;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
