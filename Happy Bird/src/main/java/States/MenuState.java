package States;

import App.GameController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import model.BotonMenu;

import java.awt.*;

public class MenuState implements GameState {
    private GameController controller;
    private BotonMenu botonJugar;
    private BotonMenu botonOpciones;
    private BotonMenu botonSkins;

    public MenuState(GameController controller) {
        this.controller = controller;
        botonJugar = new BotonMenu( 0.35, 0.40, 0.30, 0.10,"Jugar");
        botonOpciones = new BotonMenu(0.35, 0.55, 0.30, 0.10, "Opciones");
        botonSkins = new BotonMenu(0.35, 0.70, 0.30, 0.10,"Skins");
    }
    @Override
    public void update() {
    }
    @Override
    public void render() {
        controller.renderEscenario();
        GraphicsContext gc = controller.getGraphicsContext();
        botonJugar.render(gc,controller.getAnchoVentana(),controller.getAltoVentana());

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
