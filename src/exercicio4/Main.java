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
