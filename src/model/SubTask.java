package model;

/**
 * Подзадача (для эпика)
 */

public class SubTask extends Task {
    private int epicId;

    public SubTask(String name, String description) {
        super(name, description);
    }
    public SubTask(String name, String description, int id, Status s) {
        super(name, description, id, s);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toCSVString() {
        return getId() + ",SUBTASK," + getName() + "," + getStatus() + "," + getDescription() + "," + epicId;
    }

    public static SubTask fromCSVString(String s) {
        String[] split = s.split(",");
        SubTask st = new SubTask(split[2], split[4], Integer.parseInt(split[0]), Status.valueOf(split[3]));
        st.setEpicId(Integer.parseInt(split[5]));
        return st;
    }
}


