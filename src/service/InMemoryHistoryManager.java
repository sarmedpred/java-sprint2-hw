package service;

import model.Task;
import utils.MyHashLinkedList;

import java.util.List;

/**
 * История просмотра задач в памяти.
 */
public class InMemoryHistoryManager implements HistoryManager {

    private MyHashLinkedList<Task> history = new MyHashLinkedList<>();

    @Override
    public String toString() {
        return history.toString();
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
