package exercicio5;

import java.math.BigDecimal;

public abstract class FormaPagamento {
    
    public abstract void validarPagamento() throws PagamentoInvalidoException;
    
    public void processarPagamento(BigDecimal valor) throws PagamentoInvalidoException {
        validarPagamento();
        System.out.println("Processando pagamento de R$ " + valor + " via " + this.getClass().getSimpleName());
        // Lógica de processamento real iria aqui
        System.out.println("Pagamento concluído com sucesso.");
    }
}
