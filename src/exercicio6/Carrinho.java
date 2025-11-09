package exercicio6;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Carrinho {
    private final List<ItemCarrinho> itens;
    private final Cupom cupom;

    public Carrinho() {
        this.itens = Collections.emptyList();
        this.cupom = null;
    }

    private Carrinho(List<ItemCarrinho> itens, Cupom cupom) {
        this.itens = Collections.unmodifiableList(new ArrayList<>(itens));
        this.cupom = cupom;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public Carrinho adicionarItem(Produto produto, int quantidade) {
        List<ItemCarrinho> novosItens = new ArrayList<>(this.itens);
        novosItens.add(new ItemCarrinho(produto, quantidade));
        return new Carrinho(novosItens, this.cupom);
    }

    public Carrinho removerItem(Produto produto) {
        List<ItemCarrinho> novosItens = new ArrayList<>();
        for (ItemCarrinho item : this.itens) {
            if (!item.getProduto().equals(produto)) {
                novosItens.add(item);
            }
        }
        return new Carrinho(novosItens, this.cupom);
    }

    public Carrinho aplicarCupom(Cupom cupom) {
        return new Carrinho(this.itens, Objects.requireNonNull(cupom));
    }

    public Dinheiro calcularTotal() {
        BigDecimal total = itens.stream()
                .map(ItemCarrinho::getSubtotal)
                .map(Dinheiro::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (cupom != null) {
            BigDecimal desconto = total.multiply(cupom.getPorcentagemDesconto().divide(new BigDecimal("100")));
            total = total.subtract(desconto);
        }

        // Assumindo BRL como moeda padrão se o carrinho estiver vazio
        Moeda moeda = itens.isEmpty() ? Moeda.BRL : itens.get(0).getProduto().getPreco().getMoeda();
        return new Dinheiro(total.setScale(2, RoundingMode.HALF_EVEN), moeda);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Carrinho:\n");
        itens.forEach(item -> sb.append("- ").append(item).append("\n"));
        if (cupom != null) {
            sb.append("Cupom aplicado: ").append(cupom.getCodigo()).append(" (").append(cupom.getPorcentagemDesconto()).append("%)\n");
        }
        sb.append("Total: ").append(calcularTotal());
        return sb.toString();
    }
}
