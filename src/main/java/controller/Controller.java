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

    private IMoottori moottori;
    private ISimulaattoriUI ui = new SimulaattorinGUI();

    public Controller() {
    }

    @Override
    public void kaynnistaSimulointi() {
        moottori = new OmaMoottori(this);
        moottori.setSimulointiaika(ui.getAika());
        moottori.setViive(ui.getViive());
        System.out.println("controller aika: " + ui.getAika());
        System.out.println("controller viive: " + ui.getViive());
        System.out.println("Simulaatio käynnistetty"); // testi
        //ui.getVisualisointi().tyhjennaNaytto();
        ((Thread)moottori).start();
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
        ui.setAika(Double.parseDouble(aikaTextField.getText()));
        ui.setViive(Long.parseLong(viiveTextField.getText()));
        kaynnistaSimulointi();
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
