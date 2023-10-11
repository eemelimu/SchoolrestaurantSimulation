package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dao.SimulointiDao;
import java.util.*;
import entity.*;

public class TuloksetViewController {
    private SimulointiDao simulointiDao = new SimulointiDao();

    @FXML
    private Button closeButton;

    @FXML
    private ChoiceBox simuloinnitChoiceBox;

    @FXML
    private TextArea tulosHistoriaTulokset;

    @FXML
    public void closeWindow() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void initialize() {
        simuloinnitChoiceBox.getItems().addAll(simulointiDao.getAllSimulointi());
    }

    @FXML
    public void setTulosHistoriaTulokset() {
        Simulointi s = (Simulointi) simuloinnitChoiceBox.getValue();
        tulosHistoriaTulokset.setText(s + " \nAika: " + s.getAika() +  " Viive: " + s.getViive() + "\n");
        List<PalvelupisteData> palvelupisteDataList = simulointiDao.getAllPalvelupisteet(s.getId());
        for (PalvelupisteData p : palvelupisteDataList) {
            tulosHistoriaTulokset.appendText("\n" + p);
        }
    }
}
