import java.util.ArrayList;
import java.util.Objects;

/**
 * Эпик (глобальная задача с подзадачами)
 */

public class Epic extends Task {
    private ArrayList<SubTask> subTask;

    public Epic(String name, String description) {
        super(name, description);
        subTask = new ArrayList<>();
    }

    public ArrayList<SubTask> getSubTask() {
        return subTask;
    }

    public void setSubTask(ArrayList<SubTask> subTask) {
        this.subTask = subTask;
    }

    public void addSubTask(SubTask subTask) {
        this.subTask.add(subTask);
        subTask.setEpicId(this.getId());
    }

    /**
     * Вычисление статуса эпика.
     */
    @Override
    public String getStatus() {
        int counterNew = 0;
        int counterDone = 0;
        int counterInProgress = 0;

        for(SubTask subTask: this.getSubTask()) {
            if (subTask.getStatus().equals(Status.NEW)) {
                counterNew++;
            } else if (subTask.getStatus().equals(Status.DONE)) {
                counterDone++;
            } else {
                counterInProgress++;
            }
        }

        if (this.getSubTask().size() == counterNew) {
            return Status.NEW;
        } else if (this.getSubTask().size() == counterDone) {
            return Status.DONE;
        }
        return Status.IN_PROGRESS;

    }

}
