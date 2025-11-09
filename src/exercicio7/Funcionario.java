package exercicio7;

public class Funcionario implements Identificavel<Integer> {
    private final Integer id;
    private final String nome;
    private double salario;

    public Funcionario(Integer id, String nome, double salario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + "'" +
                ", salario=" + salario +
                '}';
    }
}
