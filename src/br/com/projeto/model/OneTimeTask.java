package br.com.projeto.model;

import java.time.LocalDate;
import br.com.projeto.model.enums.Priority;
import br.com.projeto.model.enums.Status;

public class OneTimeTask extends Task {
    public OneTimeTask(String title, String description, LocalDate dueDate, Priority priority) {
        super(title, description, dueDate, priority);
    }
    public OneTimeTask(long id, String title, String description, LocalDate dueDate, Priority priority, Status status) {
        super(id, title, description, dueDate, priority, status);
    }
}
