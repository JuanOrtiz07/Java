package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BotonMenu {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    private String texto;

    public BotonMenu(double x, double y, double ancho, double alto, String texto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.texto = texto;
    }

    public void render(GraphicsContext gc,
                       double anchoVentana,
                       double altoVentana){
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(x, y, ancho, alto);

        gc.setStroke(Color.WHITE);
        gc.strokeRoundRect(x, y, ancho, alto, 20, 20);

        gc.setFill(Color.WHITE);
        gc.fillText(texto, x +40, y+30);

    }

    public boolean contiene (double mouseX, double mouseY){
        return mouseX >= x && mouseX <= x + ancho && mouseY >= y && mouseY <= y + alto;
    }

    public String getTexto() {
        return texto;
    }
}
