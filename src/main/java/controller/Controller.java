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
    private TextField tavallinenJonoKeskiarvo;

    @FXML
    private TextField tavallinenJonoMuutos;

    @FXML
    private TextField grillijonoTextField;

    @FXML
    private TextField grillijonoKeskiarvo;

    @FXML
    private TextField grillijonoMuutos;

    @FXML
    private TextField maksupaateTextField;

    @FXML
    private TextField maksupaateKeskiarvo;

    @FXML
    private TextField maksupaateMuutos;

    @FXML
    private TextField poytaTextField;

    @FXML
    private TextField poytaKeskiarvo;

    @FXML
    private TextField poytaMuutos;

    @FXML
    private TextField astioidenpalautusTextField;

    @FXML
    private TextField astioidenpalautusKeskiarvo;

    @FXML
    private TextField astioidenpalautusMuutos;

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

    @FXML
    private TextField saapumisKeskiarvo;

    @FXML
    private TextField saapumisMuutos;

    @FXML
    private Label viiveDisplay;

    @FXML
    private Button edellisetTuloksetButton;

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
        viewViive();
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
        if (aikaTextField.getText().isEmpty() || viiveTextField.getText().isEmpty() || tavallinenJonoTextField.getText().isEmpty() || grillijonoTextField.getText().isEmpty() || maksupaateTextField.getText().isEmpty() || poytaTextField.getText().isEmpty() || astioidenpalautusTextField.getText().isEmpty() || saapumisKeskiarvo.getText().isEmpty() || saapumisMuutos.getText().isEmpty() || tavallinenJonoKeskiarvo.getText().isEmpty() || tavallinenJonoMuutos.getText().isEmpty() || grillijonoKeskiarvo.getText().isEmpty() || grillijonoMuutos.getText().isEmpty() || maksupaateKeskiarvo.getText().isEmpty() || maksupaateMuutos.getText().isEmpty() || poytaKeskiarvo.getText().isEmpty() || poytaMuutos.getText().isEmpty() || astioidenpalautusKeskiarvo.getText().isEmpty() || astioidenpalautusMuutos.getText().isEmpty()) {
            errorLabel.setOpacity(1);
            errorLabel.setText("Täytä kaikki kentät!");
        } else {
            kaynnistaSimulointi();
            errorLabel.setOpacity(0);
        }
    }

    @FXML
    public void viewViive() {
    	viiveDisplay.setText(String.valueOf(moottori.getViive()));
    }

    @Override
    public void nopeuta() {
        moottori.setViive((long) (moottori.getViive() * 0.75));
        viewViive();
    }

    @Override
    public void hidasta() {
        moottori.setViive((long) (moottori.getViive() * 1.25));
        viewViive();
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


    public int getTavallinenJonoKeskiarvo() {
        return Integer.parseInt(tavallinenJonoKeskiarvo.getText());
    }

    public int getGrillijonoKeskiarvo() {
        return Integer.parseInt(grillijonoKeskiarvo.getText());
    }

    public int getMaksupaateKeskiarvo() {
        return Integer.parseInt(maksupaateKeskiarvo.getText());
    }

    public int getPoytaKeskiarvo() {
        return Integer.parseInt(poytaKeskiarvo.getText());
    }

    public int getAstioidenpalautusKeskiarvo() {
        return Integer.parseInt(astioidenpalautusKeskiarvo.getText());
    }

    public int getTavallinenJonoMuutos() {
        return Integer.parseInt(tavallinenJonoMuutos.getText());
    }

    public int getGrillijonoMuutos() {
        return Integer.parseInt(grillijonoMuutos.getText());
    }

    public int getMaksupaateMuutos() {
        return Integer.parseInt(maksupaateMuutos.getText());
    }

    public int getPoytaMuutos() {
        return Integer.parseInt(poytaMuutos.getText());
    }

    public int getAstioidenpalautusMuutos() {
        return Integer.parseInt(astioidenpalautusMuutos.getText());
    }

    public int getSaapumisKeskiarvo() {
        return Integer.parseInt(saapumisKeskiarvo.getText());
    }

    public int getSaapumisMuutos() {
        return Integer.parseInt(saapumisMuutos.getText());
    }

    public void setTavallinenJonoKeskiarvo(int tavallinenJonoKeskiarvo) {
        this.tavallinenJonoKeskiarvo.setText(String.valueOf(tavallinenJonoKeskiarvo));
    }

    public void setGrillijonoKeskiarvo(int grillijonoKeskiarvo) {
        this.grillijonoKeskiarvo.setText(String.valueOf(grillijonoKeskiarvo));
    }

    public void setMaksupaateKeskiarvo(int maksupaateKeskiarvo) {
        this.maksupaateKeskiarvo.setText(String.valueOf(maksupaateKeskiarvo));
    }

    public void setPoytaKeskiarvo(int poytaKeskiarvo) {
        this.poytaKeskiarvo.setText(String.valueOf(poytaKeskiarvo));
    }

    public void setAstioidenpalautusKeskiarvo(int astioidenpalautusKeskiarvo) {
        this.astioidenpalautusKeskiarvo.setText(String.valueOf(astioidenpalautusKeskiarvo));
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

    @FXML
    public void openEdellisetTulokset() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/tulokset_view.fxml"));
        Parent root = loader.load();
        Stage tuloksetStage = new Stage();
        tuloksetStage.setTitle("Simulaattori");
        TuloksetViewController TuloksetViewController = loader.getController();
        tuloksetStage.getIcons().add(new Image("/images/iconimage.png"));
        tuloksetStage.setScene(new Scene(root));
        tuloksetStage.show();
    }

}