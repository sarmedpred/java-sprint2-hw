package service;
import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Менеджер задач.
 */

public class InMemoryTaskManager implements TaskManager {
    HashMap<Integer, Task> allTasks = new HashMap<>();
    HashMap<Integer, Epic> allEpics = new HashMap<>();
    HashMap<Integer, SubTask> allSubTasks = new HashMap<>();
    private HistoryManager inMemoryHistoryManager;

    public InMemoryTaskManager() {
        this.inMemoryHistoryManager = new InMemoryHistoryManager();
    }

    /**
     * Получение списка всех задач.
     */
    @Override
    public ArrayList<Task> getOfTasks() {
        return new ArrayList<>(allTasks.values());
    }

    /**
     * Удаление всех задач.
     */
    @Override
    public void deleteAllTasks() {
        allTasks.clear();
    }

    /**
     * Получение по идентификатору.
     */
    @Override
    public Task getTaskByID(int id) {
        Task task = allTasks.get(id);
        inMemoryHistoryManager.addTaskToHistory(task);
        return task;
    }

    /**
     * Создание задачи.
     */
    @Override
    public void saveTask(Task task) {
        allTasks.put(task.getId(), task);
    }

    /**
     * Обновление задачи.
     */
    @Override
    public void updateTask(Task task) {
        if (!allTasks.containsKey(task.getId())) {
            return;
        }
        allTasks.put(task.getId(), task);
    }

    /**
     * Удаление задачи по идентификатору.
     */
    @Override
    public void deleteTaskByID(int id) {
        allTasks.remove(id);
    }

    /**
     * Получение списка всех эпиков.
     */
    @Override
    public ArrayList<Epic> getOfEpics() {
        return new ArrayList<>(allEpics.values());
    }

    /**
     * Удаление всех эпиков.
     */
    @Override
    public void deleteAllEpics() {
        allEpics.clear();
    }

    /**
     * Получение эпика по идентификатору.
     */
    @Override
    public Epic getEpicByID(int id) {
        Epic task = allEpics.get(id);
        inMemoryHistoryManager.addTaskToHistory(task);
        return task;
    }

    /**
     * Создание Эпика.
     */
    @Override
    public void saveEpic(Epic epic) {
        allEpics.put(epic.getId(), epic);
    }

    /**
     * Обновление эпика.
     */
    @Override
    public void updateEpic(Epic epic) {
        if (!allEpics.containsKey(epic.getId())) {
            return;
        }
        allEpics.put(epic.getId(), epic);
    }

    /**
     * Удаление эпика по идентификатору.
     */
    @Override
    public void deleteEpicByID(int id) {
        Epic epic = allEpics.remove(id);
        for (SubTask subTask: epic.getSubTasks()) {
            allSubTasks.remove(subTask.getId());
        }
    }

    /**
     * Получение списка всех подзадач.
     */
    @Override
    public ArrayList<SubTask> getOfSubTasks() {
        return new ArrayList<>(allSubTasks.values());
    }

    /**
     * Удаление всех подзадач.
     */
    @Override
    public void deleteAllSubTasks() {
        allSubTasks.clear();
    }

    /**
     * Получение подзадачи по идентификатору.
     */
    @Override
    public SubTask getSubTaskByID(int id) {
        SubTask task = allSubTasks.get(id);
        inMemoryHistoryManager.addTaskToHistory(task);
        return task;
    }

    /**
     * Создание подзадачи.
     */
    @Override
    public void saveSubTask(SubTask subTask) {
        allSubTasks.put(subTask.getId(), subTask);
    }

    /**
     * Обновление подзадачи.
     */
    @Override
    public void updateSubTask(SubTask subTask) {
        if (!allSubTasks.containsKey(subTask.getId())) {
            return;
        }
        allSubTasks.put(subTask.getId(), subTask);
    }

    /**
     * Удаление подзадачи по идентификатору.
     */
    @Override
    public void deleteSubTaskByID(int id) {
        allSubTasks.remove(id);
    }

    /**
     * Получение списка всех подзадач определенного эпика.
     */
    @Override
    public ArrayList<SubTask> getListOfSubTasksIntoEpic(Epic epic) {
        if (!allEpics.containsKey(epic.getId())) {
            return null;
        }
        return new ArrayList<>(epic.getSubTasks());
    }

    /**
     * Обновление статуса эпика.
     * @return
     */
    @Override
    public Status getStatusEpic(Epic epic) {
        return epic.getStatus();
    }

    /**
     * Связывание эпика с сабтаском.
     */
    @Override
    public void linkEpicWithSubtask(Epic epic, SubTask subTask) {
        if (epic == null || !allEpics.containsKey(epic.getId())) {
            return;
        }
        epic.addSubTask(subTask);
    }

    /**
     * Получить историю просмотров задач.
     */
    @Override
    public List<Task> getHistory() {
        return this.inMemoryHistoryManager.getHistory();
    }

}


