package org.example.view;

import org.example.controller.BibliotecaController;
import org.example.model.Livro;
import org.example.model.exceptions.ClienteNaoEncontradoException;
import org.example.model.exceptions.LivroIndisponivelException;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private BibliotecaController bibliotecaController;

    public Menu(BibliotecaController controller){
        this.bibliotecaController = controller;
    }

    public void iniciarMenu() {
        Scanner scanner = new Scanner(System.in);
        String opcao = "0";

        while (!opcao.equals("7")){
            exibirMenu();

            opcao = scanner.nextLine();
            processarOpcao(opcao);

            System.out.println();

        }
    }

    public void exibirMenu() {
        System.out.print("""
            === Sistema De Biblioteca ===
            
            1 - Cadastrar Cliente
            2 - Cadastrar Livro
            3 - Exibir Livros
            4 - Empréstimo de Livro
            5 - Devolução de Livro
            6 - Remover Livro
            7 - Sair
            """);
        System.out.print("\nEscolha uma opção: ");
    }

    private void processarOpcao(String opcao) {
        Scanner scanner = new Scanner(System.in);

        switch (opcao) {
            case "1":
                System.out.println("Digite o nome do cliente: ");
                String nomeCliente = scanner.nextLine();
                System.out.println("Digite o CPF do cliente: ");
                String cpf = scanner.nextLine();

                if (bibliotecaController.cadastrarCliente(nomeCliente, cpf)){
                    System.out.println("\nCliente cadastrado com sucesso.");
                } else {
                    System.out.println("\nErro: O cliente já está cadastrado no sistema!");
                }

                break;
            case "2":
                System.out.println("Digite o nome do autor do livro: ");
                String autor = scanner.nextLine();
                System.out.println("Digite o nome do livro: ");
                String nomeLivro = scanner.nextLine();

                bibliotecaController.cadastrarLivro(autor, nomeLivro);
                System.out.println("\nLivro cadastrado com sucesso.");

                break;
            case "3":
                List<Livro> livrosBiblioteca = bibliotecaController.livrosBiblioteca();

                if (!livrosBiblioteca.isEmpty()){
                    System.out.println();
                    for (Livro livro : livrosBiblioteca) {
                        System.out.println("- " + livro.getNome() + " (Autor: " + livro.getAutor() + ")");
                    }
                } else {
                    System.out.println("\nNão há livros cadastrados.");
                }

                break;
            case "4":
                System.out.println("Digite o cpf do cliente (apenas dígitos): ");
                cpf = scanner.nextLine();
                System.out.println("Digite o nome do autor do livro: ");
                autor = scanner.nextLine();
                System.out.println("Digite o nome do livro: ");
                nomeLivro = scanner.nextLine();

                try {
                    bibliotecaController.emprestimo(cpf, autor, nomeLivro);
                    System.out.println("\nEmpréstimo realizado com sucesso.");
                } catch (ClienteNaoEncontradoException | LivroIndisponivelException e) {
                    System.out.println("\nErro no empréstimo: " + e.getMessage());
                }

                break;
            case "5":
                System.out.println("Digite o cpf do cliente: ");
                cpf = scanner.nextLine();
                System.out.println("Digite o nome do autor do livro: ");
                autor = scanner.nextLine();
                System.out.println("Digite o nome do livro: ");
                nomeLivro = scanner.nextLine();

                try {
                    bibliotecaController.devolucao(cpf, autor, nomeLivro);
                    System.out.println("\nDevolução realizada com sucesso");
                } catch (ClienteNaoEncontradoException | LivroIndisponivelException e) {
                    System.out.println("\nErro na devolução: " + e.getMessage());
                }

                break;
            case "6":
                System.out.println("Digite o nome do autor do livro: ");
                autor = scanner.nextLine();
                System.out.println("Digite o nome do livro: ");
                nomeLivro = scanner.nextLine();

                try {
                    bibliotecaController.removerLivro(autor, nomeLivro);
                    System.out.println("\nLivro removido com sucesso");
                } catch (LivroIndisponivelException e){
                    System.out.println("\nErro ao remover o livro: " + e.getMessage());
                }

                break;
            case "7":
                System.out.println("Encerrando o Programa...");

                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");

                break;
        }
    }
}
