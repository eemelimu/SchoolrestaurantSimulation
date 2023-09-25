package simu.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import simu.controller.Controller;
import simu.controller.IControllerForM;

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
        topleft.getChildren().add(simulointiAikaLabel);
        topleft.getChildren().add(simulointiAikaTextField);

        // VBox topright joka sisältää top containerin oikean puolen
        VBox topright = new VBox();
        Label simulointiViiveLabel = new Label("Simulointiviive");
        TextField simulointiViiveTextField = new TextField();
        topright.getChildren().add(simulointiViiveLabel);
        topright.getChildren().add(simulointiViiveTextField);

        // button jota käytetään testaamiseen
        Button button = new Button("Testaa");
        top.getChildren().add(button);

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

        // Borderpanen center osa sisältää
        // simulaation animoimisen / visualisoinnin

        // Borderpanen right osa sisältää
        // simulaation tulokset (kun simulaatio on loppunut)
        // Right osassa käytetään VBox
        VBox right = new VBox();

        // Borderpanen bottom osa sisältää
        // simulaation käynnistys-, nopeuta- ja hidasta -painikkeet
        // Bottom osassa käytetään HBox
        HBox bottom = new HBox();




        // Kun kaikki komponentit on tehty
        // ne lisätään tässä borderpanen eri osiin
        borderPane.setTop(top);

        // Kaikki event handlerit tänne
        button.setOnAction((event) -> {
            simulointiAika = Double.parseDouble(simulointiAikaTextField.getText());
            ui.setAika(simulointiAika);

            simulointiViive = Long.parseLong(simulointiViiveTextField.getText());
            ui.setViive(simulointiViive);

            controller.kaynnistaSimulointi();
        });

        //Scene
        Scene scene = new Scene(borderPane, 640, 480);

        // Stage
        stage.setScene(scene);
        stage.setTitle("Simulaattori");
        stage.show();
    }

    @Override
    public double getAika() {
        return 0;
    }

    @Override
    public long getViive() {
        return 0;
    }

    @Override
    public void setLoppuaika(double aika) {

    }

    @Override
    public void setAika(double aika) {

    }

    @Override
    public void setViive(long viive) {

    }

    @Override
    public IVisualisointi getVisualisointi() {
        return null;
    }
}
