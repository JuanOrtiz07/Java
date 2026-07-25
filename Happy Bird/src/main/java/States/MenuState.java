package States;

import App.GameController;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import model.BotonMenu;

import java.awt.*;
import java.util.ArrayList;

public class MenuState implements GameState {
    private GameController controller;
    private ArrayList<BotonMenu> botones = new ArrayList<>();

    public MenuState(GameController controller) {
        this.controller = controller;
        botones.add(new BotonMenu("Jugar"));
        botones.add(new BotonMenu("Opciones"));
        botones.add(new BotonMenu("Skins"));
    }

    @Override
    public void update() {
    }

    @Override
    public void render() {
        controller.renderEscenario();
        renderizarBotones();
    }

    private void renderizarBotones() {
        GraphicsContext gc = controller.getGraphicsContext();
        double escala = getEscala();
        double x = getPosicionXBotones(escala);
        for (int i = 0; i < botones.size(); i++) {
            botones.get(i).render(
                    gc,
                    x,
                    getPosicionYBoton(i, escala),
                    escala
            );
        }
    }

    private double getEscala() {
        return Math.min(
                controller.getAnchoVentana() / 800.0,
                controller.getAltoVentana() / 600.0
        );
    }

    @Override
    public void procesarTecla(KeyCode tecla) {

        System.out.println("MenuState recibió: " + tecla);

        if (tecla == KeyCode.ENTER) {

            System.out.println("Voy a cambiar al estado JugadorState");

            controller.reiniciarEntidades();

            controller.cambiarEstadoActual(
                    new JugadorState(controller)
            );
        }
    }

    private double getPosicionXBotones(double escala) {
        double anchoBoton = botones.get(0).getAncho() * escala;
        return (controller.getAnchoVentana() - anchoBoton) / 2;
    }

    private double getPosicionYBoton(int indice, double escala) {
        double altoBoton = botones.get(0).getAlto() * escala;
        double yInicial = controller.getAltoVentana() * 0.40;
        double separacion = 30 * escala;
        return yInicial + indice * (altoBoton + separacion);
    }

    @Override
    public void procesarClick(double mouseX, double mouseY) {
        double escala = getEscala();
        double x = getPosicionXBotones(escala);
        for (int i = 0; i < botones.size(); i++) {
            BotonMenu boton = botones.get(i);
            double y = getPosicionYBoton(i, escala);
            if (boton.contiene(mouseX, mouseY, x, y, escala)) {
                ejecutarAccion(boton);
                break;
            }
        }
    }

    private void ejecutarAccion(BotonMenu boton) {
        switch (boton.getTexto()) {
            case "Jugar":
                controller.reiniciarEntidades();
                controller.cambiarEstadoActual(new JugadorState(controller));
                break;
            case "Opciones":
                System.out.println("AbrirOpciones");
                break;
            case "Skins":
                System.out.println("AbrirSkins");
                break;
        }
    }
}