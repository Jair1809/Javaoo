package exercicio7;

public class Produto implements Identificavel<Long> {
    private final Long id;
    private final String nome;
    private double preco;

    public Produto(Long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{"
                + "id=" + id +
                ", nome='" + nome + "'" +
                ", preco=" + preco +
                '}';
    }
}
