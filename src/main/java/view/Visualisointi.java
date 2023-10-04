package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import simu.model.Palvelupiste;
import simu.model.OmaMoottori;

import java.util.ArrayList;

public class Visualisointi implements IVisualisointi {
    private GraphicsContext gc;
    private Canvas canvas;

    public Visualisointi(Canvas canvas) {
        this.canvas = canvas;
        this.gc = this.canvas.getGraphicsContext2D();
        tyhjennaNaytto();
    }

    public void tyhjennaNaytto() {
        gc.setFill(Color.YELLOW);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.RED);
        gc.setFont(javafx.scene.text.Font.font(20)); // Set the font size
        gc.fillText("Tavallinen jono: ", 10, 50);
        gc.fillText("", 250, 50);

        gc.fillText("Grillijono: ", 10, 75);
        gc.fillText("", 250, 75);

        gc.fillText("Maksupääte: ", 10, 100);
        gc.fillText("", 250, 100);

        gc.fillText("Pöytä: ", 10, 125);
        gc.fillText("", 250, 125);

        gc.fillText("Astioidenpalautus: ", 10, 150);
        gc.fillText("", 250, 150);
    }

    public void uusiAsiakas(Palvelupiste[] palvelupisteet) {
        // TODO implement here
        //System.out.println("uusiAsiakas: " + palvelupisteet.get(0).getNykyisetAsiakkaat());
        try {
            tyhjennaNaytto();
            System.out.println("PÄIVITETTY");
            System.out.println(palvelupisteet[0].getNykyisetAsiakkaat());
            System.out.println(palvelupisteet[1].getNykyisetAsiakkaat());
            System.out.println(palvelupisteet[2].getNykyisetAsiakkaat());
            System.out.println(palvelupisteet[3].getNykyisetAsiakkaat());
            System.out.println(palvelupisteet[4].getNykyisetAsiakkaat());
            gc.fillText(String.valueOf(palvelupisteet[0].getNykyisetAsiakkaat()), 250, 50);
            gc.fillText(String.valueOf(palvelupisteet[1].getNykyisetAsiakkaat()), 250, 75);
            gc.fillText(String.valueOf(palvelupisteet[2].getNykyisetAsiakkaat()), 250, 100);
            gc.fillText(String.valueOf(palvelupisteet[3].getNykyisetAsiakkaat()), 250, 125);
            gc.fillText(String.valueOf(palvelupisteet[4].getNykyisetAsiakkaat()), 250, 150);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}