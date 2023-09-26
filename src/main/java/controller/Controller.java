package controller;

import simu.framework.IMoottori;
import simu.model.OmaMoottori;
import view.ISimulaattoriUI;

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
        System.out.println("controller aika: " + ui.getAika());
        System.out.println("controller viive: " + ui.getViive());
        System.out.println("Simulaatio käynnistetty"); // testi
        //ui.getVisualisointi().tyhjennaNaytto();
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
