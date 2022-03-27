/**
 * Подзадача (для эпика)
 */

public class SubTask extends Task {
    private int epicId;

    public SubTask(String name, String description, int id, String status) {
        super(name, description, id, status);
    }

    public SubTask(String name, String description, int epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}


