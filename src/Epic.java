import java.util.ArrayList;

/**
 * Эпик (глобальная задача с подзадачами)
 */

public class Epic extends Task {
    private ArrayList<SubTask> subTask;

    public Epic(String name, String description) {
        super(name, description);
    }

    public Epic(String name, String description, int id, String status) {
        super(name, description, id, status);
    }

    public Epic(String name, String description, int id, String status, ArrayList<SubTask> subTask) {
        super(name, description, id, status);
        this.subTask = subTask;
    }

    public ArrayList<SubTask> getSubTask() {
        return subTask;
    }

    public void setSubTask(ArrayList<SubTask> subTask) {
        this.subTask = subTask;
    }

}
