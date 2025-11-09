package exercicio1;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstração de uso da classe Produto");

        // Criando uma instância de Produto com valores válidos
        try {
            Produto produto1 = new Produto("Laptop", 3500.00, 10);
            System.out.println("Produto 1 criado com sucesso:");
            System.out.println("Nome: " + produto1.getNome());
            System.out.println("Preço: " + produto1.getPreco());
            System.out.println("Estoque: " + produto1.getQuantidadeEmEstoque());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar Produto 1: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // Tentando criar uma instância com preço negativo
        try {
            System.out.println("Tentando criar produto com preço negativo...");
            new Produto("Mouse", -50.00, 20);
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // Tentando criar uma instância com nome vazio
        try {
            System.out.println("Tentando criar produto com nome vazio...");
            new Produto("", 150.00, 30);
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // Tentando criar uma instância com quantidade negativa
        try {
            System.out.println("Tentando criar produto com quantidade negativa...");
            new Produto("Teclado", 200.00, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // Alterando valores de um produto existente
        try {
            System.out.println("Alterando valores de um produto existente...");
            Produto produto2 = new Produto("Monitor", 800.00, 15);
            System.out.println("Valores originais do Produto 2:");
            System.out.println("Preço: " + produto2.getPreco());
            System.out.println("Estoque: " + produto2.getQuantidadeEmEstoque());

            produto2.setPreco(950.00);
            produto2.setQuantidadeEmEstoque(12);

            System.out.println("\nValores alterados do Produto 2:");
            System.out.println("Novo Preço: " + produto2.getPreco());
            System.out.println("Novo Estoque: " + produto2.getQuantidadeEmEstoque());

            System.out.println("\nTentando alterar para um valor inválido (preço negativo)...");
            produto2.setPreco(-100);

        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada ao alterar: " + e.getMessage());
        }
    }
}
