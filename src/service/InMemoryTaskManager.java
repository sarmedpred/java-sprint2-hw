package service;
import model.Epic;
import model.Status;
import model.SubTask;
import model.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Менеджер задач.
 */

public class InMemoryTaskManager implements TaskManager {
    private Map<Integer, Task> allTasks = new HashMap<>();
    private Map<Integer, Epic> allEpics = new HashMap<>();
    private Map<Integer, SubTask> allSubTasks = new HashMap<>();
    protected HistoryManager inMemoryHistoryManager;

    /**
     * Сериализация объекта в список строк
     */
    @Override
    public List<String> serialize(){
        List<String> data = new ArrayList<>();
        data.add("id,type,name,status,description,epic");

        List<Task> allTasks = getOfTasks();
        for(Task t: allTasks) {
            String s = t.toCSVString();
            data.add(s);
        }

        List<Epic> allEpics = getOfEpics();
        for(Epic e: allEpics) {
            String s = e.toCSVString();
            data.add(s);
        }

        List<SubTask> allSubTasks = getOfSubTasks();
        for(SubTask st: allSubTasks) {
            String s = st.toCSVString();
            data.add(s);
        }

        data.add("");

        data.add(inMemoryHistoryManager.toCSVString());
        return data;
    };

    /**
     * Кастомизация таскменеджера через аргументы командной строки
     */
    @Override
    public void setSettings(String[] string){}

    /**
     * Получение списка всех задач.
     */
    @Override
    public List<Task> getOfTasks() {
        return new ArrayList<>(allTasks.values());
    }

    /**
     * Удаление всех задач.
     */
    @Override
    public void deleteAllTasks() {
        for (Task t: allTasks.values()) {
            inMemoryHistoryManager.removeTaskFromHistory(t);
        }
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
        Task t = allTasks.remove(id);
        if (t != null) inMemoryHistoryManager.removeTaskFromHistory(t);
    }

    /**
     * Получение списка всех эпиков.
     */
    @Override
    public List<Epic> getOfEpics() {
        return new ArrayList<>(allEpics.values());
    }

    /**
     * Удаление всех эпиков.
     */
    @Override
    public void deleteAllEpics() {
        for (Task t: allEpics.values()) {
            inMemoryHistoryManager.removeTaskFromHistory(t);
        }
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
        inMemoryHistoryManager.removeTaskFromHistory(epic);
        for (SubTask subTask: epic.getSubTasks()) {
            allSubTasks.remove(subTask.getId());
            inMemoryHistoryManager.removeTaskFromHistory(subTask);
        }
    }

    /**
     * Получение списка всех подзадач.
     */
    @Override
    public List<SubTask> getOfSubTasks() {
        return new ArrayList<>(allSubTasks.values());
    }

    /**
     * Удаление всех подзадач.
     */
    @Override
    public void deleteAllSubTasks() {
        for (SubTask s: allSubTasks.values()) {
            inMemoryHistoryManager.removeTaskFromHistory(s);
        }
        for (SubTask st: allSubTasks.values()) {
            Epic epic = getEpicByID(st.getEpicId());
            if (epic != null) epic.removeSubTask(st);
        }
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
        SubTask s = allSubTasks.remove(id);
        if (s != null) inMemoryHistoryManager.removeTaskFromHistory(s);
    }

    /**
     * Получение списка всех подзадач определенного эпика.
     */
    @Override
    public List<SubTask> getListOfSubTasksIntoEpic(Epic epic) {
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

    /**
     * Линковка наблюдателя истории.
     */
    @Override
    public void setHistoryManager(HistoryManager historyManager) {
        this.inMemoryHistoryManager = new InMemoryHistoryManager();
    }

}


