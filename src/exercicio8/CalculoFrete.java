package exercicio8;

import java.math.BigDecimal;

@FunctionalInterface
public interface CalculoFrete {
    BigDecimal calcular(Pedido pedido) throws CepInvalidoException;
}
