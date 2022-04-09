package service;

import model.Task;

import java.util.LinkedList;
import java.util.List;

/**
 * История просмотра задач в памяти.
 */

public class InMemoryHistoryManager implements HistoryManager {

    List<Task> history = new LinkedList<>();

    @Override
    public List<Task> getHistory() {
        return history;
    }

    @Override
    public void addTaskToHistory(Task task) {
        if (task == null) {
            return;
        }
        if (history.size() == 10) {
            history.remove(0);
        }
        history.add(task);
    }
}
