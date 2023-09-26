package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import controller.Controller;
import controller.IControllerForM;
import org.w3c.dom.Text;

public class SimulaattorinGUI extends Application implements ISimulaattoriUI {

    private ISimulaattoriUI ui = this;
    private Controller controller = new Controller(ui);
    private double simulointiAika; // Simulointi aika jonka käyttäjä antaa
    private long simulointiViive; // Simulointi viive jonka käyttäjä antaa

    @Override
    public void start(Stage stage) {

        // Borderpanea käytetään pohjana
        // johon lisätään muut komponentit
        BorderPane borderPane = new BorderPane();

        // Borderpanen top osa sisältää tekstikentät:
        // - Simulointiaika
        // - Simulointiviive
        // Top osassa käytetään kahta VBoxia
        // yksi simulointiajalle ja toinen simulointiviiveelle
        // nämä VBoxit lisätään yhden HBoxin sisälle

        // Hbox top joka on Borderpanen top osion container
        HBox top = new HBox();

        // VBox topleft joka sisältää top containerin vasemman puolen
        VBox topleft = new VBox();
        Label simulointiAikaLabel = new Label("Simulointiaika");
        TextField simulointiAikaTextField = new TextField();
        simulointiAikaTextField.setPromptText("ms");
        topleft.getChildren().add(simulointiAikaLabel);
        topleft.getChildren().add(simulointiAikaTextField);

        // VBox topright joka sisältää top containerin oikean puolen
        VBox topright = new VBox();
        Label simulointiViiveLabel = new Label("Simulointiviive");
        TextField simulointiViiveTextField = new TextField();
        simulointiViiveTextField.setPromptText("ms");
        topright.getChildren().add(simulointiViiveLabel);
        topright.getChildren().add(simulointiViiveTextField);

        // Lisätään topleft ja topright top containeriin
        top.getChildren().add(topleft);
        top.getChildren().add(topright);

        // Asetetaan top containerin sisältö keskelle
        // ja lisätään väliä vasemman ja oikean puolen väliin
        top.setAlignment(Pos.CENTER);
        top.setSpacing(20);

        // Borderpanen left osa sisältää palvelupisteet
        // ja käyttäjän valitsemat määrät jokaiselle palvelupisteelle
        // Left osassa käytetään VBox
        VBox left = new VBox();
        HBox lefttop = new HBox();

        // Palvelupisteet

        // Tavallinen jono
        HBox tavallinenJono = new HBox();
        Label tavallinenJonoLabel = new Label("Tavallinen jono: ");
        tavallinenJonoLabel.setPadding(new Insets(0, 5, 0, 0));
        Spinner tavallinenJonoSpinner = new Spinner(0, 10, 0);
        tavallinenJonoSpinner.setPrefWidth(60);
        tavallinenJonoSpinner.setEditable(true);
        tavallinenJono.getChildren().add(tavallinenJonoLabel);
        tavallinenJono.getChildren().add(tavallinenJonoSpinner);
        tavallinenJono.setPadding(new Insets(10, 10, 10, 10));
        left.getChildren().add(tavallinenJono);

        // Grilli jono
        HBox grilliJono = new HBox();
        Label grilliJonoLabel = new Label("Grillijono:");
        grilliJonoLabel.setPadding(new Insets(0, 5, 0, 0));
        Spinner grilliJonoSpinner = new Spinner(0, 10, 0);
        grilliJonoSpinner.setPrefWidth(60);
        grilliJonoSpinner.setEditable(true);
        grilliJono.getChildren().add(grilliJonoLabel);
        grilliJono.getChildren().add(grilliJonoSpinner);
        grilliJono.setPadding(new Insets(10, 10, 10, 10));
        left.getChildren().add(grilliJono);

        // Maksupääte
        HBox maksupaate = new HBox();
        Label maksupaateLabel = new Label("Maksupääte:");
        maksupaateLabel.setPadding(new Insets(0, 5, 0, 0));
        Spinner maksupaateSpinner = new Spinner(0, 10, 0);
        maksupaateSpinner.setPrefWidth(60);
        maksupaateSpinner.setEditable(true);
        maksupaate.getChildren().add(maksupaateLabel);
        maksupaate.getChildren().add(maksupaateSpinner);
        maksupaate.setPadding(new Insets(10, 10, 10, 10));
        left.getChildren().add(maksupaate);

        // Pöytä
        HBox poyta = new HBox();
        Label poytaLabel = new Label("Pöytä:");
        poytaLabel.setPadding(new Insets(0, 5, 0, 0));
        Spinner poytaSpinner = new Spinner(0, 10, 0);
        poytaSpinner.setPrefWidth(60);
        poytaSpinner.setEditable(true);
        poyta.getChildren().add(poytaLabel);
        poyta.getChildren().add(poytaSpinner);
        poyta.setPadding(new Insets(10, 10, 10, 10));
        left.getChildren().add(poyta);

        // Astioiden palautus
        HBox astioidenPalautus = new HBox();
        Label astioidenPalautusLabel = new Label("Astioiden palautus:");
        astioidenPalautusLabel.setPadding(new Insets(0, 5, 0, 0));
        Spinner astioidenPalautusSpinner = new Spinner(0, 10, 0);
        astioidenPalautusSpinner.setPrefWidth(60);
        astioidenPalautusSpinner.setEditable(true);
        astioidenPalautus.getChildren().add(astioidenPalautusLabel);
        astioidenPalautus.getChildren().add(astioidenPalautusSpinner);
        astioidenPalautus.setPadding(new Insets(10, 10, 10, 10));
        left.getChildren().add(astioidenPalautus);


        /*
        TextArea palvelupisteetTextArea = new TextArea();
        palvelupisteetTextArea.setEditable(false);
        palvelupisteetTextArea.setPrefHeight(450);
        palvelupisteetTextArea.setPrefWidth(200);
        palvelupisteetTextArea.setPromptText("Syötä palvelupisteet ja niiden määrät.");
        Label palvelupisteetLabel = new Label("Palvelupisteet");
        lefttop.getChildren().add(palvelupisteetLabel);
        lefttop.setAlignment(Pos.CENTER);
        Insets padding2 = new Insets(10, 10, 10, 10);
        left.getChildren().add(lefttop);
        left.setPadding(padding2);
        left.getChildren().add(palvelupisteetTextArea);
        left.setSpacing(7);
        left.setAlignment(Pos.CENTER_LEFT);
        */


        // Borderpanen center osa sisältää
        // simulaation animoimisen / visualisoinnin

        // Borderpanen right osa sisältää
        // simulaation tulokset (kun simulaatio on loppunut)
        // Right osassa käytetään VBox
        VBox right = new VBox();
        HBox righttop = new HBox(); // labelia varten
        TextArea tuloksetTextArea = new TextArea();
        tuloksetTextArea.setEditable(false);
        tuloksetTextArea.setPrefHeight(450);
        tuloksetTextArea.setPrefWidth(200);
        tuloksetTextArea.setPromptText("Syötä aika ja viive ja käynnistä simulaatio.");
        Label tuloksetLabel = new Label("Tulokset");
        righttop.getChildren().add(tuloksetLabel);
        righttop.setAlignment(Pos.CENTER);
        Insets padding = new Insets(10, 10, 10, 10);
        right.getChildren().add(righttop);
        right.setPadding(padding);
        right.getChildren().add(tuloksetTextArea);
        right.setSpacing(7);
        right.setAlignment(Pos.CENTER_RIGHT);


        // Borderpanen bottom osa sisältää
        // simulaation käynnistys-, nopeuta- ja hidasta -painikkeet
        // Bottom osassa käytetään HBox
        HBox bottom = new HBox();
        Button startButton = new Button("Käynnistä");
        bottom.getChildren().add(startButton);
        bottom.setAlignment(Pos.CENTER);


        // Kun kaikki komponentit on tehty
        // ne lisätään tässä borderpanen eri osiin
        borderPane.setTop(top);
        borderPane.setRight(right);
        borderPane.setLeft(left);
        borderPane.setBottom(bottom);

        // Kaikki event handlerit tänne
        startButton.setOnAction((event) -> {
            if (!simulointiAikaTextField.getText().isEmpty() || !simulointiViiveTextField.getText().isEmpty()) {
                try {
                    simulointiAika = Double.parseDouble(simulointiAikaTextField.getText());
                    setAika(simulointiAika);

                    simulointiViive = Long.parseLong(simulointiViiveTextField.getText());
                    setViive(simulointiViive);

                    controller.kaynnistaSimulointi();
                } catch (NumberFormatException e) {
                    tuloksetTextArea.setText("Virheellinen syöte.");
                }
            } else {
                tuloksetTextArea.setText("Syötä aika ja viive");
            }


        });

        //Scene
        Scene scene = new Scene(borderPane, 950, 600);
        scene.getStylesheets().add("styles.css");

        // Stage
        stage.setScene(scene);
        stage.setTitle("Simulaattori");
        stage.show();
    }

    @Override
    public double getAika() {
        return this.simulointiAika;
    }

    @Override
    public long getViive() {
        return this.simulointiViive;
    }

    @Override
    public void setLoppuaika(double aika) {

    }

    @Override
    public void setAika(double aika) {
        this.simulointiAika = aika;
    }


    @Override
    public void setViive(long viive) {
        this.simulointiViive = viive;
    }

    @Override
    public IVisualisointi getVisualisointi() {
        return null;
    }
}
