public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();

        // Создание задачи 1.
        Task task1 = new Task("Тест_1", "Описание");
        manager.saveTask(task1);
        Task taskCheck1 = manager.getTaskByID(task1.getId());
        if (!taskCheck1.equals(task1)) {
            System.out.println("Ошибка: задача + " + task1.getName() + " не найдена по идентификатору " + task1.getId());
        }
        System.out.println(manager.getListOfTasks());

        // Создание задачи 2.
        Task task2 = new Task("Тест_2", "Описание");
        manager.saveTask(task2);
        Task taskCheck2 = manager.getTaskByID(task2.getId());
        if (!taskCheck2.equals(task2)) {
            System.out.println("Ошибка: задача + " + task2.getName() + " не найдена по идентификатору " + task2.getId());
        }
        System.out.println(manager.getListOfTasks());

        // Изменение статуса задачи 1 и задачи 2.
        task1.setStatus(Status.IN_PROGRESS);
        manager.updateTask(task1);
        System.out.println(manager.getTaskByID(task1.getId()));
        task2.setStatus(Status.IN_PROGRESS);
        manager.updateTask(task2);
        System.out.println(manager.getTaskByID(task2.getId()));

        // Удаление задачи 2.
        manager.deleteTaskByID(task2.getId());
        if (manager.getTaskByID(task2.getId()) != null) {
            System.out.println("Ошибка: задача " + task2.getName() + " c идентификатором " + task2.getId() + " не удалена.");
        }
        System.out.println(manager.getListOfTasks());

        // Создание эпика 1 с двумя подзадачами
        Epic epic1 = new Epic("Эпик_1", "Описание");
        manager.saveEpic(epic1);
        int epic1Id = epic1.getId();
        SubTask subTask1 = new SubTask("Подзадача_1", "Описание", epic1Id);
        manager.saveSubTask(subTask1);
        SubTask subTask2 = new SubTask("Подзадача_2", "Описание", epic1Id);
        manager.saveSubTask(subTask2);
        manager.getEpicByID(epic1.getId());
        final Epic epicCheck1 = manager.getEpicByID(epic1.getId());
        if (!epicCheck1.equals(epic1)) {
            System.out.println("Ошибка: эпик + " + epic1.getName() + " не найден по идентификатору " + epic1.getId());
        }
        System.out.println(manager.getListOfEpics());
        System.out.println(manager.getListOfSubTasks());

        // Создание эпика 2 с одной подзадачей
        Epic epic2 = new Epic("Эпик_2", "Описание");
        manager.saveEpic(epic2);
        int epic2Id = epic2.getId();
        SubTask subTask3 = new SubTask("Подзадача_1", "Описание", epic2Id);
        manager.saveSubTask(subTask3);
        manager.getEpicByID(epic2.getId());
        final Epic epicCheck2 = manager.getEpicByID(epic2.getId());
        if (!epicCheck2.equals(epic2)) {
            System.out.println("Ошибка: эпик + " + epic2.getName() + " не найден по идентификатору " + epic2.getId());
        }
        System.out.println(manager.getListOfEpics());
        System.out.println(manager.getListOfSubTasks());

        // Изменение статуса эпика 2. Сначала меняем статус подзадачи 1 у эпика 2.
        subTask3 = manager.getSubTaskByID(subTask3.getId());
        subTask3.setStatus(Status.IN_PROGRESS);
        manager.updateSubTask(subTask3);
        System.out.println(manager.getSubTaskByID(subTask3.getId()));
        manager.getStatusEpic(epic2);

        // Удаление эпика 2.
        manager.deleteEpicByID(epic2.getId());
        if (manager.allEpics.containsKey(epic2.getId())) {
            System.out.println("Ошибка: эпик + " + epic2.getName() + " не удален " + " по идентификатору " + epic2.getId());
        }
        System.out.println(manager.getListOfEpics());

    }

}








