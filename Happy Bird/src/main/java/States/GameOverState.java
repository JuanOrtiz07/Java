package States;

import App.GameController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

public class GameOverState implements GameState {
    private GameController controller;

    public GameOverState(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void update(){};

    @Override
    public void render(){
        GraphicsContext gc = controller.getGraphicsContext();

        gc.fillText("Presione R para reiniciar", 300, 250);
    }
    @Override
    public void procesarTecla (KeyCode tecla){
        if (tecla == KeyCode.R){
            controller.reiniciarEntidades();

            controller.cambiarEstadoActual(new MenuState(controller));
        }
    }

}
