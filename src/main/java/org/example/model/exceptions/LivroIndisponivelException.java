package org.example.model.exceptions;

public class LivroIndisponivelException extends Exception {
    public LivroIndisponivelException(String mensagem){
        super(mensagem);
    }
}
