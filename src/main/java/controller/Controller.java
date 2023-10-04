package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import view.ISimulaattoriUI;
import javafx.scene.control.*;
import view.IVisualisointi;
import view.SimulaattorinGUI;
import view.Visualisointi;
import javafx.scene.canvas.Canvas;

public class Controller implements IControllerForV, IControllerForM{

    @FXML
    private TextField aikaTextField;

    @FXML
    private TextField viiveTextField;

    @FXML
    private Button nopeutaButton;

    @FXML
    private Button hidastaButton;

    @FXML
    private TextField tavallinenJonoTextField;

    @FXML
    private TextField grillijonoTextField;

    @FXML
    private TextField maksupaateTextField;

    @FXML
    private TextField poytaTextField;

    @FXML
    private TextField astioidenpalautusTextField;

    @FXML
    private Button kaynnistaButton;

    @FXML
    private TextArea tuloksetTextArea;

    @FXML
    private Label errorLabel;

    @FXML
    private IVisualisointi visualisointi = null;

    @FXML
    private Canvas visu;

    /*
    public void handleStart() {
        if (visualisointi == null) {
            visualisointi = new Visualisointi(visu);
            visualisointi.tyhjennaNaytto();
        }
    }

     */

    private IMoottori moottori;
    private ISimulaattoriUI ui = new SimulaattorinGUI();
    private SimulaattorinGUI simuUI = new SimulaattorinGUI();
    private Visualisointi visualisointi1;

    public Controller() {
    }

    @FXML
    public void clearTulostukset() {
        tuloksetTextArea.setText("");
    }

    @FXML
    public void setTulostukset(String tulostus) {
        tuloksetTextArea.appendText(tulostus + "\n");
    }

    @Override
    public void kaynnistaSimulointi() {
        //handleStart();
        clearTulostukset();
        moottori = new OmaMoottori(this, simuUI, this);
        moottori.setSimulointiaika(ui.getAika());
        moottori.setViive(ui.getViive());
        System.out.println("controller aika: " + ui.getAika());
        System.out.println("controller viive: " + ui.getViive());
        System.out.println("Simulaatio käynnistetty"); // testi
        visualisointi1 = new Visualisointi(visu);
        visualisointi1.tyhjennaNaytto(); // uus
        //ui.getVisualisointi().tyhjennaNaytto();
        ((Thread) moottori).start();
    }

    @FXML
    private void isNumeric() {
        TextField[] textFields = new TextField[]{aikaTextField, viiveTextField, tavallinenJonoTextField, grillijonoTextField, maksupaateTextField, poytaTextField, astioidenpalautusTextField};
        for (TextField textField : textFields) {
            String character = textField.getText();
            if (!character.matches("\\d*")) {
                textField.setText(character.replaceAll("[^\\d]", ""));
            }
        }
    }

    @FXML
    private void kaynnista(){
        if (aikaTextField.getText().isEmpty() || viiveTextField.getText().isEmpty() || tavallinenJonoTextField.getText().isEmpty() || grillijonoTextField.getText().isEmpty() || maksupaateTextField.getText().isEmpty() || poytaTextField.getText().isEmpty() || astioidenpalautusTextField.getText().isEmpty()) {
            errorLabel.setOpacity(1);
            errorLabel.setText("Aika ja viive kentät eivät voi olla tyhjiä!");
        } else {
            ui.setAika(Double.parseDouble(aikaTextField.getText()));
            ui.setViive(Long.parseLong(viiveTextField.getText()));
            ui.setTavallinenAsiakasKapasiteetti(Integer.parseInt(tavallinenJonoTextField.getText()));
            ui.setGrilliAsiakasKapasiteetti(Integer.parseInt(grillijonoTextField.getText()));
            ui.setMaksupaateAsiakasKapasiteetti(Integer.parseInt(maksupaateTextField.getText()));
            ui.setPoytaAsiakasKapasiteetti(Integer.parseInt(poytaTextField.getText()));
            ui.setAstioidenpalautusKapasiteetti(Integer.parseInt(astioidenpalautusTextField.getText()));
            kaynnistaSimulointi();
            errorLabel.setOpacity(0);
        }

    }

    @FXML
    public TextField getTavallinenJonoTextField() {
        return tavallinenJonoTextField;
    }

    @Override
    public void nopeuta() {

    }

    @Override
    public void hidasta() {

    }

    @Override
    public void naytaLoppuaika(double aika) {
    }

    @Override
    public void visualisoiAsiakas() {

    }

    @Override
    public IVisualisointi getVisualisointi() {
        return visualisointi1;
    }

}