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
