package App;

import States.GameState;
import States.MenuState;
import colision.Colisiones;
import Config.GameConfig;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import model.Bird;
import model.Obstaculo;
import States.MenuState;

import java.util.ArrayList;

public class GameController {

    // Dimensiones
    private final double altoVentana = GameConfig.ALTO_VENTANA;

    private final double anchoVentana = GameConfig.ANCHO_VENTANA;

    // Renderizado
    private Canvas canvas;
    private GraphicsContext gc;

    private Bird bird;
    private ArrayList<Obstaculo> obstaculos;

    // Estado
    private GameState estadoActual;

    public GameController() {

        obstaculos = new ArrayList<>();
        obstaculos.add(new Obstaculo());

        bird = new Bird();


        canvas = new Canvas(
                anchoVentana,
                altoVentana
        );
        estadoActual = new MenuState(this);

        gc = canvas.getGraphicsContext2D();

    }
    public Bird getBird(){
        return bird;
    }

    public ArrayList<Obstaculo> getObstaculos() {
        return obstaculos;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void jump() {
        bird.jump();
    }

    public void render() {
        estadoActual.render();
    }

    public void renderEscenario() {

        gc.clearRect(
                0,
                0,
                anchoVentana,
                altoVentana
        );

        // Fondo
        gc.setFill(Color.SKYBLUE);
        gc.fillRect(
                0,
                0,
                anchoVentana,
                altoVentana
        );

        // Suelo
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(
                0,
                GameConfig.POSICION_SUELO,
                anchoVentana,
                altoVentana
        );

        // Obstáculos
        for (Obstaculo o : obstaculos) {
            o.render(gc);
        }

        // Pájaro
        bird.render(gc);
    }
    public void update() {
        estadoActual.update();
    }

    public void reiniciarEntidades() {

        bird = new Bird();

        obstaculos.clear();

        obstaculos.add(new Obstaculo());
    }

    public void cambiarEstadoActual(GameState nuevoEstado) {
        this.estadoActual = nuevoEstado;
    }

    public void procesarTecla(KeyCode tecla) {
        estadoActual.procesarTecla(tecla);
    }
}
