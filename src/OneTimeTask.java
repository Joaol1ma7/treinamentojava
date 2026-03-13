import java.time.LocalDate;

public class OneTimeTask extends Task {
    public OneTimeTask(String title, String description, LocalDate dueDate, Priority priority) {
        super(title, description, dueDate, priority);
    }
    public OneTimeTask(long id, String title, String description, LocalDate dueDate, Priority priority, Status status) {
        super(id, title, description, dueDate, priority, status);
    }
}
