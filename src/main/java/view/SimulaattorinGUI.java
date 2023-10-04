package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import controller.Controller;
import controller.IControllerForM;
import org.w3c.dom.Text;
import javafx.scene.image.Image;

import java.io.IOException;

public class SimulaattorinGUI extends Application implements ISimulaattoriUI {

    //private ISimulaattoriUI ui = this;
    //private Controller controller = new Controller(ui);
    private double simulointiAika; // Simulointi aika jonka käyttäjä antaa
    private long simulointiViive; // Simulointi viive jonka käyttäjä antaa
    private int tavallinenAsiakasKapasiteetti;
    private int grilliAsiakasKapasiteetti;
    private int maksupaateAsiakasKapasiteetti;
    private int poytaAsiakasKapasiteetti;
    private int astioidenpalautusKapasiteetti;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/simulation_view.fxml"));
        Parent root = loader.load();

        stage.getIcons().add(new Image("/iconimage.png"));
        stage.setScene(new Scene(root));
        stage.setTitle("Simulaattori");
        stage.show();
    }

    @Override
    public double getAika() {
        return this.simulointiAika;
    }

    @Override
    public int getTavallinenAsiakasKapasiteetti() {
        return this.tavallinenAsiakasKapasiteetti;
    }

    @Override
    public int getGrilliAsiakasKapasiteetti() {
        return this.grilliAsiakasKapasiteetti;
    }

    @Override
    public int getMaksupaateAsiakasKapasiteetti() {
        return this.maksupaateAsiakasKapasiteetti;
    }

    @Override
    public int getPoytaAsiakasKapasiteetti() {
        return this.poytaAsiakasKapasiteetti;
    }

    @Override
    public int getAstioidenpalautusKapasiteetti() {
        return this.astioidenpalautusKapasiteetti;
    }

    @Override
    public void setTavallinenAsiakasKapasiteetti(int tavallinenAsiakasKapasiteetti) {
        this.tavallinenAsiakasKapasiteetti = tavallinenAsiakasKapasiteetti;
    }

    @Override
    public void setGrilliAsiakasKapasiteetti(int grilliAsiakasKapasiteetti) {
        this.grilliAsiakasKapasiteetti = grilliAsiakasKapasiteetti;
    }

    @Override
    public void setMaksupaateAsiakasKapasiteetti(int maksupaateAsiakasKapasiteetti) {
        this.maksupaateAsiakasKapasiteetti = maksupaateAsiakasKapasiteetti;
    }

    @Override
    public void setPoytaAsiakasKapasiteetti(int poytaAsiakasKapasiteetti) {
        this.poytaAsiakasKapasiteetti = poytaAsiakasKapasiteetti;
    }

    @Override
    public void setAstioidenpalautusKapasiteetti(int astioidenpalautusKapasiteetti) {
        this.astioidenpalautusKapasiteetti = astioidenpalautusKapasiteetti;
    }

    @Override
    public long getViive() {
        return this.simulointiViive;
    }

    @Override
    public void setLoppuaika(double aika) {

    }

    @Override
    public IVisualisointi getVisualisointi() {
        return null;
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
        Visualisointi visualisointi = new Visualisointi(new Canvas(400,400));
        return visualisointi;
    }
}