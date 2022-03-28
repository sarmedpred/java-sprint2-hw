package model;

import java.util.ArrayList;

/**
 * Эпик (глобальная задача с подзадачами)
 */

public class Epic extends Task {
    private ArrayList<SubTask> subTasks = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
        subTasks = new ArrayList<>();
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public SubTask addSubTask(SubTask subTask) {
        if (!subTasks.contains(subTask)) {
            subTasks.add(subTask);
            subTask.setEpicId(this.getId());
            return subTask;
        }
        return null;
    }

    /**
     * Удаление сабтаска из эпика.
     */
    public boolean removeSubTask(SubTask subTask) {
        int removeId = -1;
        for (int i = 0; i < subTasks.size(); i++) {
            if (subTasks.get(i).equals(subTask)) {
                removeId = i;
                break;
            }
        }
        if (removeId != -1) {
            subTask.setEpicId(0);
            subTasks.remove(removeId);
            return true;
        }
        return false;
    }

    /**
     * Вычисление статуса эпика.
     */
    @Override
    public String getStatus() {
        int counterNew = 0;
        int counterDone = 0;
        int counterInProgress = 0;

        for(SubTask subTask: this.getSubTasks()) {
            if (subTask.getStatus().equals(Status.NEW)) {
                counterNew++;
            } else if (subTask.getStatus().equals(Status.DONE)) {
                counterDone++;
            } else {
                counterInProgress++;
            }
        }

        if (this.getSubTasks().size() == counterNew) {
            return Status.NEW;
        } else if (this.getSubTasks().size() == counterDone) {
            return Status.DONE;
        }
        return Status.IN_PROGRESS;

    }

}
