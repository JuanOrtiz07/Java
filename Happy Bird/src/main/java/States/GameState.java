package States;

import javafx.scene.input.KeyCode;

public interface GameState {
    void update();
    void render();

    void procesarTecla(KeyCode tecla);
}
