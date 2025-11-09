package exercicio5;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstração de Abstração com Sistema de Pagamentos");
        BigDecimal valorCompra = new BigDecimal("150.75");

        System.out.println("\n----------------------------------------\n");

        // Pagamento com Cartão de Crédito (válido)
        FormaPagamento cartaoValido = new CartaoCredito("1234567890123456", "J. Silva");
        processar(cartaoValido, valorCompra);

        System.out.println("\n----------------------------------------\n");

        // Pagamento com Cartão de Crédito (inválido)
        FormaPagamento cartaoInvalido = new CartaoCredito("1234", "J. Silva");
        processar(cartaoInvalido, valorCompra);

        System.out.println("\n----------------------------------------\n");

        // Pagamento com Boleto (válido)
        FormaPagamento boletoValido = new Boleto("123456789012345678901234567890123456789012345678");
        processar(boletoValido, valorCompra);

        System.out.println("\n----------------------------------------\n");

        // Pagamento com Boleto (inválido)
        FormaPagamento boletoInvalido = new Boleto("invalido");
        processar(boletoInvalido, valorCompra);

        System.out.println("\n----------------------------------------\n");

        // Pagamento com PIX (válido)
        FormaPagamento pixValido = new Pix("meu-email@example.com");
        processar(pixValido, valorCompra);

        System.out.println("\n----------------------------------------\n");

        // Pagamento com PIX (inválido)
        FormaPagamento pixInvalido = new Pix(" ");
        processar(pixInvalido, valorCompra);
    }

    private static void processar(FormaPagamento forma, BigDecimal valor) {
        try {
            System.out.println("Tentando processar pagamento com " + forma.getClass().getSimpleName());
            forma.processarPagamento(valor);
        } catch (PagamentoInvalidoException e) {
            System.out.println("Falha no pagamento: " + e.getMessage());
        }
    }
}
