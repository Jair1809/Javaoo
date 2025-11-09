package exercicio6;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstração de Imutabilidade com Carrinho de Compras");

        // Criando produtos
        Produto p1 = new Produto("Notebook", new Dinheiro(new BigDecimal("5000.00"), Moeda.BRL));
        Produto p2 = new Produto("Mouse", new Dinheiro(new BigDecimal("150.50"), Moeda.BRL));
        Produto p3 = new Produto("Teclado", new Dinheiro(new BigDecimal("250.00"), Moeda.BRL));

        // 1. Carrinho inicial (vazio)
        Carrinho carrinho1 = new Carrinho();
        System.out.println("1. Carrinho inicial:");
        System.out.println(carrinho1);
        System.out.println("\n----------------------------------------\n");

        // 2. Adicionando itens (gera um novo carrinho)
        Carrinho carrinho2 = carrinho1.adicionarItem(p1, 1);
        Carrinho carrinho3 = carrinho2.adicionarItem(p2, 2);
        System.out.println("2. Carrinho após adicionar itens:");
        System.out.println(carrinho3);
        System.out.println("Instância do carrinho1 mudou? " + (carrinho1 == carrinho3));
        System.out.println("\n----------------------------------------\n");

        // 3. Removendo um item (gera um novo carrinho)
        Carrinho carrinho4 = carrinho3.removerItem(p1);
        System.out.println("3. Carrinho após remover o Notebook:");
        System.out.println(carrinho4);
        System.out.println("Instância do carrinho3 mudou? " + (carrinho3 == carrinho4));
        System.out.println("\n----------------------------------------\n");

        // 4. Aplicando um cupom de desconto (gera um novo carrinho)
        Cupom cupom = new Cupom("PROMO15", new BigDecimal("15"));
        Carrinho carrinho5 = carrinho4.aplicarCupom(cupom);
        System.out.println("4. Carrinho após aplicar cupom de 15%:");
        System.out.println(carrinho5);
        System.out.println("\n----------------------------------------\n");
        
        // 5. Testando validações
        try {
            System.out.println("5. Tentando adicionar item com quantidade zero...");
            carrinho5.adicionarItem(p3, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }

        try {
            System.out.println("\nTentando criar cupom com desconto de 40%...");
            new Cupom("INVALIDO", new BigDecimal("40"));
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }
    }
}
