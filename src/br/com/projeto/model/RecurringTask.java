package br.com.projeto.model;

import java.time.LocalDate;
import br.com.projeto.model.enums.Priority;
import br.com.projeto.model.enums.Status;

public class RecurringTask extends Task {
    private int recurrenceInterval;

    public RecurringTask(String title, String description, LocalDate dueDate, Priority priority, int recurrenceInterval) {
        super(title, description, dueDate, priority);
        this.recurrenceInterval=recurrenceInterval;
    }
    public RecurringTask(long id, String title, String description, LocalDate dueDate, Priority priority, Status status, int rec) {
        super(id, title, description, dueDate, priority, status);
        this.recurrenceInterval = rec;
    }

    public int getRecurrenceInterval() {
        return recurrenceInterval;
    }

    public void setRecurrenceInterval(int recurrenceInterval) {
        this.recurrenceInterval = recurrenceInterval;
    }

    public void show(){
        super.show();
        System.out.println("Recorrência:a cada "+this.recurrenceInterval+" dias");
    }
}
