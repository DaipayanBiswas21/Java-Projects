package MyProject.src;

public class Task {
    private String title;
    private String deadline; // format: yyyy-mm-dd
    private String priority; // HIGH, MEDIUM, LOW
    private boolean completed;

    public Task(String title, String deadline, String priority) {
        this.title = title;
        this.deadline = deadline;
        this.priority = priority.toUpperCase();
        this.completed = false;
    }

    public String getTitle() { return title; }
    public String getDeadline() { return deadline; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return completed; }

    public void markCompleted() { this.completed = true; }

    @Override
    public String toString() {
        return String.format("[%s] %s | Deadline: %s | Priority: %s",
                completed ? "X" : " ", title, deadline, priority);
    }
}

