package br.com.projeto.exception;

public abstract class InputValidationException extends TaskServiceException{
    public InputValidationException(String message){
        super(message);
    }
}
