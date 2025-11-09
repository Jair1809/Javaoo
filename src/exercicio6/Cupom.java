package exercicio6;

import java.math.BigDecimal;

public class Cupom {
    private final String codigo;
    private final BigDecimal porcentagemDesconto;

    public Cupom(String codigo, BigDecimal porcentagemDesconto) {
        if (porcentagemDesconto.compareTo(BigDecimal.ZERO) < 0 || porcentagemDesconto.compareTo(new BigDecimal("30")) > 0) {
            throw new IllegalArgumentException("A porcentagem de desconto do cupom deve estar entre 0 e 30.");
        }
        this.codigo = codigo;
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPorcentagemDesconto() {
        return porcentagemDesconto;
    }
}
