package org.example.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.model.Biblioteca;
import org.example.model.Cliente;
import org.example.model.Livro;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    private final File arquivoLivros;
    private final File arquivoClientes;
    private final ObjectMapper objectMapper;
    private final Biblioteca biblioteca;

    public Repositorio(String caminhoArquivoLivros, String caminhoArquivoClientes) {
        this.arquivoLivros = new File(caminhoArquivoLivros);
        this.arquivoClientes = new File(caminhoArquivoClientes);
        this.objectMapper = new ObjectMapper();
        this.biblioteca = new Biblioteca(carregarArquivoLivros(), carregarArquivoClientes());

        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public List<Livro> carregarArquivoLivros() {
        if (!arquivoLivros.exists() || arquivoLivros.length() == 0) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(arquivoLivros, new TypeReference<List<Livro>>() {
            });

        } catch (IOException e) {
            System.err.println("Erro ao ler e converter o arquivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Cliente> carregarArquivoClientes() {
        if (!arquivoClientes.exists() || arquivoClientes.length() == 0) {
            return new ArrayList<>();
        }

        try {
            return objectMapper.readValue(arquivoClientes, new TypeReference<List<Cliente>>() {
            });

        } catch (IOException e) {
            System.err.println("Erro ao ler e converter o arquivo JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void salvarArquivoLivros(List<Livro> livros) {
        try {
            if (arquivoLivros.getParentFile() != null && !arquivoLivros.getParentFile().exists()) {
                arquivoLivros.getParentFile().mkdirs();
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivoLivros, livros);

        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo JSON: " + e.getMessage());
        }
    }

    public void salvarArquivoClientes(List<Cliente> clientes) {
        try {
            if (arquivoClientes.getParentFile() != null && !arquivoClientes.getParentFile().exists()) {
                arquivoClientes.getParentFile().mkdirs();
            }

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivoClientes, clientes);

        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo JSON: " + e.getMessage());
        }
    }
}
