package org.example.model;

public class Livro {
    private String autor;
    private String nome;
    private int id;

    public Livro(){
    }

    public Livro(String autor, String nome, int id){
        this.autor = autor;
        this.nome = nome;
        this.id = id;
    }

    public String getAutor(){
        return autor;
    }
    public String getNome(){
        return nome;
    }
    public int getId(){
        return id;
    }
}
