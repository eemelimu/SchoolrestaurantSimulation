package controller;

import view.IVisualisointi;

public interface IControllerForM {

    // Rajapinta, joka tarjotaan moottorille:

    public void naytaLoppuaika(double aika);
    public void visualisoiAsiakas();
    public IVisualisointi getVisualisointi();
}