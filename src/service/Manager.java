package service;
import model.Epic;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Менеджер задач.
 */

public class Manager {
    HashMap<Integer, Task> allTasks = new HashMap<>();
    HashMap<Integer, Epic> allEpics = new HashMap<>();
    HashMap<Integer, SubTask> allSubTasks = new HashMap<>();

    /**
     * Получение списка всех задач.
     */
    public ArrayList<Task> getListOfTasks() {
        return new ArrayList<>(allTasks.values());
    }

    /**
     * Удаление всех задач.
     */
    public void deleteAllTasks() {
        allTasks.clear();
    }

    /**
     * Получение по идентификатору.
     */
    public Task getTaskByID(int id) {
        return allTasks.get(id);
    }

    /**
     * Создание задачи.
     */
    public void saveTask(Task task) {
        allTasks.put(task.getId(), task);
    }

    /**
     * Обновление задачи.
     */
    public void updateTask(Task task) {
        if (!allTasks.containsKey(task.getId())) {
            return;
        }
        allTasks.put(task.getId(), task);
    }

    /**
     * Удаление задачи по идентификатору.
     */
    public void deleteTaskByID (int id) {
        allTasks.remove(id);
    }

    /**
     * Получение списка всех эпиков.
     */
    public ArrayList<Epic> getListOfEpics() {
        return new ArrayList<>(allEpics.values());
    }

    /**
     * Удаление всех эпиков.
     */
    public void deleteAllEpics() {
        allEpics.clear();
    }

    /**
     * Получение эпика по идентификатору.
     */
    public Epic getEpicByID(int id) {
        return allEpics.get(id);
    }

    /**
     * Создание Эпика.
     */
    public void saveEpic(Epic epic) {
        allEpics.put(epic.getId(), epic);
    }


    /**
     * Обновление эпика.
     */
    public void updateEpic(Epic epic) {
        if (!allEpics.containsKey(epic.getId())) {
            return;
        }
        allEpics.put(epic.getId(), epic);
    }

    /**
     * Удаление эпика по идентификатору.
     */
    public void deleteEpicByID (int id) {
        Epic epic = allEpics.remove(id);
        for (SubTask subTask: epic.getSubTasks()) {
            allSubTasks.remove(subTask.getId());
        }
    }

    /**
     * Получение списка всех подзадач.
     */
    public ArrayList<SubTask> getListOfSubTasks() {
        return new ArrayList<>(allSubTasks.values());
    }

    /**
     * Удаление всех подзадач.
     */
    public void deleteAllSubTasks() {
        allSubTasks.clear();
    }

    /**
     * Получение подзадачи по идентификатору.
     */
    public SubTask getSubTaskByID(int id) {
        return allSubTasks.get(id);
    }

    /**
     * Создание подзадачи.
     */
    public void saveSubTask(SubTask subTask) {
        allSubTasks.put(subTask.getId(), subTask);
    }

    /**
     * Обновление подзадачи.
     */
    public void updateSubTask(SubTask subTask) {
        if (!allSubTasks.containsKey(subTask.getId())) {
            return;
        }
        allSubTasks.put(subTask.getId(), subTask);
    }

    /**
     * Удаление подзадачи по идентификатору.
     */
    public void deleteSubTaskByID (int id) {
        allSubTasks.remove(id);
    }

    /**
     * Получение списка всех подзадач определенного эпика.
     */
    public ArrayList<SubTask> getListOfSubTasksIntoEpic(Epic epic) {
        if (!allEpics.containsKey(epic.getId())) {
            return null;
        }
        return new ArrayList<>(epic.getSubTasks());
    }

    /**
     * Обновление статуса эпика.
     */
    public String getStatusEpic(Epic epic) {
        return epic.getStatus();
    }

    /**
     * Связывание эпика с сабтаском.
     */
    public void linkEpicWithSubtask(Epic epic, SubTask subTask) {
        epic.addSubTask(subTask);
    }

}


