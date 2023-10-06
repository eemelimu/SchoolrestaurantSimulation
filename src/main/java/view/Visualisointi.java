package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import simu.model.Palvelupiste;
import simu.model.OmaMoottori;
import javafx.scene.image.Image;
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
        gc.setFill(Color.rgb(61,61,61));
        Image image = new Image("/images/simulator_dark.png");
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(image, 0, 190, 800, 400);

        Image kehys = new Image("/images/palkit/palkki0.png");
        gc.drawImage(kehys, 290,13, 100, 20);
        gc.drawImage(kehys, 290,43, 100, 20);
        gc.drawImage(kehys, 290,73, 100, 20);
        gc.drawImage(kehys, 290,103, 100, 20);
        gc.drawImage(kehys, 290,133, 100, 20);

        gc.setFill(Color.rgb(93, 173, 213));
        gc.setFont(javafx.scene.text.Font.font(20)); // Set the font size
        gc.fillText("Tavallinen jono: ", 10, 30);
        gc.fillText("", 350, 0);

        gc.fillText("Grillijono: ", 10, 60);
        gc.fillText("", 350, 30);

        gc.fillText("Maksupääte: ", 10, 90);
        gc.fillText("", 350, 60);

        gc.fillText("Pöytä: ", 10, 120);
        gc.fillText("", 350, 90);

        gc.fillText("Astioidenpalautus: ", 10, 150);
        gc.fillText("", 350, 120);
    }

    public void uusiAsiakas(Palvelupiste[] palvelupisteet) {
        // TODO implement here
        //System.out.println("uusiAsiakas: " + palvelupisteet.get(0).getNykyisetAsiakkaat());
        try {
            tyhjennaNaytto();
            System.out.println("PÄIVITETTY");
            System.out.println(palvelupisteet[0].getPalveltavienJonoSize());
            System.out.println(palvelupisteet[1].getPalveltavienJonoSize());
            System.out.println(palvelupisteet[2].getPalveltavienJonoSize());
            System.out.println(palvelupisteet[3].getPalveltavienJonoSize());
            System.out.println(palvelupisteet[4].getPalveltavienJonoSize());
            gc.fillText(String.valueOf(palvelupisteet[0].getPalveltavienJonoSize()), 250, 30);
            gc.fillText(String.valueOf(palvelupisteet[1].getPalveltavienJonoSize()), 250, 60);
            gc.fillText(String.valueOf(palvelupisteet[2].getPalveltavienJonoSize()), 250, 90);
            gc.fillText(String.valueOf(palvelupisteet[3].getPalveltavienJonoSize()), 250, 120);
            gc.fillText(String.valueOf(palvelupisteet[4].getPalveltavienJonoSize()), 250, 150);

            for (int i = 0; i < palvelupisteet.length; i++) {
                int progress = palvelupisteet[i].getMaksimiAsiakasKapasiteetti() / palvelupisteet[i].getPalveltavienJonoSize();
                System.out.println("p: " + palvelupisteet[i].getPalvelupisteNimi() + " progress: " + progress);
                if (progress  == 1) {
                    String p = "/images/palkit/palkki100.png";
                    Image palkki = new Image(p);
                    gc.drawImage(palkki, 290, (13 + (i*30)), 100, 20);
                } else {
                    String path = "/images/palkit/palkki" + (Math.round(progress / 10.0) * 10 + ".png");
                    System.out.println("math round: " + Math.round(progress / 10.0) * 10);
                    System.out.println("path: " + path);
                    Image palkki = new Image(path);
                    gc.drawImage(palkki, 290, (13 + (i * 30)), 100, 20);
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}