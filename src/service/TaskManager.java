package service;

import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Менеджер задач.
 */

public interface TaskManager {

    /**
     * Получение списка всех задач.
     */
    List<Task> getOfTasks();

    /**
     * Удаление всех задач.
     */
    void deleteAllTasks();

    /**
     * Получение по идентификатору.
     */
    Task getTaskByID(int id);

    /**
     * Создание задачи.
     */
    void saveTask(Task task);

    /**
     * Обновление задачи.
     */
    void updateTask(Task task);

    /**
     * Удаление задачи по идентификатору.
     */
    void deleteTaskByID(int id);

    /**
     * Получение списка всех эпиков.
     */
    List<Epic> getOfEpics();

    /**
     * Удаление всех эпиков.
     */
    void deleteAllEpics();

    /**
     * Получение эпика по идентификатору.
     */
    Epic getEpicByID(int id);

    /**
     * Создание Эпика.
     */
    void saveEpic(Epic epic);

    /**
     * Обновление эпика.
     */
    void updateEpic(Epic epic);

    /**
     * Удаление эпика по идентификатору.
     */
    void deleteEpicByID(int id);

    /**
     * Получение списка всех подзадач.
     */
    List<SubTask> getOfSubTasks();

    /**
     * Удаление всех подзадач.
     */
    void deleteAllSubTasks();

    /**
     * Получение подзадачи по идентификатору.
     */
    SubTask getSubTaskByID(int id);

    /**
     * Создание подзадачи.
     */
    void saveSubTask(SubTask subTask);

    /**
     * Обновление подзадачи.
     */
    void updateSubTask(SubTask subTask);

    /**
     * Удаление подзадачи по идентификатору.
     */
    void deleteSubTaskByID(int id);

    /**
     * Получение списка всех подзадач определенного эпика.
     */
    List<SubTask> getListOfSubTasksIntoEpic(Epic epic);

    /**
     * Обновление статуса эпика.
     * @return
     */
    Status getStatusEpic(Epic epic);

    /**
     * Связывание эпика с сабтаском.
     */
    void linkEpicWithSubtask(Epic epic, SubTask subTask);

    /**
     * Получить историю просмотров задач.
     */
    List<Task> getHistory();

    /**
     * Линковка наблюдателя истории.
     */
    void setHistoryManager(HistoryManager historyManager);

}
