package service;

import model.Task;

import java.util.List;

/**
 * История просмотра задач.
 */

public interface HistoryManager {

    /**
     * Получить историю просмотра задач.
     */
    List<Task> getHistory();

    /**
     * Добавить задачу в историю просмотра.
     */
    void addTaskToHistory(Task task);

    /**
     * Удалить задачу из истории просмотра.
     */
    void removeTaskFromHistory(Task task);

    /**
     * Приведение к формату String для CSV.
     */
    String toCSVString();
}
