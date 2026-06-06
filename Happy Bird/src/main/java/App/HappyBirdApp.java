    package App;
    import javafx.application.Application;
    import javafx.scene.Scene;
    import javafx.scene.input.KeyCode;
    import javafx.scene.layout.Pane;
    import javafx.stage.Stage;
    import javafx.animation.AnimationTimer;

    public class HappyBirdApp extends Application {
        GameController controller = new GameController();
        Pane root = new Pane();

        @Override
        public void start(Stage primaryStage) {

            root.getChildren().add(controller.getCanvas());

            controller.render();

            Scene scene = new Scene(root, 800, 600);

            primaryStage.setTitle("Happy Bird");
            primaryStage.setScene(scene);
            primaryStage.show();

            root.requestFocus();
            AnimationTimer gameLoop = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    controller.update();
                    controller.render();
                }
            };
            gameLoop.start();
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.SPACE) {
                    controller.jump();
                }
            });
        }
    }
