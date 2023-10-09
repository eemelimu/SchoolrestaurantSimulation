package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import view.ISimulaattoriUI;
import view.IVisualisointi;
import view.SimulaattorinGUI;
import view.Visualisointi;
import javafx.scene.canvas.Canvas;

import java.io.IOException;

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
    private Button keskittymisNappi;

    @FXML
    private TextArea tuloksetTextArea;

    @FXML
    private Label errorLabel;

    @FXML
    private IVisualisointi visualisointi = null;

    @FXML
    private Canvas visu;


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
        clearTulostukset();
        moottori = new OmaMoottori(this, simuUI, this);
        moottori.setSimulointiaika(Double.parseDouble(aikaTextField.getText()));
        moottori.setViive(Long.parseLong((viiveTextField.getText())));
        System.out.println("Simulaatio käynnistetty");
        visualisointi1 = new Visualisointi(visu);
        visualisointi1.tyhjennaNaytto();
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
            kaynnistaSimulointi();
            errorLabel.setOpacity(0);
        }
    }

    @Override
    public void nopeuta() {
        moottori.setViive((long) (moottori.getViive()*0.9));
    }

    @Override
    public void hidasta() {
        moottori.setViive((long) (moottori.getViive()*1.1));
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

    public int tavallinenKapasiteetti() {
        return Integer.parseInt(tavallinenJonoTextField.getText());
    }

    public int grilliKapasiteetti() {
        return Integer.parseInt(grillijonoTextField.getText());
    }

    public int maksupaateKapasiteetti() {
        return Integer.parseInt(maksupaateTextField.getText());
    }

    public int poytaKapasiteetti() {
        return Integer.parseInt(poytaTextField.getText());
    }

    public int astioidenpalautusKapasiteetti() {
        return Integer.parseInt(astioidenpalautusTextField.getText());
    }


    @FXML
    public void openKeskittymisNakyma() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/keskittymis_view.fxml"));
        Parent root = loader.load();
        Stage keskittymisStage = new Stage();
        keskittymisStage.setTitle("Simulaattori");
        KeskittymisViewController keskittymisViewController = loader.getController();
        keskittymisStage.getIcons().add(new Image("/images/iconimage.png"));
        keskittymisStage.setScene(new Scene(root));
        keskittymisStage.show();
    }

}