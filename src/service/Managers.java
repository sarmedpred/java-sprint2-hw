package service;

/**
 * Менеджеры.
 */

public class Managers {

    public static TaskManager get(String managerType, String historyType) {
        HistoryManager historyManager;
        if (historyType.equals("inMemory")) {
            historyManager = new InMemoryHistoryManager();
        } else {
            // временно InMemoryHistoryManager(), т.к. пока нет второго HistoryManager
            historyManager = new InMemoryHistoryManager();
        }

        TaskManager taskManager;
        if (managerType.equals("inMemory")) {
            taskManager = new InMemoryTaskManager();
        } else {
            // временно InMemoryTaskManager(), т.к. пока нет второго TaskManager
            taskManager = new InMemoryTaskManager();
        }

        taskManager.setHistoryManager(historyManager);
        return taskManager;
    }

    public static TaskManager get() {
        return get("inMemory", "inMemory");
    }

}
