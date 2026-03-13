import java.time.LocalDate;

public abstract class Task {
    private static long contadorId=1;
    public final long id;
    public String title;
    public String description;
    public LocalDate dueDate;
    public Status status;
    public Priority priority;

    public Task(String title, String description, LocalDate dueDate,Priority priority){
        this.id=contadorId++;
        this.title=title;
        this.description=description;
        this.dueDate=dueDate;
        this.status=Status.TO_DO;
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

    public void show() {
        System.out.println("[ID:"+this.id+", Título:"+this.title+", Descrição:"+this.description+", Data de validade: "+this.dueDate+", status:"+this.status+", Prioridade:"+this.priority+"]");

    }
}
