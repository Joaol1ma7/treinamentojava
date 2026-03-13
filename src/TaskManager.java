import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class TaskManager {
    public static List<Task> listaDeTarefas = new ArrayList<>();

    public static void addTask( String title, String description, LocalDate dueDate, String prio,int rec) {
        Priority prioEsc;
        if (prio.equals("A")) {
            prioEsc = Priority.HIGH;
        } else if (prio.equals("M")) {
            prioEsc = Priority.MEDIUM;
        } else {
            prioEsc = Priority.LOW;
        }
        Task t1;
        if (rec>0){
            t1=new RecurringTask(title,description,dueDate,prioEsc,rec);
        }else{
            t1=new OneTimeTask(title,description,dueDate,prioEsc);
        }
        listaDeTarefas.add(t1);
    }
    public static void listTasks(int cond){
        if(cond==1){
            for(Task t:listaDeTarefas){
                if(t.status == Status.DONE) {
                    t.show();
                    System.out.println();
                }
            }
        }else if(cond==2){
            for(Task t:listaDeTarefas){
                if(t.status == Status.TO_DO) {
                    t.show();
                    System.out.println();
                }
            }
        }else{
            for(Task t:listaDeTarefas){
                t.show();
                System.out.println();
            }
        }

    }
    public static void finishTask(long id){
        for (Task t:listaDeTarefas){
            if (t.id==id){
                t.status=Status.DONE;
                System.out.println("Tarefa de ID:"+id+" foi finalizada com sucesso.");
                return;
            }
        }
    }
    public static void deleteTask(long id){
        for (Task t:listaDeTarefas) {
            if (t.id == id) {
                listaDeTarefas.remove(t);
                System.out.println("Tarefa de ID:" + id + " foi removida com sucesso.");
                return;
            }
        }
    }
    public static boolean verifyTaskInList(long id){
        for (Task t:TaskManager.listaDeTarefas){
            if(t.id==id){
                return true;
            }
        }
        return false;
    }
    public static boolean verifyTaskType(long id){
        for (Task t:TaskManager.listaDeTarefas){
            if(t.id==id){
                if(t instanceof RecurringTask){
                    return true;
                    //vdd se for instancia de recurring falso se for instancia de onetimetask
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    public static void updateTask(long id,String title, String description, LocalDate dueDate, String prio, int rec,String status) {
        Priority prioEsc;
        Status statusEsc;
        if (prio.equals("A")) {
            prioEsc = Priority.HIGH;
        } else if (prio.equals("M")) {
            prioEsc = Priority.MEDIUM;
        } else {
            prioEsc = Priority.LOW;
        }
        if(status.equals("T")){
            statusEsc=Status.TO_DO;
        }else{
            statusEsc=Status.DONE;
        }
        for (Task t:TaskManager.listaDeTarefas){
            if(t.id==id){
              t.title=title;
              t.description=description;
              t.dueDate=dueDate;
              t.priority=prioEsc;
              t.status=statusEsc;
              if(t instanceof RecurringTask){
                  ((RecurringTask) t).recurrenceInterval=rec;
              }
            }
        }
    }
    public static void sortTasks(String condition){
        List<Task> listaOrdenada = new ArrayList<>(listaDeTarefas);
        if(condition.equals("D")){
            listaOrdenada.sort(Comparator.comparing(task -> task.dueDate));
        }else if(condition.equals("P")){
            listaOrdenada.sort(Comparator.comparing(task -> task.priority));
        }
        for(Task t:listaOrdenada){
            t.show();
        }
    }
    public static void saveTasks() {
        try (PrintWriter writer = new PrintWriter("tasks.csv")) {
            for (Task t : listaDeTarefas) {
                if (t instanceof RecurringTask) {
                    RecurringTask rt = (RecurringTask) t;
                    writer.println("R;" + rt.id + ";" + rt.title + ";" + rt.description + ";"
                            + rt.dueDate + ";" + rt.priority + ";" + rt.status + ";" + rt.recurrenceInterval);
                } else if (t instanceof OneTimeTask) {
                    writer.println("O;" + t.id + ";" + t.title + ";" + t.description + ";"
                            + t.dueDate + ";" + t.priority + ";" + t.status + ";0");
                }
            }
            System.out.println("Arquivo tasks.csv salvo!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo. " + e.getMessage());
        }
    }
    public static void loadTasks() {
        File arquivo = new File("tasks.csv");
        if (!arquivo.exists()) {
            System.out.println("Nenhum arquivo encontrado.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                String tipo = dados[0];
                long id = Long.parseLong(dados[1]);
                String title = dados[2];
                String description = dados[3];
                LocalDate dueDate = LocalDate.parse(dados[4]);
                Priority prio = Priority.valueOf(dados[5]);
                Status status = Status.valueOf(dados[6]);
                int rec = Integer.parseInt(dados[7]);
                if (tipo.equals("R")) {
                    listaDeTarefas.add(new RecurringTask(id, title, description, dueDate, prio, status, rec));
                } else if (tipo.equals("O")) {
                    listaDeTarefas.add(new OneTimeTask(id, title, description, dueDate, prio, status));
                }
            }
            System.out.println("Tarefas carregadas com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
