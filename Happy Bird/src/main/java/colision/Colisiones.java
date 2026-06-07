package colision;

import model.Bird;
import model.Obstaculo;

public class Colisiones {

    public static boolean colisionPajaroObstaculo(Bird pajaro, Obstaculo obstaculo) {
        double pajaroX = pajaro.getPosX();
        double pajaroY = pajaro.getPosY();
        double pajaroAncho = pajaro.getAncho();
        double pajaroAlto = pajaro.getAlto();

        boolean colisionSuperior = rectangulosColisionan(pajaroX, pajaroY, pajaroAncho, pajaroAlto,
                obstaculo.getPosX(),0, obstaculo.getAnchoTuberia(), obstaculo.getAlturaSuperior());
        double yInferior = obstaculo.getAlturaSuperior() + obstaculo.getGap();

        boolean colisionInferior = rectangulosColisionan(pajaroX, pajaroY, pajaroAncho, pajaroAlto,
                obstaculo.getPosX(), yInferior, obstaculo.getAnchoTuberia(), 600 - yInferior);

        return colisionSuperior || colisionInferior;
    }
    private static boolean rectangulosColisionan(double x1, double y1, double ancho1, double alto1,
                                                 double x2, double y2, double ancho2, double alto2) {
        return x1 < x2 + ancho2 &&
                x1 + ancho1 > x2 &&
                y1 < y2 + alto2 &&
                y1 + alto1 > y2;
    }
}
