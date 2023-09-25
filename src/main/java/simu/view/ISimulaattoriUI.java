package simu.view;

public interface ISimulaattoriUI {

    // Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
    public double getAika();
    public long getViive();

    // Aseta syötteet, joita kontrolleri tarvitsee
    public void setAika(double aika);
    public void setViive(long viive);

    //Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa
    public void setLoppuaika(double aika);

    // Kontrolleri tarvitsee
    public IVisualisointi getVisualisointi();

}