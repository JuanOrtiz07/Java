package App;

import colision.Colisiones;
import Config.GameConfig;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Bird;
import model.Obstaculo;

import java.util.ArrayList;

public class GameController {

    // Dimensiones
    private final double altoVentana = GameConfig.ALTO_VENTANA;

    private final double anchoVentana = GameConfig.ANCHO_VENTANA;
    // Dificultad
    private double velocidadTuberias = GameConfig.VELOCIDAD_INICIAL_TUBERIAS;

    // Entidades
    private Bird bird;
    private ArrayList<Obstaculo> obstaculos;

    // Renderizado
    private Canvas canvas;
    private GraphicsContext gc;

    // Tiempo
    private long tiempoInicio = System.currentTimeMillis();

    // Estado
    private boolean gameOver = false;

    public GameController() {

        canvas = new Canvas(
                anchoVentana,
                altoVentana
        );

        gc = canvas.getGraphicsContext2D();

        bird = new Bird();

        obstaculos = new ArrayList<>();
        obstaculos.add(new Obstaculo());
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void jump() {
        bird.jump();
    }

    public void render() {

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
                GameConfig.POSICION_SUELO,
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

        if (gameOver) {
            return;
        }

        bird.update();

        moverTuberias();

        generarTuberias();

        eliminarTuberias();

        verificarColisiones();
    }
    private void moverTuberias() {
        long segundos = (System.currentTimeMillis() - tiempoInicio) / 1000;
        velocidadTuberias = Math.min(
                GameConfig.VELOCIDAD_MAXIMA_TUBERIAS,
                GameConfig.VELOCIDAD_INICIAL_TUBERIAS + segundos / 20.0
        );
        for (Obstaculo o : obstaculos) {
            o.update(velocidadTuberias);
        }
    }
    private void generarTuberias() {

        if (obstaculos.isEmpty()) {
            obstaculos.add(new Obstaculo());
            return;
        }

        long segundos =
                (System.currentTimeMillis() - tiempoInicio) / 1000;

        double distanciaEntreTuberias =
                Math.max(GameConfig.DISTANCIA_MINIMA_TUBERIAS,
                        GameConfig.DISTANCIA_INICIAL_TUBERIAS - segundos * 2
                );

        Obstaculo ultima = obstaculos.get(obstaculos.size() - 1);
        if (ultima.getPosX() < anchoVentana - distanciaEntreTuberias) {
            obstaculos.add(new Obstaculo());
        }
    }
    private void eliminarTuberias() {
        for (int i = 0; i < obstaculos.size(); i++) {
            Obstaculo o = obstaculos.get(i);
            if (o.getPosX() + o.getAnchoTuberia() < 0) {
                obstaculos.remove(i);
                i--;
            }
        }
    }
    private void verificarColisiones() {
        for (Obstaculo o : obstaculos) {
            if (Colisiones.colisionPajaroObstaculo(bird, o)) {
                gameOver = true;
                break;
            }
        }
    }
}
