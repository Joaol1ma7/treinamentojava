package br.com.projeto.exception;

public class InvalidDateException extends InputValidationException{
    public InvalidDateException() {
        super("Formato de data digitado da maneira errada.");
    }
}
