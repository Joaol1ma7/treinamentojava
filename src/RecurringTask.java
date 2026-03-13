import java.time.LocalDate;

public class RecurringTask extends  Task{
    public int recurrenceInterval;

    public RecurringTask(String title, String description, LocalDate dueDate, Priority priority,int recurrenceInterval) {
        super(title, description, dueDate, priority);
        this.recurrenceInterval=recurrenceInterval;
    }
    public RecurringTask(long id, String title, String description, LocalDate dueDate, Priority priority, Status status, int rec) {
        super(id, title, description, dueDate, priority, status);
        this.recurrenceInterval = rec;
    }

    public void show(){
        super.show();
        System.out.println("Recorrência:a cada "+this.recurrenceInterval+" dias");
    }
}
