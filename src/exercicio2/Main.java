package exercicio2;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstração do método aplicarDesconto");

        // Criando um produto
        Produto produto = new Produto("Celular", 2000.00, 5);
        System.out.println("Produto criado: " + produto.getNome());
        System.out.println("Preço original: " + produto.getPreco());

        System.out.println("\n----------------------------------------\n");

        // Aplicando um desconto válido
        try {
            System.out.println("Aplicando desconto de 15%...");
            produto.aplicarDesconto(15);
            System.out.println("Novo preço após desconto: " + produto.getPreco());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao aplicar desconto: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // Tentando aplicar um desconto inválido (maior que 50)
        try {
            System.out.println("Tentando aplicar desconto de 60%...");
            produto.aplicarDesconto(60);
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }
        System.out.println("Preço do produto permanece: " + produto.getPreco());


        System.out.println("\n----------------------------------------\n");

        // Tentando aplicar um desconto inválido (negativo)
        try {
            System.out.println("Tentando aplicar desconto de -10%...");
            produto.aplicarDesconto(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }
        System.out.println("Preço do produto permanece: " + produto.getPreco());
    }
}
