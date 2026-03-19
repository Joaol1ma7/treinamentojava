package br.com.projeto.service;

import br.com.projeto.model.OneTimeTask;
import br.com.projeto.model.RecurringTask;
import br.com.projeto.model.enums.Priority;
import br.com.projeto.model.enums.Status;
import br.com.projeto.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class TaskService {
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
                if(t.getStatus() == Status.DONE) {
                    t.show();
                    System.out.println();
                }
            }
        }else if(cond==2){
            for(Task t:listaDeTarefas){
                if(t.getStatus() == Status.TO_DO) {
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
            if (t.getId() ==id){
                t.setStatus(Status.DONE);
                System.out.println("Tarefa de ID:"+id+" foi finalizada com sucesso.");
                return;
            }
        }
    }
    public static void deleteTask(long id){
        for (Task t:listaDeTarefas) {
            if (t.getId() == id) {
                listaDeTarefas.remove(t);
                System.out.println("Tarefa de ID:" + id + " foi removida com sucesso.");
                return;
            }
        }
    }
    public static boolean verifyTaskInList(long id){
        for (Task t: TaskService.listaDeTarefas){
            if(t.getId() ==id){
                return true;
            }
        }
        return false;
    }
    public static boolean verifyTaskType(long id){
        for (Task t: TaskService.listaDeTarefas){
            if(t.getId()==id){
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
            statusEsc= Status.TO_DO;
        }else{
            statusEsc= Status.DONE;
        }
        for (Task t: TaskService.listaDeTarefas){
            if(t.getId() ==id){
              t.setTitle(title);
              t.setDescription(description);
              t.setDueDate(dueDate);
              t.setPriority(prioEsc);
              t.setStatus(statusEsc);
              if(t instanceof RecurringTask){
                  ((RecurringTask) t).setRecurrenceInterval(rec);
              }
            }
        }
    }
    public static void sortTasks(String condition){
        List<Task> listaOrdenada = new ArrayList<>(listaDeTarefas);
        if(condition.equals("D")){
            listaOrdenada.sort(Comparator.comparing(task -> task.getDueDate()));
        }else if(condition.equals("P")){
            listaOrdenada.sort(Comparator.comparing(task -> task.getPriority()));
        }
        for(Task t:listaOrdenada){
            t.show();
        }
    }

}
