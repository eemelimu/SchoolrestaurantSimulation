package view;
/**
 * ISimulaattoriUI interface for the application.
 */
public interface ISimulaattoriUI {

    // Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
    //public double getAika(); //kommentoitu 5.10.2023
    //public long getViive(); kommentoitu 5.10.2023
    /*public int getTavallinenAsiakasKapasiteetti();
    public int getGrilliAsiakasKapasiteetti();
    public int getMaksupaateAsiakasKapasiteetti();
    public int getPoytaAsiakasKapasiteetti();
    public int getAstioidenpalautusKapasiteetti();*/ //kaikki kommentoitu 5.10.2023

    // Aseta syötteet, joita kontrolleri tarvitsee
    //public void setAika(double aika);
    //public void setViive(long viive);
    /*public void setTavallinenAsiakasKapasiteetti(int tavallinenAsiakasKapasiteetti);
    public void setGrilliAsiakasKapasiteetti(int grilliAsiakasKapasiteetti);
    public void setMaksupaateAsiakasKapasiteetti(int maksupaateAsiakasKapasiteetti);
    public void setPoytaAsiakasKapasiteetti(int poytaAsiakasKapasiteetti);
    public void setAstioidenpalautusKapasiteetti(int astioidenpalautusKapasiteetti);*/ // kommentoitu 5.10.2023

    //Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa
    public void setLoppuaika(double aika);

    // Kontrolleri tarvitsee
    public IVisualisointi getVisualisointi();

}