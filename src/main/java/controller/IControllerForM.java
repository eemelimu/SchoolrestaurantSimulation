package controller;

import view.IVisualisointi;
/**
 * IControllerForM interface for the application.
 */
public interface IControllerForM {

    // Rajapinta, joka tarjotaan moottorille:

    public void naytaLoppuaika(double aika);
    public void visualisoiAsiakas();
    public IVisualisointi getVisualisointi();
}