package exercicio8;

import java.math.BigDecimal;

public class Main {

    // --- Estratégias Concretas ---

    static class FreteSedex implements CalculoFrete {
        @Override
        public BigDecimal calcular(Pedido pedido) throws CepInvalidoException {
            validarCep(pedido.getCepDestino());
            // Lógica de cálculo do Sedex (ex: 2% do valor do pedido, mínimo R$ 15)
            BigDecimal frete = pedido.getValor().multiply(new BigDecimal("0.02"));
            return frete.compareTo(new BigDecimal("15")) < 0 ? new BigDecimal("15") : frete;
        }
    }

    static class FretePac implements CalculoFrete {
        @Override
        public BigDecimal calcular(Pedido pedido) throws CepInvalidoException {
            validarCep(pedido.getCepDestino());
            // Lógica de cálculo do PAC (ex: 1% do valor do pedido, mínimo R$ 10)
            BigDecimal frete = pedido.getValor().multiply(new BigDecimal("0.01"));
            return frete.compareTo(new BigDecimal("10")) < 0 ? new BigDecimal("10") : frete;
        }
    }

    static class RetiradaNaLoja implements CalculoFrete {
        @Override
        public BigDecimal calcular(Pedido pedido) {
            // Retirada na loja tem frete grátis
            return BigDecimal.ZERO;
        }
    }
    
    private static void validarCep(String cep) throws CepInvalidoException {
        // Simulação de validação de CEP (deve ter 8 dígitos numéricos)
        if (cep == null || !cep.matches("\\d{8}")) {
            throw new CepInvalidoException("CEP inválido: " + cep);
        }
        System.out.println("CEP " + cep + " validado para entrega.");
    }


    public static void main(String[] args) {
        System.out.println("Demonstração do Padrão Strategy para Cálculo de Frete");

        Pedido pedido = new Pedido(new BigDecimal("500.00"), "12345678");

        System.out.println("\n----------------------------------------\n");

        // 1. Calculando com Sedex
        pedido.setEstrategiaFrete(new FreteSedex());
        demonstrarCalculo(pedido, "Sedex");

        System.out.println("\n----------------------------------------\n");

        // 2. Trocando a estratégia para PAC em tempo de execução
        pedido.setEstrategiaFrete(new FretePac());
        demonstrarCalculo(pedido, "PAC");

        System.out.println("\n----------------------------------------\n");

        // 3. Trocando para Retirada na Loja
        pedido.setEstrategiaFrete(new RetiradaNaLoja());
        demonstrarCalculo(pedido, "Retirada na Loja");

        System.out.println("\n----------------------------------------\n");

        // 4. Usando uma estratégia promocional com Lambda (frete grátis acima de R$ 400)
        System.out.println("Avaliando frete com estratégia promocional (Lambda)...");
        CalculoFrete fretePromocional = p -> {
            if (p.getValor().compareTo(new BigDecimal("400")) > 0) {
                System.out.println("Promoção aplicada: Frete Grátis!");
                return BigDecimal.ZERO;
            }
            // Se não se qualificar, usa o PAC como padrão
            return new FretePac().calcular(p);
        };
        pedido.setEstrategiaFrete(fretePromocional);
        demonstrarCalculo(pedido, "Promocional");
        
        System.out.println("\n----------------------------------------\n");
        
        // 5. Testando CEP inválido
        System.out.println("Tentando calcular com CEP inválido...");
        Pedido pedidoInvalido = new Pedido(new BigDecimal("100"), "ABC");
        pedidoInvalido.setEstrategiaFrete(new FreteSedex());
        demonstrarCalculo(pedidoInvalido, "Sedex com CEP inválido");

    }

    private static void demonstrarCalculo(Pedido pedido, String nomeEstrategia) {
        try {
            BigDecimal frete = pedido.calcularFrete();
            System.out.println("Valor do frete (" + nomeEstrategia + "): R$ " + frete.setScale(2));
        } catch (CepInvalidoException | IllegalStateException e) {
            System.out.println("Falha ao calcular frete ("+ nomeEstrategia +"): " + e.getMessage());
        }
    }
}
