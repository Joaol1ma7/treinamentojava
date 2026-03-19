package br.com.projeto.exception;

public class FileOperationException extends TaskServiceException{
    public FileOperationException(String message){
        super("Erro no banco de dados:"+message);
    }
}
