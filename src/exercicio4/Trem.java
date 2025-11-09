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
