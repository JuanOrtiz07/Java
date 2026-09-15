package Video1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GenerarBoletosSimples2 {
    private static final int Min = 1;
    private static final int Max = 50;
    private static final int Nums = 5;

    public static List<Integer> generarBoletos() {
        List<Integer> numeros = new ArrayList<>();
        for (int i = 0; i < Nums; i++) {
            int num = generarNumero();
            while (numeros.contains(num)) {
                num = generarNumero();
            }
            numeros.add(num);
        }
        Collections.sort(numeros);
        return numeros;
    }

    private static int generarNumero() {
        return (int) (Math.random() * (Max - Min + 1)) + Min;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            List<Integer> boletos = generarBoletos();
            System.out.print(boletos);
        }
    }
}
