package org.example.model.exceptions;

public class ClienteNaoEncontradoException extends Exception {
    public ClienteNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
