package org.example;

import org.example.controller.BibliotecaController;
import org.example.repository.Repositorio;
import org.example.view.Menu;

public class Main {
    private static final String CAMINHO_ARQUIVO_LIVROS = "dados/livros.json";
    private static final String CAMINHO_ARQUIVO_CLIENTES = "dados/clientes.json";

    public static void main(String[] args) {
        Repositorio repositorio = new Repositorio(CAMINHO_ARQUIVO_LIVROS, CAMINHO_ARQUIVO_CLIENTES);

        BibliotecaController controller = new BibliotecaController(repositorio, repositorio.getBiblioteca());

        Menu menu = new Menu(controller);

        menu.iniciarMenu();
    }
}
