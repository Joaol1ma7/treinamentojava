package br.com.projeto.model;

import java.time.LocalDate;
import br.com.projeto.model.enums.Priority;
import br.com.projeto.model.enums.Status;
public abstract class Task {
    private static long contadorId=1;
    private final long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Status status;
    private Priority priority;

    public Task(String title, String description, LocalDate dueDate, Priority priority){
        this.id=contadorId++;
        this.title=title;
        this.description=description;
        this.dueDate=dueDate;
        this.status= Status.TO_DO;
        this.priority=priority;
    }
    public Task(long id, String title, String description, LocalDate dueDate, Priority priority, Status status) {
        this.id = id;
        this.title = title;
        this.description=description;
        this.dueDate=dueDate;
        this.status = status;
        this.priority=priority;
        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    public String getTitle() {
        return title;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void show() {
        System.out.println("[ID:"+this.id+", Título:"+this.title+", Descrição:"+this.description+", Data de validade: "+this.dueDate+", status:"+this.status+", Prioridade:"+this.priority+"]");

    }
}
