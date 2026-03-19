package br.com.projeto.repository;

import br.com.projeto.exception.FileOperationException;
import br.com.projeto.model.OneTimeTask;
import br.com.projeto.model.RecurringTask;
import br.com.projeto.model.Task;
import br.com.projeto.model.enums.Priority;
import br.com.projeto.model.enums.Status;

import java.io.*;
import java.time.LocalDate;

import static br.com.projeto.service.TaskService.listaDeTarefas;

public class TaskRepository {
    public static void saveTasks() {
        try (PrintWriter writer = new PrintWriter("tasks.csv")) {
            for (Task t : listaDeTarefas) {
                if (t instanceof RecurringTask) {
                    RecurringTask rt = (RecurringTask) t;
                    writer.println("R;" + rt.getId() + ";" + rt.getTitle() + ";" + rt.getDescription() + ";"
                            + rt.getDueDate() + ";" + rt.getPriority() + ";" + rt.getStatus() + ";" + rt.getRecurrenceInterval());
                } else if (t instanceof OneTimeTask) {
                    writer.println("O;" + t.getId() + ";" + t.getTitle() + ";" + t.getDescription() + ";"
                            + t.getDueDate() + ";" + t.getPriority() + ";" + t.getStatus() + ";0");
                }
            }
            System.out.println("Arquivo tasks.csv salvo!");
        }  catch (IOException e) {
            throw new FileOperationException(e.getMessage());
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
        } catch (IOException e) {
            throw new FileOperationException(e.getMessage());
        }
    }
}
