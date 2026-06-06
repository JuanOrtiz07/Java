package App;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameController {
    //posicion del pajaro
    private double posX=100;
    private double posY=300;

    //fisica
    private double velocidadY=0;
    private double gravedad=0.4;
    private double fuerzaSalto= -8;

    //Dimensiones
    private double alturaVentana=600;
    private double anchoVentana=800;

    //limites
    private double sueloY=550;
    private double techoY=20;

    //entrada
    private boolean saltoPajaro= false;

    private Canvas canvas;
    private GraphicsContext gc;

    public GameController() {
        canvas = new Canvas(anchoVentana, alturaVentana);
        gc = canvas.getGraphicsContext2D();
    }

    public Canvas getCanvas() {
        return canvas;
    }
    public void jump(){
        saltoPajaro=true;
    }

    public void render(){
        //Limpia la pantalla del frame anterior
        gc.clearRect(0, 0, anchoVentana, alturaVentana);

        //fondo
        gc.setFill(Color.SKYBLUE);
        gc.fillRect(0, 0, anchoVentana, alturaVentana);

        //suelo
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0,sueloY, anchoVentana, alturaVentana);

        //pajaro
        gc.setFill(Color.YELLOW);
        gc.fillOval(posX, posY, 40, 40);


    }

    public void update(){
        procesarSalto();
        aplicarLimites();
        System.out.println(posY);

    }
    private void procesarSalto() {
        if(saltoPajaro) {
            velocidadY = fuerzaSalto;
            aplicarGravedad();
            moverPajaro();
            saltoPajaro = false;
        }
    }
    private void aplicarLimites() {

        if(posY >= sueloY){
            posY = sueloY;
            velocidadY = 0;
        }

        if(posY <= techoY){
            posY = techoY;
            velocidadY = 0;
        }
    }
    private void aplicarGravedad() {
        velocidadY += gravedad;
    }

    private void moverPajaro() {
        posY += velocidadY;
    }
}
