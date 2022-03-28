package model;

/**
 * Подзадача (для эпика)
 */

public class SubTask extends Task {
    private int epicId;

    public SubTask(String name, String description) {
        super(name, description);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}


