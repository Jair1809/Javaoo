package exercicio6;

import java.util.Objects;

public class Produto {
    private final String nome;
    private final Dinheiro preco;

    public Produto(String nome, Dinheiro preco) {
        this.nome = Objects.requireNonNull(nome, "O nome do produto não pode ser nulo.");
        this.preco = Objects.requireNonNull(preco, "O preço do produto não pode ser nulo.");
    }

    public String getNome() {
        return nome;
    }

    public Dinheiro getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "nome='" + nome + "'" +
                ", preco=" + preco +
                '}';
    }
}
