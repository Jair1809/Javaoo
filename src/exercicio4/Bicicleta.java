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
