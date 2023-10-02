package view;

public interface ISimulaattoriUI {

    // Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
    public double getAika();
    public long getViive();
    public int getTavallinenAsiakasKapasiteetti();
    public int getGrilliAsiakasKapasiteetti();
    public int getMaksupaateAsiakasKapasiteetti();
    public int getPoytaAsiakasKapasiteetti();
    public int getAstioidenpalautusKapasiteetti();

    // Aseta syötteet, joita kontrolleri tarvitsee
    public void setAika(double aika);
    public void setViive(long viive);
    public void setTavallinenAsiakasKapasiteetti(int tavallinenAsiakasKapasiteetti);
    public void setGrilliAsiakasKapasiteetti(int grilliAsiakasKapasiteetti);
    public void setMaksupaateAsiakasKapasiteetti(int maksupaateAsiakasKapasiteetti);
    public void setPoytaAsiakasKapasiteetti(int poytaAsiakasKapasiteetti);
    public void setAstioidenpalautusKapasiteetti(int astioidenpalautusKapasiteetti);

    //Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa
    public void setLoppuaika(double aika);

    // Kontrolleri tarvitsee
    public IVisualisointi getVisualisointi();

}