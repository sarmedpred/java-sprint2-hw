package service;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.Epic;
import model.SubTask;
import model.Task;
import model.TaskType;

/**
 * Класс реализовывает логику автосохранения в файл.
 */

public class FileBackedTasksManager extends InMemoryTaskManager {
    private File file;
    private Boolean hasToBeSaved = true;

    public FileBackedTasksManager(String filePath){
        setFile(filePath);
    }

    public FileBackedTasksManager(){}

    /**
     * Установление пути к файлу.
     */
    public void setFile(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Установление пути к файлу.
     */
    public void setFile(File file) {
        this.file = file;
        System.out.println(file.getPath());
    }

    public static void main(String[] args) {
        TaskManager manager = Managers.get("FileBacked", "inMemory");
        manager.setSettings(args);

        // Создание задачи 1.
        Task task1 = new Task("Тест_1", "Описание");
        manager.saveTask(task1);
        Task taskCheck1 = manager.getTaskByID(task1.getId());
        if (!taskCheck1.equals(task1)) {
            System.out.println("Ошибка: задача + " + task1.getName() + " не найдена по идентификатору " + task1.getId());
        }
        System.out.println(manager.getOfTasks());

        // Создание задачи 2.
        Task task2 = new Task("Тест_2", "Описание");
        manager.saveTask(task2);
        Task taskCheck2 = manager.getTaskByID(task2.getId());
        if (!taskCheck2.equals(task2)) {
            System.out.println("Ошибка: задача + " + task2.getName() + " не найдена по идентификатору " + task2.getId());
        }
        System.out.println(manager.getOfTasks());

        // Создание эпика 1 с двумя подзадачами
        Epic epic1 = new Epic("Эпик_1", "Описание");
        manager.saveEpic(epic1);
        SubTask subTask1 = new SubTask("Подзадача_1", "Описание");
        manager.saveSubTask(subTask1);
        manager.linkEpicWithSubtask(epic1, subTask1);
        SubTask subTask2 = new SubTask("Подзадача_2", "Описание");
        manager.saveSubTask(subTask2);
        manager.linkEpicWithSubtask(epic1, subTask2);
        Epic epicCheck1 = manager.getEpicByID(epic1.getId());
        if (!epicCheck1.equals(epic1)) {
            System.out.println("Ошибка: эпик + " + epic1.getName() + " не найден по идентификатору " + epic1.getId());
        }
        System.out.println(manager.getOfEpics());
        System.out.println(manager.getOfSubTasks());

        // Создание эпика 2 с одной подзадачей
        Epic epic2 = new Epic("Эпик_2", "Описание");
        manager.saveEpic(epic2);
        SubTask subTask3 = new SubTask("Подзадача_1", "Описание");
        manager.saveSubTask(subTask3);
        manager.linkEpicWithSubtask(epic2, subTask3);
        Epic epicCheck2 = manager.getEpicByID(epic2.getId());
        if (!epicCheck2.equals(epic2)) {
            System.out.println("Ошибка: эпик + " + epic2.getName() + " не найден по идентификатору " + epic2.getId());
        }
        System.out.println(manager.getOfEpics());
        System.out.println(manager.getOfSubTasks());

        // Вызов разных тасков
        manager.getTaskByID(task1.getId());
        manager.getTaskByID(task2.getId());
        manager.getEpicByID(epic1.getId());
        manager.getSubTaskByID(subTask1.getId());
        manager.getSubTaskByID(subTask2.getId());
        manager.getEpicByID(epic2.getId());
        manager.getSubTaskByID(subTask3.getId());

        List<String> data = manager.serialize();

        //Проверка, что история просмотра восстановилась верно и все задачи, эпики, подзадачи,
        //которые были в старом, есть в новом менеджере.
        TaskManager manager2 = Managers.get("FileBacked", "inMemory");
        manager2.setSettings(args);

        List<String> data2 = manager2.serialize();
        if(data.equals(data2)) {
            System.out.println("Managers are the same");
        } else {
            System.out.println("Managers are NOT the same");
        }

        System.out.println(data);
        System.out.println("=========");
        System.out.println(data2);

    }

    /**
     * Кастомизация таскменеджера через аргументы командной строки
     */
    @Override
    public void setSettings(String[] args){
        if (args.length == 0) {
            URL resource = getClass().getClassLoader().getResource("sprint_6_data.csv");
            try {setFile(new File(resource.toURI()));}
            catch (URISyntaxException e) {
                System.out.println(e);
            }
        } else {
            setFile(args[0]);
        }
        setHistoryManager(new InMemoryHistoryManager());
        load();
    }

    /**
     *Восстанавление данных менеджера из файла при запуске программы.
     */
    public static FileBackedTasksManager loadFromFile(String filePath) {
        FileBackedTasksManager tm = new FileBackedTasksManager(filePath);
        tm.load();
        return tm;
    }

    /**
     * Загрузка данных из сохраненного файла в объект таскменеджера.
     */
    public void load() {
        List<String> content = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.file));
            String line;
            Boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }
                line = line.trim();
                if (line.length() == 0) continue;
                content.add(line);
            }
        } catch (Exception e) {
            // don`t mind about reading problem
        }

        this.hasToBeSaved = false;
        for(String line: content) {
            String[] chunks = line.split(",");
            try {
                TaskType ttp = TaskType.valueOf(chunks[1]);
                switch (ttp) {
                    case TASK:
                        Task t = Task.fromCSVString(line);
                        this.saveTask(t);
                        break;
                    case EPIC:
                        Epic e = Epic.fromCSVString(line);
                        this.saveEpic(e);
                        break;
                    case SUBTASK:
                        SubTask st = SubTask.fromCSVString(line);
                        this.saveSubTask(st);
                        break;
                    default:
                        System.out.println("Unrecognised task");
                }
            } catch (IllegalArgumentException e) {
                this.historyFromString(line);
            }
        }
        this.hasToBeSaved = true;
    }

    /**
     * Сохранение текущего состояния менеджера в файл.
     */
    public void save() throws ManagerSaveException{
        if (!hasToBeSaved) return;
        List<String> data = serialize();

        try {
            Writer fileWriter = new FileWriter(file);
            for (String s: data) {
                fileWriter.write(s + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new ManagerSaveException("Save error");
        }

    }

    /**
     * Сохранение менеджера истории в CSV.
     */
    public String historyToString(HistoryManager manager) {
        return inMemoryHistoryManager.toCSVString();
    }

    /**
     * Восстановления менеджера истории из CSV.
     */
    public List<Integer> historyFromString(String value) {
        List<Integer> ret = new ArrayList<>();
        for (String tid: value.split(",")) {
            Integer id = Integer.parseInt(tid);
            ret.add(id);
            getTaskByID(id);
            getEpicByID(id);
            getSubTaskByID(id);
        }
        return ret;
    }

    /**
     * Получение по идентификатору.
     */
    @Override
    public Task getTaskByID(int id) {
        Task t = super.getTaskByID(id);
        save();
        return t;
    }

    /**
     * Получение эпика по идентификатору.
     */
    @Override
    public Epic getEpicByID(int id) {
        Epic e = super.getEpicByID(id);
        save();
        return e;
    }

    /**
     * Получение подзадачи по идентификатору.
     */
    @Override
    public SubTask getSubTaskByID(int id) {
        SubTask st = super.getSubTaskByID(id);
        save();
        return st;
    }

    /**
     * Удаление всех задач.
     */
    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
        save();
    }

    /**
     * Создание задачи.
     */
    @Override
    public void saveTask(Task task) {
        super.saveTask(task);
        save();
    }

    /**
     * Обновление задачи.
     */
    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    /**
     * Удаление задачи по идентификатору.
     */
    @Override
    public void deleteTaskByID(int id) {
        super.deleteTaskByID(id);
        save();
    }

    /**
     * Удаление всех эпиков.
     */
    @Override
    public void deleteAllEpics() {
        super.deleteAllEpics();
        save();
    }

    /**
     * Создание Эпика.
     */
    @Override
    public void saveEpic(Epic epic) {
        super.saveEpic(epic);
        save();
    }

    /**
     * Обновление эпика.
     */
    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    /**
     * Удаление эпика по идентификатору.
     */
    @Override
    public void deleteEpicByID(int id) {
        super.deleteEpicByID(id);
        save();
    }

    /**
     * Удаление всех подзадач.
     */
    @Override
    public void deleteAllSubTasks() {
        super.deleteAllSubTasks();
        save();
    }

    /**
     * Создание подзадачи.
     */
    @Override
    public void saveSubTask(SubTask subTask) {
        super.saveSubTask(subTask);
        save();
    }

    /**
     * Обновление подзадачи.
     */
    @Override
    public void updateSubTask(SubTask subTask) {
        super.updateSubTask(subTask);
        save();
    }

    /**
     * Удаление подзадачи по идентификатору.
     */
    @Override
    public void deleteSubTaskByID(int id) {
        super.deleteSubTaskByID(id);
        save();
    }

}

    /**
     * Класс с exception.
     */

class ManagerSaveException extends RuntimeException {
    public ManagerSaveException (String msg) {
        super(msg);
    }
}