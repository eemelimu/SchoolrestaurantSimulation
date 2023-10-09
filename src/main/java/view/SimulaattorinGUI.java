package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.IOException;

public class SimulaattorinGUI extends Application implements ISimulaattoriUI {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/simulation_view.fxml"));
        Parent root = loader.load();

        stage.getIcons().add(new Image("/images/iconimage.png"));

        root.getStylesheets().add("/dark_styles.css");
        stage.setScene(new Scene(root));
        stage.setTitle("Simulaattori");
        stage.show();
    }

    @Override
    public void setLoppuaika(double aika) {

    }

    @Override
    public IVisualisointi getVisualisointi() {
        return null;
    }
}