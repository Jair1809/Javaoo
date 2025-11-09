package exercicio7;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstração de Generics com Repositório");

        // Repositório de Produtos
        System.out.println("\n--- Repositório de Produtos (ID: Long) ---");
        IRepository<Produto, Long> repoProdutos = new InMemoryRepository<>();
        repoProdutos.salvar(new Produto(1L, "TV 4K", 2500.00));
        repoProdutos.salvar(new Produto(2L, "Soundbar", 800.00));

        System.out.println("Listando todos os produtos:");
        repoProdutos.listarTodos().forEach(System.out::println);

        System.out.println("\nBuscando produto com ID 1:");
        Optional<Produto> produtoOpt = repoProdutos.buscarPorId(1L);
        produtoOpt.ifPresent(p -> System.out.println("Encontrado: " + p));

        try {
            System.out.println("\nRemovendo produto com ID 2...");
            repoProdutos.remover(2L);
            System.out.println("Produto removido.");
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nListando todos os produtos novamente:");
        repoProdutos.listarTodos().forEach(System.out::println);

        try {
            System.out.println("\nTentando remover produto com ID 99 (inexistente)...");
            repoProdutos.remover(99L);
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }


        // Repositório de Funcionários
        System.out.println("\n\n--- Repositório de Funcionários (ID: Integer) ---");
        IRepository<Funcionario, Integer> repoFuncionarios = new InMemoryRepository<>();
        repoFuncionarios.salvar(new Funcionario(101, "Maria", 7000.00));
        repoFuncionarios.salvar(new Funcionario(102, "Pedro", 4500.00));

        System.out.println("Listando todos os funcionários:");
        repoFuncionarios.listarTodos().forEach(System.out::println);

        System.out.println("\nBuscando funcionário com ID 102:");
        repoFuncionarios.buscarPorId(102).ifPresent(f -> System.out.println("Encontrado: " + f));
    }
}
