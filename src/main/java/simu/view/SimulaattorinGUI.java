package simu.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class SimulaattorinGUI extends Application implements ISimulaattoriUI {

    @Override
    public void start(Stage stage) {
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
    public IVisualisointi getVisualisointi() {
        return null;
    }
}
