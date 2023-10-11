package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import simu.model.Palvelupiste;
import javafx.scene.image.Image;

public class Visualisointi implements IVisualisointi {
    private GraphicsContext gc;
    private Canvas canvas;
    private int mallikuva = 1;

    public Visualisointi(Canvas canvas) {
        this.canvas = canvas;
        this.gc = this.canvas.getGraphicsContext2D();
        tyhjennaNaytto();
    }

    // Resetoi tulokset, jotta uudet saadaan näkyviin
    public void tyhjennaNaytto() {
        gc.setFill(Color.rgb(61, 61, 61));
        Image image = new Image("/images/simulaatio_kuvaus/simulaatioKuvaus_bg.png");
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(image, 0, 190, 800, 400);

        Image kehys = new Image("/images/palkit/palkki0.0.png");
        gc.drawImage(kehys, 290, 12, 100, 20);
        gc.drawImage(kehys, 290, 42, 100, 20);
        gc.drawImage(kehys, 290, 72, 100, 20);
        gc.drawImage(kehys, 290, 102, 100, 20);
        gc.drawImage(kehys, 290, 132, 100, 20);

        gc.setFill(Color.rgb(93, 173, 213));
        gc.setFont(javafx.scene.text.Font.font(20));
        gc.fillText("Tavallinen linjasto: ", 10, 30);
        gc.fillText("", 350, 0);

        gc.fillText("Grillilinjasto: ", 10, 60);
        gc.fillText("", 350, 30);

        gc.fillText("Maksupääte: ", 10, 90);
        gc.fillText("", 350, 60);

        gc.fillText("Pöytä: ", 10, 120);
        gc.fillText("", 350, 90);

        gc.fillText("Astioidenpalautus: ", 10, 150);
        gc.fillText("", 350, 120);

        gc.fillText("Jono: ", 400, 30);

        gc.fillText("Jono: ", 400, 60);

        gc.fillText("Jono: ", 400, 90);

        gc.fillText("Jono: ", 400, 120);

        gc.fillText("Jono: ", 400, 150);
    }

    // Kutsutaan OmaMoottorin suoritaTapahtuma funktiossa.
    // Päivittää kuinka monta asiakasta on jokaisella palvelupisteellä
    // Päivittää myös palkki visualisoinnin joka esittää kuinka täynnä kyseinen palvelupiste on
    public void uusiAsiakas(Palvelupiste[] palvelupisteet) {
        try {
            // Tyhjennetään canva vanhoista tuloksista
            tyhjennaNaytto();
            // Päivitetään mallin kuvaa
            updateMallinkuva();
            // Asetetaan uudet tulokset
            gc.fillText(String.valueOf(palvelupisteet[0].getPalveltavienJonoSize()), 250, 30);
            gc.fillText(String.valueOf(palvelupisteet[1].getPalveltavienJonoSize()), 250, 60);
            gc.fillText(String.valueOf(palvelupisteet[2].getPalveltavienJonoSize()), 250, 90);
            gc.fillText(String.valueOf(palvelupisteet[3].getPalveltavienJonoSize()), 250, 120);
            gc.fillText(String.valueOf(palvelupisteet[4].getPalveltavienJonoSize()), 250, 150);

            gc.fillText(String.valueOf(palvelupisteet[0].getJonoSize()), 460, 30);
            gc.fillText(String.valueOf(palvelupisteet[1].getJonoSize()), 460, 60);
            gc.fillText(String.valueOf(palvelupisteet[2].getJonoSize()), 460, 90);
            gc.fillText(String.valueOf(palvelupisteet[3].getJonoSize()), 460, 120);
            gc.fillText(String.valueOf(palvelupisteet[4].getJonoSize()), 460, 150);

            // Päivitä ruokalan mallin asiakkaan liikkumista
            /*String path2 = "/images/simulaatio_kuvas/simulaatioKuvaus_" + this.mallikuva  + ".png";
            Image malli = new Image(path2);
            gc.drawImage(malli, 0, 190, 800, 400);
            if (this.mallikuva == 11) {
                this.mallikuva = 1;
            } else {
                this.mallikuva++;
            }*/


            // Käydään läpi kaikki palvelupisteet ja lasketaan prosentteina kuinka täynnä palvelupiste on
            // Prosenttiarvoa käytetään valitsemaan kuva joka kuvastaa kuinka täynnä palvelupiste on
            for (int i = 0; i < palvelupisteet.length; i++) {

                // Laske kuinka täynnä palvelupiste on prosentteina
                double progress = (double) palvelupisteet[i].getPalveltavienJonoSize() / palvelupisteet[i].getMaksimiAsiakasKapasiteetti();

                // Muuta arvo muotoon jolla sitä voi etsiä
                double value = (double) Math.round((progress) * 10) / 10;

                String path;

                if (value == 0.9) {
                    // Meillä ei oo tehty palkkia esittämään 90% täyttä palvelupistettä
                    // Joten kuvataan silloin 100% täyttä palvelupistettä.

                    // Path kuvaan
                    path = "/images/palkit/palkki1.0.png";
                } else {
                    // Path kuvaan
                    path = "/images/palkit/palkki" + value + ".png";
                }

                // Luo kuva ja päivitä näytölle
                Image palkki = new Image(path);
                gc.drawImage(palkki, 290, (12 + (i * 30)), 100, 20);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public void updateMallinkuva() {
        String kuvausPath = "/images/simulaatio_kuvaus/simulaatioKuvaus_" + this.mallikuva + ".png";
        Image malli = new Image(kuvausPath);
        gc.drawImage(malli, 0, 190, 800, 400);
        System.out.println("mallikuva: " + this.mallikuva);
        if (this.mallikuva == 11) {
            this.mallikuva = 1;
        } else {
            this.mallikuva++;
        }
    }
}