package exercicio5;

import java.math.BigDecimal;

public class Boleto extends FormaPagamento {
    private String codigoBarras;

    public Boleto(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @Override
    public void validarPagamento() throws PagamentoInvalidoException {
        // Simulação de validação: código de barras deve ter 48 dígitos
        if (codigoBarras == null || !codigoBarras.matches("\\d{48}")) {
            throw new PagamentoInvalidoException("Código de barras do boleto inválido.");
        }
        System.out.println("Boleto validado com sucesso.");
    }
}
