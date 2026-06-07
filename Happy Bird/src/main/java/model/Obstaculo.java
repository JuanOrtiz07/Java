package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.Random;

public class Obstaculo {
    private double posX;
    private double anchoTuberia;
    private double alturaSuperior;
    private double gap;
    private Random random = new Random();
    public Obstaculo() {
        posX = 885;
        anchoTuberia = 80;
        gap = 180;
        alturaSuperior = random.nextInt(250) + 50;
    }
    public double getPosX() {
        return posX;
    }

    public double getAnchoTuberia() {
        return anchoTuberia;
    }

    public double getAlturaSuperior() {
        return alturaSuperior;
    }

    public double getGap() {
        return gap;
    }

    public Random getRandom() {
        return random;
    }

    public void render(GraphicsContext gc){
        gc.setFill(Color.GREEN);
        gc.fillRect(posX,0,anchoTuberia,alturaSuperior);
        double yInferior = alturaSuperior + gap;
        gc.fillRect(posX, yInferior, anchoTuberia, 600 - yInferior
        );
    }
    public void update(double velocidad){
        movimientoTuberia(velocidad);
    }

    private void movimientoTuberia(double velocidad){
        posX -= velocidad;
    }
}
