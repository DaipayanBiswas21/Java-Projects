package MyProject.src;

import java.util.*;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void completeTask(String title) {
        for (Task t : tasks) {
            if (t.getTitle().equalsIgnoreCase(title) && !t.isCompleted()) {
                t.markCompleted();
                return;
            }
        }
    }

    public List<Task> getTasks(boolean includeCompleted) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (includeCompleted || !t.isCompleted()) {
                result.add(t);
            }
        }
        return result;
    }

    public List<Task> getTasksSortedByDeadline() {
        List<Task> sorted = new ArrayList<>(tasks);
        sorted.sort(Comparator.comparing(Task::getDeadline));
        return sorted;
    }

    public List<Task> getTasksSortedByPriority() {
        List<Task> sorted = new ArrayList<>(tasks);
        sorted.sort(Comparator.comparing(t -> switch (t.getPriority()) {
            case "HIGH" -> 1;
            case "MEDIUM" -> 2;
            case "LOW" -> 3;
            default -> 4;
        }));
        return sorted;
    }
}


