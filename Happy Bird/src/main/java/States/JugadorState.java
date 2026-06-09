package States;

import App.GameController;
import Config.GameConfig;
import colision.Colisiones;
import javafx.scene.input.KeyCode;
import model.Bird;
import model.Obstaculo;

import java.util.ArrayList;

public class JugadorState implements GameState {

    private long tiempoInicio = System.currentTimeMillis();
    private double velocidadTuberias = GameConfig.VELOCIDAD_INICIAL_TUBERIAS;
    private final double anchoVentana = GameConfig.ANCHO_VENTANA;
    private GameController controller;

    public JugadorState(GameController controller) {
        this.controller = controller;

    }
    @Override
    public void update() {

        controller.getBird().update();

        moverTuberias();

        generarTuberias();

        eliminarTuberias();

        verificarColisiones();
    }

    @Override
    public void render() {
        controller.renderEscenario();
    }

    @Override
    public void procesarTecla(KeyCode tecla){
        if(tecla == KeyCode.SPACE){
            controller.getBird().jump();
        }
    }

    private void moverTuberias() {
        long segundos = (System.currentTimeMillis() - tiempoInicio) / 1000;
        velocidadTuberias = Math.min(
                GameConfig.VELOCIDAD_MAXIMA_TUBERIAS,
                GameConfig.VELOCIDAD_INICIAL_TUBERIAS + segundos / 20.0
        );
        for (Obstaculo o : controller.getObstaculos()) {
            o.update(velocidadTuberias);
        }
    }
    private void generarTuberias() {

        if (controller.getObstaculos().isEmpty()) {
            controller.getObstaculos().add(new Obstaculo());
            return;
        }

        long segundos =
                (System.currentTimeMillis() - tiempoInicio) / 1000;

        double distanciaEntreTuberias =
                Math.max(GameConfig.DISTANCIA_MINIMA_TUBERIAS,
                        GameConfig.DISTANCIA_INICIAL_TUBERIAS - segundos * 2
                );

        Obstaculo ultima = controller.getObstaculos().get(controller.getObstaculos().size() - 1);
        if (ultima.getPosX() < anchoVentana - distanciaEntreTuberias) {
            controller.getObstaculos().add(new Obstaculo());
        }
    }
    private void eliminarTuberias() {
        for (int i = 0; i < controller.getObstaculos().size(); i++) {
            Obstaculo o = controller.getObstaculos().get(i);
            if (o.getPosX() + o.getAnchoTuberia() < 0) {
                controller.getObstaculos().remove(i);
                i--;
            }
        }
    }
    private void verificarColisiones() {
        for (Obstaculo o : controller.getObstaculos()) {
            if (Colisiones.colisionPajaroObstaculo(controller.getBird(), o)) {
                controller.cambiarEstadoActual(new GameOverState(controller));
            }
        }
    }
}
