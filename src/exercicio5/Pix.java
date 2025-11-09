package exercicio5;

import java.math.BigDecimal;

public class Pix extends FormaPagamento {
    private String chavePix;

    public Pix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void validarPagamento() throws PagamentoInvalidoException {
        // Simulação de validação: chave PIX não pode ser nula ou vazia
        if (chavePix == null || chavePix.trim().isEmpty()) {
            throw new PagamentoInvalidoException("Chave PIX inválida.");
        }
        System.out.println("Chave PIX validada com sucesso.");
    }
}
