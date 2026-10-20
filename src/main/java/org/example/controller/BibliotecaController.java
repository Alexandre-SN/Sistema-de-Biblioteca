package org.example.controller;

import org.example.model.Biblioteca;
import org.example.model.Cliente;
import org.example.model.Livro;
import org.example.model.exceptions.ClienteNaoEncontradoException;
import org.example.model.exceptions.LivroIndisponivelException;
import org.example.repository.Repositorio;

import java.util.List;

public class BibliotecaController {
    private Biblioteca biblioteca;
    private Repositorio repositorio;

    public BibliotecaController(Repositorio repositorio, Biblioteca biblioteca) {
        this.repositorio = repositorio;
        this.biblioteca = biblioteca;
    }

    public List<Livro> livrosBiblioteca() {
        return biblioteca.getLivros();
    }

    public boolean cadastrarCliente(String nomeCliente, String cpf) {
        Cliente cliente = biblioteca.buscarCliente(cpf);
        boolean concluiu = false;

        if (cliente == null) {
            int id = 1;

            for (Cliente ultimoCliente : biblioteca.getClientes()){
                id = ultimoCliente.getId() + 1;
            }

            Cliente novoCliente = new Cliente(nomeCliente, cpf, id);
            concluiu = biblioteca.cadastrar(novoCliente);
        }
        repositorio.salvarArquivoClientes(biblioteca.getClientes());
        return concluiu;
    }

    public void cadastrarLivro(String autor, String nomeLivro) {
        int id = 1;

        for (Livro ultimoLivro : biblioteca.getLivros()){
            id = ultimoLivro.getId() + 1;
        }

        Livro novoLivro = new Livro(autor, nomeLivro, id);
        biblioteca.cadastrar(novoLivro);

        repositorio.salvarArquivoLivros(biblioteca.getLivros());
    }

    public void emprestimo(String cpf, String autor, String nomeLivro) throws ClienteNaoEncontradoException, LivroIndisponivelException {
        Livro livro = biblioteca.buscarLivro(autor, nomeLivro);
        Cliente cliente = biblioteca.buscarCliente(cpf);

        if (cliente == null){
            throw new ClienteNaoEncontradoException("Não há um cliente com o CPF " + cpf + " cadastrado no sistema.");
        }

        if (livro == null) {
            throw new LivroIndisponivelException("O livro solicitado está indisponível.");
        }

        biblioteca.emprestimoLivro(cliente, livro);

        repositorio.salvarArquivoClientes(biblioteca.getClientes());
        repositorio.salvarArquivoLivros(biblioteca.getLivros());
    }

    public void devolucao(String cpf, String autor, String nomeLivro) throws ClienteNaoEncontradoException, LivroIndisponivelException {
        Cliente cliente = biblioteca.buscarCliente(cpf);

        if (cliente == null){
            throw new ClienteNaoEncontradoException("Não há um cliente com o CPF " + cpf + " cadastrado no sistema.");
        }

        Livro livro = cliente.buscarLivro(autor, nomeLivro);
        if (livro == null) {
            throw new LivroIndisponivelException("O cliente não possui o livro informado.");
        }

        biblioteca.devolucaoLivro(cliente, livro);

        repositorio.salvarArquivoClientes(biblioteca.getClientes());
        repositorio.salvarArquivoLivros(biblioteca.getLivros());
    }

    public void removerLivro(String autor, String nomeLivro) throws LivroIndisponivelException {
        Livro livro = biblioteca.buscarLivro(autor, nomeLivro);

        if (livro == null) {
            throw new LivroIndisponivelException("Não existe o livro " + nomeLivro + " do autor " + autor + ".");
        }

        biblioteca.remover(livro);

        repositorio.salvarArquivoLivros(biblioteca.getLivros());
    }
}
