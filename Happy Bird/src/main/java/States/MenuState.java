package States;

import App.GameController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import java.awt.*;

public class MenuState implements GameState {
    private GameController controller;

    public MenuState(GameController controller) {
        this.controller = controller;
    }
    @Override
    public void update() {
    }
    @Override
    public void render() {
        controller.renderEscenario();

        GraphicsContext gc = controller.getGraphicsContext();

        gc.fillText("Presione ENTER para continuar", 300, 250);
    }

    @Override
    public void procesarTecla(KeyCode tecla){

        System.out.println("MenuState recibió: " + tecla);

        if (tecla == KeyCode.ENTER){

            System.out.println("Voy a cambiar al estado JugadorState");

            controller.reiniciarEntidades();

            controller.cambiarEstadoActual(
                    new JugadorState(controller)
            );
        }
    }

}
