package controller;

import javafx.fxml.FXML;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import view.ISimulaattoriUI;
import javafx.scene.control.*;
import view.SimulaattorinGUI;

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

    private IMoottori moottori;
    private ISimulaattoriUI ui = new SimulaattorinGUI();
    private SimulaattorinGUI simuUI = new SimulaattorinGUI();

    public Controller() {
    }

    public void clearTulostukset() {
        tuloksetTextArea.setText("");
    }

    @FXML
    public void setTulostukset(String tulostus) {
        tuloksetTextArea.appendText(tulostus + "\n");
    }

    @Override
    public void kaynnistaSimulointi() {
        clearTulostukset();
        moottori = new OmaMoottori(this, simuUI, this);
        moottori.setSimulointiaika(ui.getAika());
        moottori.setViive(ui.getViive());
        System.out.println("controller aika: " + ui.getAika());
        System.out.println("controller viive: " + ui.getViive());
        System.out.println("Simulaatio käynnistetty"); // testi
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

}
