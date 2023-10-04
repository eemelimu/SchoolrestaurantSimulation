package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import simu.model.Palvelupiste;
import simu.model.OmaMoottori;

public class Visualisointi implements IVisualisointi {
    private GraphicsContext gc;
    private Canvas canvas;
    private Palvelupiste[] palvelupisteet;
    private OmaMoottori moottori;

    public Visualisointi(Canvas canvas, OmaMoottori moottori) {
        this.moottori = moottori;
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        tyhjennaNaytto();
    }

    public void paivitaVisualisointi() {
        palvelupisteet = moottori.getPalvelupisteet();
        gc.fillText(String.valueOf(palvelupisteet[0].getNykyisetAsiakkaat()), 250, 50);
    }

    public void tyhjennaNaytto() {
        gc.setFill(Color.YELLOW);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.RED);
        gc.setFont(javafx.scene.text.Font.font(20)); // Set the font size
        gc.fillText("Tavallinen jono: ", 10, 50);
        gc.fillText("0", 250, 50);

        gc.fillText("Grillijono: ", 10, 75);
        gc.fillText("0", 250, 75);

        gc.fillText("Maksupääte: ", 10, 100);
        gc.fillText("0", 250, 100);

        gc.fillText("Pöytä: ", 10, 125);
        gc.fillText("0", 250, 125);

        gc.fillText("Astioidenpalautus: ", 10, 150);
        gc.fillText("0", 250, 150);
    }

    public void uusiAsiakas() {
        // TODO implement here
    }
}