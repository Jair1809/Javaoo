package exercicio8;

import java.math.BigDecimal;

public class Pedido {
    private final BigDecimal valor;
    private final String cepDestino;
    private CalculoFrete estrategiaFrete;

    public Pedido(BigDecimal valor, String cepDestino) {
        this.valor = valor;
        this.cepDestino = cepDestino;
    }

    public void setEstrategiaFrete(CalculoFrete estrategiaFrete) {
        this.estrategiaFrete = estrategiaFrete;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getCepDestino() {
        return cepDestino;
    }

    public BigDecimal calcularFrete() throws CepInvalidoException {
        if (estrategiaFrete == null) {
            throw new IllegalStateException("A estratégia de frete não foi definida.");
        }
        return estrategiaFrete.calcular(this);
    }
}
