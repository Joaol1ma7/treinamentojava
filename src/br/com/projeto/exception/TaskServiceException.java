package br.com.projeto.exception;

public abstract class TaskServiceException extends RuntimeException{
    public TaskServiceException(String message){
        super(message);
    }

}
