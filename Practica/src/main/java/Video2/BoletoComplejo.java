package Video2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BoletoComplejo {
    public static final int MinN= 1;
    public static final int MaxN= 50;
    public static final int Nums = 6;
    public static final int MinR = 1;
    public static final int MaxR = 10;

    private int reintegro;
    private int[] numeros;

    public BoletoComplejo() {
        numeros = generarBoleto(MinN,MaxN,Nums);
        reintegro = generarNumero(MinR,MaxR);
    }

    private static int generarNumero(int minN, int maxN) {
        return (int) (Math.random()*(maxN-minN+1)) + minN;
    }
    private static int[] generarBoleto(int minN, int maxN, int Nums) {
        List<Integer> boletos = new ArrayList<>();
        for (int i=0; i<Nums;i++) {
            int numero = generarNumero(minN,maxN);
            while(boletos.contains(numero)) {
                numero = generarNumero(minN,maxN);
            }
            boletos.add(numero);
        }
        Collections.sort(boletos);
        return boletos.stream().mapToInt(Integer::intValue).toArray();
    }

    @Override
    public String toString() {
        return "N: " + Arrays.toString(numeros) + " R: " + reintegro;
    }

}
