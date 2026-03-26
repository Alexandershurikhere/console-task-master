import java.time.LocalDate;

public class Task {
    private String title;
    private Enum<Status> statusEnum;
    private Enum<Priority> priorityEnum;
    private LocalDate deadLine;

    public Task(String title, Enum<Priority> priorityEnum, LocalDate   deadLine) {
        this.title = title;
        this.statusEnum = Status.NEW;
        this.priorityEnum = priorityEnum;
        this.deadLine = deadLine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Enum<Status> getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(Enum<Status> statusEnum) {
        this.statusEnum = statusEnum;
    }

    public Enum<Priority> getPriorityEnum() {
        return priorityEnum;
    }

    public void setPriorityEnum(Enum<Priority> priorityEnum) {
        this.priorityEnum = priorityEnum;
    }

    public LocalDate getTime() {
        return deadLine;
    }

    public void setTime(LocalDate time) {
        this.deadLine=time;
    }
}
