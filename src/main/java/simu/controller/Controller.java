package simu.controller;

import javafx.application.Application;
import simu.model.OmaMoottori;
import simu.view.IVisualisointi;
import simu.view.ISimulaattoriUI;
import simu.framework.IMoottori;

public class Controller implements IControllerForV, IControllerForM{

    private IMoottori moottori;
    private ISimulaattoriUI ui;

    public Controller(ISimulaattoriUI ui) {
        this.ui = ui;
    }

    @Override
    public void kaynnistaSimulointi() {
        moottori = new OmaMoottori(this);
        moottori.setSimulointiaika(ui.getAika());
        moottori.setViive(ui.getViive());
        System.out.println("Simulaatio käynnistetty");
        ui.getVisualisointi().tyhjennaNaytto();
        ((Thread)moottori).start();
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
