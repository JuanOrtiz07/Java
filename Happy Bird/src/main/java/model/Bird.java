package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bird {

    private double posX = 100;
    private double posY = 300;

    private double ancho = 40;
    private double alto = 40;

    private double velocidadY = 0;

    private double gravedad = 0.4;
    private double fuerzaSalto = -8;

    private double sueloY = 550;
    private double techoY = 20;

    public double getVelocidadY() {
        return velocidadY;
    }

    public double getGravedad() {
        return gravedad;
    }

    public double getFuerzaSalto() {
        return fuerzaSalto;
    }

    public double getSueloY() {
        return sueloY;
    }

    public double getTechoY() {
        return techoY;
    }
    public double getAncho() {
        return ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void update() {

        velocidadY += gravedad;

        posY += velocidadY;

        aplicarLimites();
    }

    public void jump() {
        velocidadY = fuerzaSalto;
    }

    private void aplicarLimites() {

        if (posY >= sueloY) {
            posY = sueloY;
            velocidadY = 0;
        }

        if (posY <= techoY) {
            posY = techoY;
            velocidadY = 0;
        }
    }

    public void render(GraphicsContext gc) {

        gc.setFill(Color.YELLOW);

        gc.fillOval(posX, posY, ancho, alto);
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getRadio() {
        return 20;
    }
}
