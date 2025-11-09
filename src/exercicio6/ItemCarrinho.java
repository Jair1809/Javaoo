package exercicio6;

import java.math.BigDecimal;
import java.util.Objects;

public class ItemCarrinho {
    private final Produto produto;
    private final int quantidade;

    public ItemCarrinho(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }
        this.produto = Objects.requireNonNull(produto, "O produto não pode ser nulo.");
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Dinheiro getSubtotal() {
        BigDecimal subtotalValor = produto.getPreco().getValor().multiply(new BigDecimal(quantidade));
        return new Dinheiro(subtotalValor, produto.getPreco().getMoeda());
    }

    @Override
    public String toString() {
        return "ItemCarrinho{" +
                "produto=" + produto +
                ", quantidade=" + quantidade +
                ", subtotal=" + getSubtotal() +
                '}';
    }
}
