package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Cliente> clientes;

    public Biblioteca(List<Livro> livros, List<Cliente> clientes){
        this.livros = new ArrayList<>(livros);
        this.clientes = new ArrayList<>(clientes);
    }

    public boolean listaLivroVazia(){
        return livros.isEmpty();
    }

    public boolean listaClienteVazia(){
        return clientes.isEmpty();
    }

    public Livro buscarLivro(String autor, String nome){
        for (Livro livro : livros){
            if (livro.getAutor().equals(autor) && livro.getNome().equals(nome))
                return livro;
        }
        return null;
    }

    public Cliente buscarCliente(String cpf){
        for (Cliente cliente : clientes){
            String cpfAtual = cliente.getCpf();
            if (cpfAtual.equals(cpf))
                return cliente;
        }
        return null;
    }

    public void cadastrar(Livro livro){
            livros.add(livro);
    }

    public boolean cadastrar(Cliente cliente){
        if (!clientes.contains(cliente)){
            clientes.add(cliente);
            return true;
        }
        return false;
    }

    public boolean remover(Livro livro){
        return livros.remove(livro);
    }

    public void emprestimoLivro(Cliente cliente, Livro livro){
        if (livros.contains(livro)){
            List<Livro> livrosCliente = new ArrayList<>(cliente.getList());
            livrosCliente.add(livro);
            cliente.setLivros(livrosCliente);

            livros.remove(livro);
        }
    }

    public void devolucaoLivro(Cliente cliente, Livro livro){
        List<Livro> livrosCliente = new ArrayList<>(cliente.getList());
        if (livrosCliente.contains(livro)){
            livrosCliente.remove(livro);
            cliente.setLivros(livrosCliente);

            livros.add(livro);
        }
    }

    public List<Livro> getLivros(){
        return List.copyOf(livros);
    }
    public List<Cliente> getClientes(){
        return List.copyOf(clientes);
    }

    public void setLivros(List<Livro> livros){
        this.livros = new ArrayList<>(livros);
    }
}
