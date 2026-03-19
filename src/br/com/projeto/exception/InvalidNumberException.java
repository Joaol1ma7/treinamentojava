package br.com.projeto.exception;

public class InvalidNumberException extends InputValidationException{
    public InvalidNumberException(){
        super("Formato inválido.Você digitou letras ou símbolos. São aceitos apenas números.");
    }
}
