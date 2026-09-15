package Video1;

import java.util.Arrays;

public class GenerarBoletosSimples {
    private static final int Min = 1;
    private static final int Max = 50;
    private static final int Nums = 5;

    public static int[] generarBoletos() {
        int[] numeros = new int[Nums];
        for (int i = 0; i < Nums; i++) {
            numeros[i] = generarNumero();
        }
        Arrays.sort(numeros);
        return numeros;
    }

    private static int generarNumero() {
        return (int) (Math.random() * (Max - Min + 1)) + Min;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] boletos = generarBoletos();
            System.out.print(Arrays.toString(boletos));
        }
    }
}
