package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class BotonMenu {

    private double ancho;
    private double alto;
    private String texto;

    public BotonMenu(String texto) {
        this.texto = texto;
        this.ancho = 250;
        this.alto = 60;
    }

    public void render(GraphicsContext gc,
                       double x,
                       double y,
                       double escala) {

        double anchoEscalado = ancho * escala;
        double altoEscalado = alto * escala;

        gc.setFill(Color.DARKGRAY);
        gc.fillRoundRect(
                x,
                y,
                anchoEscalado,
                altoEscalado,
                20 * escala,
                20 * escala
        );

        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(
                x,
                y,
                anchoEscalado,
                altoEscalado,
                20 * escala,
                20 * escala
        );

        gc.setFill(Color.WHITE);

        gc.setFont(Font.font(22 * escala));

        gc.fillText(
                texto,
                x + anchoEscalado * 0.28,
                y + altoEscalado * 0.62
        );
    }

    public boolean contiene(double mouseX,
                            double mouseY,
                            double x,
                            double y,
                            double escala) {

        double anchoEscalado = ancho * escala;
        double altoEscalado = alto * escala;

        return mouseX >= x &&
                mouseX <= x + anchoEscalado &&
                mouseY >= y &&
                mouseY <= y + altoEscalado;
    }

    public double getAncho() {
        return ancho;
    }

    public double getAlto() {
        return alto;
    }

    public String getTexto() {
        return texto;
    }
}
