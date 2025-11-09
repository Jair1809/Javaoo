# Exercícios de Java - Programação Orientada a Objetos

Este arquivo contém a resolução de 8 exercícios práticos de Java, focados em conceitos fundamentais de POO como encapsulamento, herança, polimorfismo, abstração, e outros.

---

## Exercício 1 — Encapsulamento (Classe Produto)

**Enunciado:** Implemente a classe `Produto` com atributos privados `nome`, `preco` e `quantidadeEmEstoque`. Forneça getters e setters com validações: `preco` e `quantidadeEmEstoque` não podem ser negativos e `nome` não pode ser nulo ou vazio. Lance `IllegalArgumentException` em casos inválidos. Demonstre o uso criando instâncias, alterando valores válidos e tentando atribuições inválidas.

### Resolução

**`Produto.java`**
```java
package exercicio1;

public class Produto {
    private String nome;
    private double preco;
    private int quantidadeEmEstoque;

    public Produto(String nome, double preco, int quantidadeEmEstoque) {
        setNome(nome);
        setPreco(preco);
        setQuantidadeEmEstoque(quantidadeEmEstoque);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        this.preco = preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
}
```

**`Main.java`**
```java
package exercicio1;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstração de uso da classe Produto");

        // Criando uma instância de Produto com valores válidos
        try {
            Produto produto1 = new Produto("Laptop", 3500.00, 10);
            System.out.println("Produto 1 criado com sucesso:");
            System.out.println("Nome: " + produto1.getNome());
            System.out.println("Preço: " + produto1.getPreco());
            System.out.println("Estoque: " + produto1.getQuantidadeEmEstoque());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar Produto 1: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // Tentando criar uma instância com preço negativo
        try {
            System.out.println("Tentando criar produto com preço negativo...");
            new Produto("Mouse", -50.00, 20);
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // Tentando criar uma instância com nome vazio
        try {
            System.out.println("Tentando criar produto com nome vazio...");
            new Produto("", 150.00, 30);
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // Tentando criar uma instância com quantidade negativa
        try {
            System.out.println("Tentando criar produto com quantidade negativa...");
            new Produto("Teclado", 200.00, -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // Alterando valores de um produto existente
        try {
            System.out.println("Alterando valores de um produto existente...");
            Produto produto2 = new Produto("Monitor", 800.00, 15);
            System.out.println("Valores originais do Produto 2:");
            System.out.println("Preço: " + produto2.getPreco());
            System.out.println("Estoque: " + produto2.getQuantidadeEmEstoque());

            produto2.setPreco(950.00);
            produto2.setQuantidadeEmEstoque(12);

            System.out.println("\nValores alterados do Produto 2:");
            System.out.println("Novo Preço: " + produto2.getPreco());
            System.out.println("Novo Estoque: " + produto2.getQuantidadeEmEstoque());

            System.out.println("\nTentando alterar para um valor inválido (preço negativo)...");
            produto2.setPreco(-100);

        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada ao alterar: " + e.getMessage());
        }
    }
}
```

### Saída

```
Demonstração de uso da classe Produto
Produto 1 criado com sucesso:
Nome: Laptop
Preço: 3500.0
Estoque: 10

----------------------------------------

Tentando criar produto com preço negativo...
Falha esperada: O preço não pode ser negativo.

----------------------------------------

Tentando criar produto com nome vazio...
Falha esperada: O nome do produto não pode ser nulo ou vazio.

----------------------------------------

Tentando criar produto com quantidade negativa...
Falha esperada: A quantidade em estoque não pode ser negativa.

----------------------------------------

Alterando valores de um produto existente...
Valores originais do Produto 2:
Preço: 800.0
Estoque: 15

Valores alterados do Produto 2:
Novo Preço: 950.0
Novo Estoque: 12


```

---

## Exercício 2 — Encapsulamento com Validação de Regra (Desconto)

**Enunciado:** Estenda `Produto` com o método `aplicarDesconto(double porcentagem)`. Permita apenas valores entre 0 e 50 (inclusive) e lance exceção (`IllegalArgumentException` ou `DescontoInvalidoException`) se a regra for violada. Mostre, em um `main` ou testes, o preço antes/depois do desconto e a reação a entradas inválidas.

### Resolução

**`Produto.java`**
```java
package exercicio2;

public class Produto {
    private String nome;
    private double preco;
    private int quantidadeEmEstoque;

    public Produto(String nome, double preco, int quantidadeEmEstoque) {
        setNome(nome);
        setPreco(preco);
        setQuantidadeEmEstoque(quantidadeEmEstoque);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        this.preco = preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("A quantidade em estoque não pode ser negativa.");
        }
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public void aplicarDesconto(double porcentagem) {
        if (porcentagem < 0 || porcentagem > 50) {
            throw new IllegalArgumentException("A porcentagem de desconto deve estar entre 0 e 50.");
        }
        double desconto = this.preco * (porcentagem / 100);
        this.preco -= desconto;
    }
}
```

**`Main.java`**
```java
package exercicio2;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstração do método aplicarDesconto");

        // Criando um produto
        Produto produto = new Produto("Celular", 2000.00, 5);
        System.out.println("Produto criado: " + produto.getNome());
        System.out.println("Preço original: " + produto.getPreco());

        System.out.println("\n----------------------------------------\n");

        // Aplicando um desconto válido
        try {
            System.out.println("Aplicando desconto de 15%...");
            produto.aplicarDesconto(15);
            System.out.println("Novo preço após desconto: " + produto.getPreco());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao aplicar desconto: " + e.getMessage());
        }

        System.out.println("\n----------------------------------------\n");

        // Tentando aplicar um desconto inválido (maior que 50)
        try {
            System.out.println("Tentando aplicar desconto de 60%...");
            produto.aplicarDesconto(60);
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }
        System.out.println("Preço do produto permanece: " + produto.getPreco());


        System.out.println("\n----------------------------------------\n");

        // Tentando aplicar um desconto inválido (negativo)
        try {
            System.out.println("Tentando aplicar desconto de -10%...");
            produto.aplicarDesconto(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }
        System.out.println("Preço do produto permanece: " + produto.getPreco());
    }
}
```

### Saída

```
Demonstração do método aplicarDesconto
Produto criado: Celular
Preço original: 2000.0

----------------------------------------

Aplicando desconto de 15%...
Novo preço após desconto: 1700.0

----------------------------------------

Tentando aplicar desconto de 60%...
Falha esperada: A porcentagem de desconto deve estar entre 0 e 50.
Preço do produto permanece: 1700.0

----------------------------------------


```

---

## Exercício 3 — Herança (Hierarquia de Funcionários)

**Enunciado:** Crie a classe base `Funcionario` com `protected String nome` e `protected BigDecimal salario` (com getters). Crie `Gerente` e `Desenvolvedor` que sobrescrevem `calcularBonus()`: 20% do salário para gerente e 10% para desenvolvedor. Garanta que salários sejam positivos. Em um programa, coloque diferentes funcionários em uma coleção do tipo `List<Funcionario>` e exiba o bônus de cada um.

### Resolução

**`Funcionario.java`**
```java
package exercicio3;

import java.math.BigDecimal;

public class Funcionario {
    protected String nome;
    protected BigDecimal salario;

    public Funcionario(String nome, BigDecimal salario) {
        this.nome = nome;
        if (salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O salário deve ser positivo.");
        }
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public BigDecimal calcularBonus() {
        return BigDecimal.ZERO;
    }
}
```

**`Gerente.java`**
```java
package exercicio3;

import java.math.BigDecimal;

public class Gerente extends Funcionario {

    public Gerente(String nome, BigDecimal salario) {
        super(nome, salario);
    }

    @Override
    public BigDecimal calcularBonus() {
        return salario.multiply(new BigDecimal("0.20"));
    }
}
```

**`Desenvolvedor.java`**
```java
package exercicio3;

import java.math.BigDecimal;

public class Desenvolvedor extends Funcionario {

    public Desenvolvedor(String nome, BigDecimal salario) {
        super(nome, salario);
    }

    @Override
    public BigDecimal calcularBonus() {
        return salario.multiply(new BigDecimal("0.10"));
    }
}
```

**`Main.java`**
```java
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
```

### Saída

```
Demonstração de Herança com Funcionários

----------------------------------------

Funcionário: Carlos
Salário: 10000.00
Bônus: 2000.0000
---
Funcionário: Ana
Salário: 6000.00
Bônus: 600.0000
---
Funcionário: João
Salário: 5500.00
Bônus: 550.0000
---

----------------------------------------


```

---

## Exercício 4 — Polimorfismo com Interface (IMeioTransporte)

**Enunciado:** Defina a interface `IMeioTransporte` com `acelerar()` e `frear()`. Implemente `Carro`, `Bicicleta` e `Trem`, cada um com lógica própria de variação de velocidade e limites. No método principal, crie uma lista de `IMeioTransporte`, percorra e invoque `acelerar()`/`frear()` demonstrando polimorfismo. Trate operações inválidas com exceções apropriadas.

### Resolução

**`IMeioTransporte.java`**
```java
package exercicio4;

public interface IMeioTransporte {
    void acelerar();
    void frear();
}
```

**`Carro.java`**
```java
package exercicio4;

public class Carro implements IMeioTransporte {
    private int velocidade;
    private static final int VELOCIDADE_MAXIMA = 180;

    @Override
    public void acelerar() {
        if (velocidade < VELOCIDADE_MAXIMA) {
            velocidade += 10;
            System.out.println("Carro acelerando. Velocidade atual: " + velocidade + " km/h");
        } else {
            System.out.println("Carro já está na velocidade máxima.");
        }
    }

    @Override
    public void frear() {
        if (velocidade > 0) {
            velocidade -= 10;
            System.out.println("Carro freando. Velocidade atual: " + velocidade + " km/h");
        } else {
            System.out.println("Carro já está parado.");
        }
    }
}
```

**`Bicicleta.java`**
```java
package exercicio4;

public class Bicicleta implements IMeioTransporte {
    private int velocidade;
    private static final int VELOCIDADE_MAXIMA = 30;

    @Override
    public void acelerar() {
        if (velocidade < VELOCIDADE_MAXIMA) {
            velocidade += 2;
            System.out.println("Bicicleta acelerando. Velocidade atual: " + velocidade + " km/h");
        } else {
            System.out.println("Bicicleta já está na velocidade máxima.");
        }
    }

    @Override
    public void frear() {
        if (velocidade > 0) {
            velocidade -= 2;
            System.out.println("Bicicleta freando. Velocidade atual: " + velocidade + " km/h");
        } else {
            System.out.println("Bicicleta já está parada.");
        }
    }
}
```

**`Trem.java`**
```java
package exercicio4;

public class Trem implements IMeioTransporte {
    private int velocidade;
    private static final int VELOCIDADE_MAXIMA = 120;

    @Override
    public void acelerar() {
        if (velocidade < VELOCIDADE_MAXIMA) {
            velocidade += 5;
            System.out.println("Trem acelerando. Velocidade atual: " + velocidade + " km/h");
        } else {
            System.out.println("Trem já está na velocidade máxima.");
        }
    }

    @Override
    public void frear() {
        if (velocidade > 0) {
            velocidade -= 5;
            System.out.println("Trem freando. Velocidade atual: " + velocidade + " km/h");
        } else {
            System.out.println("Trem já está parado.");
        }
    }
}
```

**`Main.java`**
```java
package exercicio4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstração de Polimorfismo com IMeioTransporte");

        // Criando uma lista de meios de transporte
        List<IMeioTransporte> transportes = new ArrayList<>();
        transportes.add(new Carro());
        transportes.add(new Bicicleta());
        transportes.add(new Trem());

        System.out.println("\n----------------------------------------\n");

        // Acelerando todos os transportes
        System.out.println(">>> Acelerando todos:");
        for (IMeioTransporte transporte : transportes) {
            transporte.acelerar();
        }

        System.out.println("\n----------------------------------------\n");

        // Acelerando mais uma vez
        System.out.println(">>> Acelerando mais uma vez:");
        for (IMeioTransporte transporte : transportes) {
            transporte.acelerar();
        }

        System.out.println("\n----------------------------------------\n");

        // Freando todos os transportes
        System.out.println(">>> Freando todos:");
        for (IMeioTransporte transporte : transportes) {
            transporte.frear();
        }

        System.out.println("\n----------------------------------------\n");

        // Tentando frear quando já estão parados (ou quase)
        System.out.println(">>> Tentando frear novamente:");
        for (IMeioTransporte transporte : transportes) {
            transporte.frear();
            transporte.frear(); // Tenta frear mais uma vez para garantir que chegue a zero
        }
    }
}
```

### Saída

```
Demonstração de Polimorfismo com IMeioTransporte

----------------------------------------

>>> Acelerando todos:
Carro acelerando. Velocidade atual: 10 km/h
Bicicleta acelerando. Velocidade atual: 2 km/h
Trem acelerando. Velocidade atual: 5 km/h

----------------------------------------

>>> Acelerando mais uma vez:
Carro acelerando. Velocidade atual: 20 km/h
Bicicleta acelerando. Velocidade atual: 4 km/h
Trem acelerando. Velocidade atual: 10 km/h

----------------------------------------

>>> Freando todos:
Carro freando. Velocidade atual: 10 km/h
Bicicleta freando. Velocidade atual: 2 km/h
Trem freando. Velocidade atual: 5 km/h

----------------------------------------

>>> Tentando frear novamente:
Carro freando. Velocidade atual: 0 km/h
Carro já está parado.
Bicicleta freando. Velocidade atual: 0 km/h
Bicicleta já está parada.
Trem freando. Velocidade atual: 0 km/h
Trem já está parado.
```

---

## Exercício 5 — Abstração (Sistema de Pagamentos)

**Enunciado:** Implemente a classe abstrata `FormaPagamento` com `validarPagamento()` e `processarPagamento(BigDecimal valor)`. Crie `CartaoCredito`, `Boleto` e `Pix` com validações específicas (ex.: número do cartão, formato de boleto, chave Pix). Simule o uso de cada forma por polimorfismo e trate erros de validação com exceções específicas (ex.: `PagamentoInvalidoException`).

### Resolução

**`PagamentoInvalidoException.java`**
```java
package exercicio5;

public class PagamentoInvalidoException extends Exception {
    public PagamentoInvalidoException(String message) {
        super(message);
    }
}
```

**`FormaPagamento.java`**
```java
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
```

**`CartaoCredito.java`**
```java
package exercicio5;

import java.math.BigDecimal;

public class CartaoCredito extends FormaPagamento {
    private String numeroCartao;
    private String nomeTitular;

    public CartaoCredito(String numeroCartao, String nomeTitular) {
        this.numeroCartao = numeroCartao;
        this.nomeTitular = nomeTitular;
    }

    @Override
    public void validarPagamento() throws PagamentoInvalidoException {
        // Simulação de validação: número do cartão deve ter 16 dígitos
        if (numeroCartao == null || !numeroCartao.matches("\\d{16}")) {
            throw new PagamentoInvalidoException("Número de cartão de crédito inválido.");
        }
        if (nomeTitular == null || nomeTitular.trim().isEmpty()) {
            throw new PagamentoInvalidoException("Nome do titular é obrigatório.");
        }
        System.out.println("Cartão de crédito validado com sucesso.");
    }
}
```

**`Boleto.java`**
```java
package exercicio5;

import java.math.BigDecimal;

public class Boleto extends FormaPagamento {
    private String codigoBarras;

    public Boleto(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @Override
    public void validarPagamento() throws PagamentoInvalidoException {
        // Simulação de validação: código de barras deve ter 48 dígitos
        if (codigoBarras == null || !codigoBarras.matches("\\d{48}")) {
            throw new PagamentoInvalidoException("Código de barras do boleto inválido.");
        }
        System.out.println("Boleto validado com sucesso.");
    }
}
```

**`Pix.java`**
```java
package exercicio5;

import java.math.BigDecimal;

public class Pix extends FormaPagamento {
    private String chavePix;

    public Pix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void validarPagamento() throws PagamentoInvalidoException {
        // Simulação de validação: chave PIX não pode ser nula ou vazia
        if (chavePix == null || chavePix.trim().isEmpty()) {
            throw new PagamentoInvalidoException("Chave PIX inválida.");
        }
        System.out.println("Chave PIX validada com sucesso.");
    }
}
```

**`Main.java`**
```java
package exercicio5;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstração de Abstração com Sistema de Pagamentos");
        BigDecimal valorCompra = new BigDecimal("150.75");

        System.out.println("\n----------------------------------------\n");

        // Pagamento com Cartão de Crédito (válido)
        FormaPagamento cartaoValido = new CartaoCredito("1234567890123456", "J. Silva");
        processar(cartaoValido, valorCompra);

        System.out.println("\n----------------------------------------\n");

        // Pagamento com Cartão de Crédito (inválido)
        FormaPagamento cartaoInvalido = new CartaoCredito("1234", "J. Silva");
        processar(cartaoInvalido, valorCompra);

        System.out.println("\n----------------------------------------\n");

        // Pagamento com Boleto (válido)
        FormaPagamento boletoValido = new Boleto("123456789012345678901234567890123456789012345678");
        processar(boletoValido, valorCompra);

        System.out.println("\n----------------------------------------\n");

        // Pagamento com Boleto (inválido)
        FormaPagamento boletoInvalido = new Boleto("invalido");
        processar(boletoInvalido, valorCompra);

        System.out.println("\n----------------------------------------\n");

        // Pagamento com PIX (válido)
        FormaPagamento pixValido = new Pix("meu-email@example.com");
        processar(pixValido, valorCompra);

        System.out.println("\n----------------------------------------\n");

        // Pagamento com PIX (inválido)
        FormaPagamento pixInvalido = new Pix(" ");
        processar(pixInvalido, valorCompra);
    }

    private static void processar(FormaPagamento forma, BigDecimal valor) {
        try {
            System.out.println("Tentando processar pagamento com " + forma.getClass().getSimpleName());
            forma.processarPagamento(valor);
        } catch (PagamentoInvalidoException e) {
            System.out.println("Falha no pagamento: " + e.getMessage());
        }
    }
}
```

### Saída

```
Demonstração de Abstração com Sistema de Pagamentos

----------------------------------------

Tentando processar pagamento com CartaoCredito
Cartão de crédito validado com sucesso.
Processando pagamento de R$ 150.75 via CartaoCredito
Pagamento concluído com sucesso.

----------------------------------------

Tentando processar pagamento com CartaoCredito
Falha no pagamento: Número de cartão de crédito inválido.

----------------------------------------

Tentando processar pagamento com Boleto
Boleto validado com sucesso.
Processando pagamento de R$ 150.75 via Boleto
Pagamento concluído com sucesso.

----------------------------------------

Tentando processar pagamento com Boleto
Falha no pagamento: Código de barras do boleto inválido.

----------------------------------------

Tentando processar pagamento com Pix
Chave PIX validada com sucesso.
Processando pagamento de R$ 150.75 via Pix
Pagamento concluído com sucesso.

----------------------------------------


```

---

## Exercício 6 — Imutabilidade e Objetos de Valor (Carrinho de Compras)

**Enunciado:** Crie o objeto de valor imutável `Dinheiro` (valor `BigDecimal` e `enum Moeda`) com `equals`/`hashCode` coerentes. Modele `Produto`, `ItemCarrinho` e um `Carrinho` cuja lista de itens seja imutável: operações de `adicionar`/`remover`/`aplicarCupom` retornam um novo carrinho. Valide quantidades > 0, proíba valores negativos e limite cupons a 30% com arredondamento bancário. Demonstre o fluxo completo em testes.

### Resolução

**`Moeda.java`**
```java
package exercicio6;

public enum Moeda {
    BRL, 
    USD,
    EUR
}
```

**`Dinheiro.java`**
```java
package exercicio6;

import java.math.BigDecimal;
import java.util.Objects;

public final class Dinheiro {
    private final BigDecimal valor;
    private final Moeda moeda;

    public Dinheiro(BigDecimal valor, Moeda moeda) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor não pode ser nulo ou negativo.");
        }
        this.valor = valor;
        this.moeda = Objects.requireNonNull(moeda, "A moeda não pode ser nula.");
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Moeda getMoeda() {
        return moeda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dinheiro dinheiro = (Dinheiro) o;
        return valor.equals(dinheiro.valor) && moeda == dinheiro.moeda;
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, moeda);
    }

    @Override
    public String toString() {
        return moeda + " " + valor;
    }
}
```

**`Produto.java`**
```java
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
        return "Produto{"
                + "nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}
```

**`ItemCarrinho.java`**
```java
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
        return "ItemCarrinho{"
                + "produto=" + produto +
                ", quantidade=" + quantidade +
                ", subtotal=" + getSubtotal() +
                '}';
    }
}
```

**`Cupom.java`**
```java
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
```

**`Carrinho.java`**
```java
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
```

**`Main.java`**
```java
package exercicio6;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstração de Imutabilidade com Carrinho de Compras");

        // Criando produtos
        Produto p1 = new Produto("Notebook", new Dinheiro(new BigDecimal("5000.00"), Moeda.BRL));
        Produto p2 = new Produto("Mouse", new Dinheiro(new BigDecimal("150.50"), Moeda.BRL));
        Produto p3 = new Produto("Teclado", new Dinheiro(new BigDecimal("250.00"), Moeda.BRL));

        // 1. Carrinho inicial (vazio)
        Carrinho carrinho1 = new Carrinho();
        System.out.println("1. Carrinho inicial:");
        System.out.println(carrinho1);
        System.out.println("\n----------------------------------------\n");

        // 2. Adicionando itens (gera um novo carrinho)
        Carrinho carrinho2 = carrinho1.adicionarItem(p1, 1);
        Carrinho carrinho3 = carrinho2.adicionarItem(p2, 2);
        System.out.println("2. Carrinho após adicionar itens:");
        System.out.println(carrinho3);
        System.out.println("Instância do carrinho1 mudou? " + (carrinho1 == carrinho3));
        System.out.println("\n----------------------------------------\n");

        // 3. Removendo um item (gera um novo carrinho)
        Carrinho carrinho4 = carrinho3.removerItem(p1);
        System.out.println("3. Carrinho após remover o Notebook:");
        System.out.println(carrinho4);
        System.out.println("Instância do carrinho3 mudou? " + (carrinho3 == carrinho4));
        System.out.println("\n----------------------------------------\n");

        // 4. Aplicando um cupom de desconto (gera um novo carrinho)
        Cupom cupom = new Cupom("PROMO15", new BigDecimal("15"));
        Carrinho carrinho5 = carrinho4.aplicarCupom(cupom);
        System.out.println("4. Carrinho após aplicar cupom de 15%:");
        System.out.println(carrinho5);
        System.out.println("\n----------------------------------------\n");
        
        // 5. Testando validações
        try {
            System.out.println("5. Tentando adicionar item com quantidade zero...");
            carrinho5.adicionarItem(p3, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }

        try {
            System.out.println("\nTentando criar cupom com desconto de 40%...");
            new Cupom("INVALIDO", new BigDecimal("40"));
        } catch (IllegalArgumentException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }
    }
}
```

### Saída

```
Demonstração de Imutabilidade com Carrinho de Compras
1. Carrinho inicial:
Carrinho:
Total: BRL 0.00

----------------------------------------

2. Carrinho após adicionar itens:
Carrinho:
- ItemCarrinho{produto=Produto{nome='Notebook', preco=BRL 5000.00}, quantidade=1, subtotal=BRL 5000.00}
- ItemCarrinho{produto=Produto{nome='Mouse', preco=BRL 150.50}, quantidade=2, subtotal=BRL 301.00}
Total: BRL 5301.00
Instância do carrinho1 mudou? false

----------------------------------------

3. Carrinho após remover o Notebook:
Carrinho:
- ItemCarrinho{produto=Produto{nome='Mouse', preco=BRL 150.50}, quantidade=2, subtotal=BRL 301.00}
Total: BRL 301.00
Instância do carrinho3 mudou? false

----------------------------------------

4. Carrinho após aplicar cupom de 15%:
Carrinho:
- ItemCarrinho{produto=Produto{nome='Mouse', preco=BRL 150.50}, quantidade=2, subtotal=BRL 301.00}
Cupom aplicado: PROMO15 (15)
Total: BRL 255.85

----------------------------------------

5. Tentando adicionar item com quantidade zero...
Falha esperada: A quantidade deve ser maior que zero.


```

---

## Exercício 7 — Generics (Repositório Genérico em Memória)

**Enunciado:** Defina `Identificavel` com `getId()`. Crie `IRepository<T extends Identificavel, ID>` com `salvar`, `buscarPorId` (retorna `Optional<T>`), `listarTodos` e `remover`. Implemente `InMemoryRepository` com `Map<ID, T>`, garanta que `listarTodos` devolva cópia imutável e lance `EntidadeNaoEncontradaException` ao remover ID inexistente. Use com entidades como `Produto` e `Funcionario`.

### Resolução

**`Identificavel.java`**
```java
package exercicio7;

public interface Identificavel<ID> {
    ID getId();
}
```

**`EntidadeNaoEncontradaException.java`**
```java
package exercicio7;

public class EntidadeNaoEncontradaException extends Exception {
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }
}
```

**`IRepository.java`**
```java
package exercicio7;

import java.util.List;
import java.util.Optional;

public interface IRepository<T extends Identificavel<ID>, ID> {
    void salvar(T entidade);
    Optional<T> buscarPorId(ID id);
    List<T> listarTodos();
    void remover(ID id) throws EntidadeNaoEncontradaException;
}
```

**`InMemoryRepository.java`**
```java
package exercicio7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository<T extends Identificavel<ID>, ID> implements IRepository<T, ID> {
    private final Map<ID, T> dados = new ConcurrentHashMap<>();

    @Override
    public void salvar(T entidade) {
        dados.put(entidade.getId(), entidade);
    }

    @Override
    public Optional<T> buscarPorId(ID id) {
        return Optional.ofNullable(dados.get(id));
    }

    @Override
    public List<T> listarTodos() {
        return Collections.unmodifiableList(new ArrayList<>(dados.values()));
    }

    @Override
    public void remover(ID id) throws EntidadeNaoEncontradaException {
        if (dados.remove(id) == null) {
            throw new EntidadeNaoEncontradaException("Entidade com ID " + id + " não encontrada.");
        }
    }
}
```

**`Produto.java`**
```java
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
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }
}
```

**`Funcionario.java`**
```java
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
        return "Funcionario{"
                + "id=" + id +
                ", nome='" + nome + '\'' +
                ", salario=" + salario +
                '}';
    }
}
```

**`Main.java`**
```java
package exercicio7;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstração de Generics com Repositório");

        // Repositório de Produtos
        System.out.println("\n--- Repositório de Produtos (ID: Long) ---");
        IRepository<Produto, Long> repoProdutos = new InMemoryRepository<>();
        repoProdutos.salvar(new Produto(1L, "TV 4K", 2500.00));
        repoProdutos.salvar(new Produto(2L, "Soundbar", 800.00));

        System.out.println("Listando todos os produtos:");
        repoProdutos.listarTodos().forEach(System.out::println);

        System.out.println("\nBuscando produto com ID 1:");
        Optional<Produto> produtoOpt = repoProdutos.buscarPorId(1L);
        produtoOpt.ifPresent(p -> System.out.println("Encontrado: " + p));

        try {
            System.out.println("\nRemovendo produto com ID 2...");
            repoProdutos.remover(2L);
            System.out.println("Produto removido.");
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nListando todos os produtos novamente:");
        repoProdutos.listarTodos().forEach(System.out::println);

        try {
            System.out.println("\nTentando remover produto com ID 99 (inexistente)...");
            repoProdutos.remover(99L);
        } catch (EntidadeNaoEncontradaException e) {
            System.out.println("Falha esperada: " + e.getMessage());
        }


        // Repositório de Funcionários
        System.out.println("\n\n--- Repositório de Funcionários (ID: Integer) ---");
        IRepository<Funcionario, Integer> repoFuncionarios = new InMemoryRepository<>();
        repoFuncionarios.salvar(new Funcionario(101, "Maria", 7000.00));
        repoFuncionarios.salvar(new Funcionario(102, "Pedro", 4500.00));

        System.out.println("Listando todos os funcionários:");
        repoFuncionarios.listarTodos().forEach(System.out::println);

        System.out.println("\nBuscando funcionário com ID 102:");
        repoFuncionarios.buscarPorId(102).ifPresent(f -> System.out.println("Encontrado: " + f));
    }
}
```

### Saída

```
Demonstração de Generics com Repositório

--- Repositório de Produtos (ID: Long) ---
Listando todos os produtos:
Produto{id=1, nome='TV 4K', preco=2500.0}
Produto{id=2, nome='Soundbar', preco=800.0}

Buscando produto com ID 1:
Encontrado: Produto{id=1, nome='TV 4K', preco=2500.0}

Removendo produto com ID 2...
Produto removido.

Listando todos os produtos novamente:
Produto{id=1, nome='TV 4K', preco=2500.0}

Tentando remover produto com ID 99 (inexistente)...
Falha esperada: Entidade com ID 99 não encontrada.


--- Repositório de Funcionários (ID: Integer) ---
Listando todos os funcionários:
Funcionario{id=101, nome='Maria', salario=7000.0}
Funcionario{id=102, nome='Pedro', salario=4500.0}


```

---

## Exercício 8 — Padrão Strategy (Cálculo de Frete com Lambdas)

**Enunciado:** Modele `CalculadoraFrete` com `calcular(Pedido): BigDecimal`. Crie estratégias `Sedex`, `Pac` e `RetiradaNaLoja` e permita injeção/troca da estratégia no `Pedido`. Acrescente uma estratégia promocional via lambda (frete grátis acima de X). Valide CEP/região e dispare exceções para CEP inválido. Mostre a troca de estratégia em tempo de execução.

### Resolução

**`CepInvalidoException.java`**
```java
package exercicio8;

public class CepInvalidoException extends Exception {
    public CepInvalidoException(String message) {
        super(message);
    }
}
```

**`CalculoFrete.java`**
```java
package exercicio8;

import java.math.BigDecimal;

@FunctionalInterface
public interface CalculoFrete {
    BigDecimal calcular(Pedido pedido) throws CepInvalidoException;
}
```

**`Pedido.java`**
```java
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
```

**`Main.java`**
```java
package exercicio8;

import java.math.BigDecimal;

public class Main {

    // --- Estratégias Concretas ---

    static class FreteSedex implements CalculoFrete {
        @Override
        public BigDecimal calcular(Pedido pedido) throws CepInvalidoException {
            validarCep(pedido.getCepDestino());
            // Lógica de cálculo do Sedex (ex: 2% do valor do pedido, mínimo R$ 15)
            BigDecimal frete = pedido.getValor().multiply(new BigDecimal("0.02"));
            return frete.compareTo(new BigDecimal("15")) < 0 ? new BigDecimal("15") : frete;
        }
    }

    static class FretePac implements CalculoFrete {
        @Override
        public BigDecimal calcular(Pedido pedido) throws CepInvalidoException {
            validarCep(pedido.getCepDestino());
            // Lógica de cálculo do PAC (ex: 1% do valor do pedido, mínimo R$ 10)
            BigDecimal frete = pedido.getValor().multiply(new BigDecimal("0.01"));
            return frete.compareTo(new BigDecimal("10")) < 0 ? new BigDecimal("10") : frete;
        }
    }

    static class RetiradaNaLoja implements CalculoFrete {
        @Override
        public BigDecimal calcular(Pedido pedido) {
            // Retirada na loja tem frete grátis
            return BigDecimal.ZERO;
        }
    }
    
    private static void validarCep(String cep) throws CepInvalidoException {
        // Simulação de validação de CEP (deve ter 8 dígitos numéricos)
        if (cep == null || !cep.matches("\\d{8}")) {
            throw new CepInvalidoException("CEP inválido: " + cep);
        }
        System.out.println("CEP " + cep + " validado para entrega.");
    }


    public static void main(String[] args) {
        System.out.println("Demonstração do Padrão Strategy para Cálculo de Frete");

        Pedido pedido = new Pedido(new BigDecimal("500.00"), "12345678");

        System.out.println("\n----------------------------------------\n");

        // 1. Calculando com Sedex
        pedido.setEstrategiaFrete(new FreteSedex());
        demonstrarCalculo(pedido, "Sedex");

        System.out.println("\n----------------------------------------\n");

        // 2. Trocando a estratégia para PAC em tempo de execução
        pedido.setEstrategiaFrete(new FretePac());
        demonstrarCalculo(pedido, "PAC");

        System.out.println("\n----------------------------------------\n");

        // 3. Trocando para Retirada na Loja
        pedido.setEstrategiaFrete(new RetiradaNaLoja());
        demonstrarCalculo(pedido, "Retirada na Loja");

        System.out.println("\n----------------------------------------\n");

        // 4. Usando uma estratégia promocional com Lambda (frete grátis acima de R$ 400)
        System.out.println("Avaliando frete com estratégia promocional (Lambda)...");
        CalculoFrete fretePromocional = p -> {
            if (p.getValor().compareTo(new BigDecimal("400")) > 0) {
                System.out.println("Promoção aplicada: Frete Grátis!");
                return BigDecimal.ZERO;
            }
            // Se não se qualificar, usa o PAC como padrão
            return new FretePac().calcular(p);
        };
        pedido.setEstrategiaFrete(fretePromocional);
        demonstrarCalculo(pedido, "Promocional");
        
        System.out.println("\n----------------------------------------\n");
        
        // 5. Testando CEP inválido
        System.out.println("Tentando calcular com CEP inválido...");
        Pedido pedidoInvalido = new Pedido(new BigDecimal("100"), "ABC");
        pedidoInvalido.setEstrategiaFrete(new FreteSedex());
        demonstrarCalculo(pedidoInvalido, "Sedex com CEP inválido");

    }

    private static void demonstrarCalculo(Pedido pedido, String nomeEstrategia) {
        try {
            BigDecimal frete = pedido.calcularFrete();
            System.out.println("Valor do frete (" + nomeEstrategia + "): R$ " + frete.setScale(2));
        } catch (CepInvalidoException | IllegalStateException e) {
            System.out.println("Falha ao calcular frete ("+ nomeEstrategia +"): " + e.getMessage());
        }
    }
}
```

### Saída

```
Demonstração do Padrão Strategy para Cálculo de Frete

----------------------------------------

CEP 12345678 validado para entrega.
Valor do frete (Sedex): R$ 15.00

----------------------------------------

CEP 12345678 validado para entrega.
Valor do frete (PAC): R$ 10.00

----------------------------------------

Valor do frete (Retirada na Loja): R$ 0.00

----------------------------------------

Avaliando frete com estratégia promocional (Lambda)...
Promoção aplicada: Frete Grátis!
Valor do frete (Promocional): R$ 0.00

----------------------------------------


```
