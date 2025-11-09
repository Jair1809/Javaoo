package exercicio3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstração de Herança com Funcionários");

        // Criando funcionários
        Gerente gerente = new Gerente("Carlos", new BigDecimal("10000.00"));
        Desenvolvedor dev1 = new Desenvolvedor("Ana", new BigDecimal("6000.00"));
        Desenvolvedor dev2 = new Desenvolvedor("João", new BigDecimal("5500.00"));

        // Criando uma lista de funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(gerente);
        funcionarios.add(dev1);
        funcionarios.add(dev2);

        System.out.println("\n----------------------------------------\n");

        // Calculando e exibindo o bônus de cada funcionário
        for (Funcionario f : funcionarios) {
            System.out.println("Funcionário: " + f.getNome());
            System.out.println("Salário: " + f.getSalario());
            System.out.println("Bônus: " + f.calcularBonus());
            System.out.println("---");
        }

        System.out.println("\n----------------------------------------\n");

        // Testando a validação de salário
        try {
            System.out.println("Tentando criar funcionário com salário negativo...");
            new Gerente("Invalido", new BigDecimal("-1000"));
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }
    }
}
