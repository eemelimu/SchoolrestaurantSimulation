package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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

import java.io.IOException;

public class SimulaattorinGUI extends Application implements ISimulaattoriUI {

    //private ISimulaattoriUI ui = this;
    //private Controller controller = new Controller(ui);
    private double simulointiAika; // Simulointi aika jonka käyttäjä antaa
    private long simulointiViive; // Simulointi viive jonka käyttäjä antaa

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/simulation_view.fxml"));
        Parent root = loader.load();

        stage.setScene(new Scene(root));
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
