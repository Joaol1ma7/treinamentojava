package br.com.projeto;

import br.com.projeto.repository.TaskRepository;
import br.com.projeto.service.TaskService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static Scanner sc=new Scanner(System.in);
    public static void add(){
                System.out.println("Digite o título da tarefa:");
                String title=sc.nextLine();
                System.out.println("Digite a descrição da tarefa:");
                String description=sc.nextLine();
                LocalDate dueDate;
                while (true) {
                    System.out.println("Digite a data de vencimento da tarefa (AAAA-MM-DD):");
                    String data = sc.nextLine();
                    try {
                        dueDate = LocalDate.parse(data);
                        if (dueDate.isBefore(LocalDate.now())) {
                        System.out.println("Data inválida. São permitidas apenas datas posteriores à hoje.");
                        } else {
                            break;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato inválido.Digite no formato demonstrado.");
                    }
                }
                System.out.println("Digite a prioridade da tarefa:(A,M,B)");
                String prio =sc.nextLine().toUpperCase();
                while(!prio.equals("A") && !prio.equals("M") && !prio.equals("B")){
                    System.out.println("Digite apenas A, M ou B");
                    prio =sc.nextLine().toUpperCase();
                }
                System.out.println("Essa tarefa será recorrente? Digite 'S'(Sim) ou 'N'(Não)");
                String cond=sc.nextLine().toUpperCase();
                while(!cond.equals("N") && !cond.equals("S")){
                    System.out.println("Digite apenas 'S' ou 'N'");
                    cond=sc.nextLine().toUpperCase();
                }
                if(cond.equals("S")){
                    int rec;
                    while(true){
                        try{
                            System.out.println("Digite o número de dias para ela ficar disponível novamente.");
                            String recStr=sc.nextLine();
                            rec=Integer.parseInt(recStr);
                            break;
                        }catch(NumberFormatException e){
                            System.out.println("Você digitou uma letra ou símbolo. Apenas números são permitidos.");
                        }
                    }
                    TaskService.addTask(title,description,dueDate,prio,rec);
                }else if(cond.equals("N")){
                    TaskService.addTask(title,description,dueDate,prio,0);
                }

    }
    public static void list(){
                if (TaskService.listaDeTarefas.isEmpty()){
                    System.out.println("Não há nenhuma tarefa para ser listada.");
                }else {
                    System.out.println("Você deseja filtrar por DONE ou TO_DO?'D', 'T' ou 'N'");
                    String f=sc.nextLine().toUpperCase();
                    while(!f.equals("D") && !f.equals("T") && !f.equals("N")){
                        System.out.println("Digite apenas 'T', 'D' ou 'N'");
                        f=sc.nextLine().toUpperCase();
                    }
                    if (f.equals("D")){
                        TaskService.listTasks(1);
                    }else if(f.equals("T")){
                        TaskService.listTasks(2);
                    }else{
                        TaskService.listTasks(0);
                    }
                }

    }
    public static void done(){
                if (TaskService.listaDeTarefas.isEmpty()){
                    System.out.println("Não há nenhuma tarefa para ser finalizada.");
                }else {
                    TaskService.listTasks(0);
                    long id;
                    while(true){
                        try{
                            System.out.println("Digite o ID da task que você deseja finalizar:");
                            String idStr=sc.nextLine();
                            id=Long.parseLong(idStr);
                            break;
                        }catch(NumberFormatException e){
                            System.out.println("Você digitou uma letra ou símbolo. Apenas números são permitidos.");
                        }
                    }
                    id = getId(id);
                    TaskService.finishTask(id);
                }

    }
    public static void delete(){
                if (TaskService.listaDeTarefas.isEmpty()){
                    System.out.println("Não há nenhuma tarefa para ser removida.");
                }else{
                    TaskService.listTasks(0);
                    long id;
                    while(true){
                        try{
                            System.out.println("Digite o ID da task que você deseja excluir:");
                            String idStr=sc.nextLine();
                            id=Long.parseLong(idStr);
                            break;
                        }catch(NumberFormatException e){
                            System.out.println("Você digitou uma letra ou símbolo. Apenas números são permitidos.");
                        }
                    }
                    id = getId(id);

                    TaskService.deleteTask(id);
                }
    }

    private static long getId(long id) {//MÉTODO Q O INTELLIJ CRIOU PRA TIRAR REPETIÇÃO DO CODIGO
        while(!TaskService.verifyTaskInList(id)){
            while(true){
                try{
                    System.out.println("Digite um ID de task válido:");
                    String idStr=sc.nextLine();
                    id=Long.parseLong(idStr);
                    break;
                }catch(NumberFormatException e){
                    System.out.println("Você digitou uma letra ou símbolo. Apenas números são permitidos.");
                }
            }
        }
        return id;
    }

    public static void update(){
                if (TaskService.listaDeTarefas.isEmpty()){
                    System.out.println("Não há nenhuma tarefa para ser atualizada.");
                }else {
                    TaskService.listTasks(0);
                    long id;
                    while(true){
                        try{
                            System.out.println("Digite o ID da task que você deseja atualizar:");
                            String idStr=sc.nextLine();
                            id=Long.parseLong(idStr);
                            break;
                        }catch(NumberFormatException e){
                            System.out.println("Você digitou uma letra ou símbolo. Apenas números são permitidos.");
                        }
                    }
                    id=getId(id);
                    System.out.println("Digite o novo título da tarefa:");
                    String title=sc.nextLine();
                    System.out.println("Digite a nova descrição da tarefa:");
                    String description=sc.nextLine();
                    LocalDate dueDate;
                    while (true) {
                        System.out.println("Digite a data de vencimento da tarefa (AAAA-MM-DD):");
                        String data = sc.nextLine();
                        try {
                            dueDate = LocalDate.parse(data);
                            if (dueDate.isBefore(LocalDate.now())) {
                                System.out.println("Data inválida. São permitidas apenas datas posteriores à hoje.");
                            } else {
                                break;
                            }
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato inválido.Digite no formato demonstrado.");
                        }
                    }
                    System.out.println("Digite a nova prioridade da tarefa:(A,M,B)");
                    String prio =sc.nextLine().toUpperCase();
                    while(!prio.equals("A") && !prio.equals("M") && !prio.equals("B")){
                        System.out.println("Digite apenas A, M ou B.");
                        prio=sc.nextLine().toUpperCase();
                    }
                    System.out.println("Escreva o novo estado da tarefa:'T' ou 'D'.");
                    String status=sc.nextLine().toUpperCase();
                    while(!status.equals("T") && !status.equals("D")){
                        System.out.println("Digite apenas 'T' ou 'D':");
                        status=sc.nextLine().toUpperCase();
                    }
                    if(TaskService.verifyTaskType(id)){
                        int rec;
                        while(true){
                            try{
                                System.out.println("Digite o número de dias para ela ficar disponível novamente.");
                                String recStr=sc.nextLine();
                                rec=Integer.parseInt(recStr);
                                break;
                            }catch(NumberFormatException e){
                                System.out.println("Você digitou uma letra ou símbolo. Apenas números são permitidos.");
                            }
                        }
                        TaskService.updateTask(id,title,description,dueDate,prio,rec,status);
                    }else{
                        TaskService.updateTask(id,title,description,dueDate,prio,0,status);
                    }
                }
    }
    public static void sort(){
                if (TaskService.listaDeTarefas.isEmpty()){
                    System.out.println("Não há nenhuma tarefa para ser ordenada.");
                }else {
                    System.out.println("Você deseja ordenar por data ou prioridade? 'D' ou 'P'");
                    String condition=sc.nextLine().toUpperCase();
                    while(!condition.equals("D") && !condition.equals("P")){
                        System.out.println("Digite apenas 'D' ou 'P':");
                        condition=sc.nextLine().toUpperCase();
                    }
                    TaskService.sortTasks(condition);
                }
    }
    public static void main(String[] args) {
        System.out.println("Seja bem-vindo ao Task Manager!");
        TaskRepository.loadTasks();
        label:
        while(true){
            System.out.println("Digite uma das opções para realizar uma ação:");
            System.out.println("ADD LIST SORT UPDATE DONE DELETE QUIT");
            String func=sc.nextLine().toUpperCase();
            System.out.println(func);
            switch (func) {
                case "ADD":
                    Main.add();
                    break;
                case "LIST":
                    Main.list();
                    break;
                case "SORT":
                    Main.sort();
                    break;
                case "UPDATE":
                    Main.update();
                    break;
                case "DONE":
                    Main.done();
                    break;
                case "DELETE":
                    Main.delete();
                    break;
                case "QUIT":
                    TaskRepository.saveTasks();
                    break label;
            }
        }
    }
}
