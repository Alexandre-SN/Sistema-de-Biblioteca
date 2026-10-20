package org.example.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private List<Livro> livros;
    private String cpf;
    private int id;

    public Cliente() {
        this.livros = new ArrayList<>();
    }

    public Cliente(String nome, String cpf, int id) {
        this.nome = nome;
        this.livros = new ArrayList<>();
        this.cpf = cpf;
        this.id = id;
    }

    public Livro buscarLivro(String autor, String nome) {
        for (Livro livro : livros) {
            if (livro.getAutor().equals(autor) && livro.getNome().equals(nome))
                return livro;
        }
        return null;
    }

    public String getNome() {
        return nome;
    }

    @JsonGetter("livros")
    public List<Livro> getList() {
        if (this.livros == null) return new ArrayList<>();
        return List.copyOf(this.livros);
    }

    public String getCpf() {
        return cpf;
    }

    public int getId() {
        return id;
    }

    @JsonSetter("livros")
    public void setLivros(List<Livro> list) {
        if (list == null) {
            this.livros = new ArrayList<>();
        } else {
            this.livros = new ArrayList<>(list);
        }
    }
}
