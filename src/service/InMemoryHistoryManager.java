package service;

import model.Task;
import utils.MyHashLinkedList;

import java.util.ArrayList;
import java.util.List;

/**
 * История просмотра задач в памяти.
 */
public class InMemoryHistoryManager implements HistoryManager {

    private MyHashLinkedList<Task> history = new MyHashLinkedList<>();

    /**
     * Приведение истории к формату String.
     */

    @Override
    public String toString() {
        return history.toString();
    }

    /**
     * Приведение к формату String для CSV.
     */

    @Override
    public String toCSVString() {
        StringBuilder sb = new StringBuilder();
        for (Task t: getHistory()) {
            sb.append(t.getId());
            sb.append(",");
        }
        if (sb.length() > 0) sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Получить историю просмотров.
     */

    @Override
    public List<Task> getHistory() {
        return history.getTasks();
    }

    /**
     * Добавить задачу в историю просмотров.
     */

    @Override
    public void addTaskToHistory(Task task) {
        history.linkLast(task);
    }

    /**
     * Удалить задачу из истории просмотра.
     */
    @Override
    public void removeTaskFromHistory(Task task) {
        history.removeTask(task);
    }
}
