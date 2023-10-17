package simu.model;

import simu.framework.*;
/**
 * Asiakas-luokka
 *
 * Luokka sisältää asiakkaan tiedot
 *
 * @version 1.0 1.4.2019
 *
 */
public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	protected double palvelunPaattymisAika;
	private int id;
	private static int i = 1;
	private static long sum = 0;

	/**
	 * Konstruktori
	 */
	public Asiakas(){
	    id = i++;

		saapumisaika = Kello.getInstance().getAika();
		System.out.println("Uusi asiakas nro " + id + " saapui klo " + saapumisaika);
	}

	/**
	 * @return the palvelunPaattymisAika
	 */
	public double getPoistumisaika() {
		return poistumisaika;
	}

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}

	public int getId() {
		return id;
	}

	/**
	 * Ohjelman loputtua tulostetaan asiakkaan tiedot
	 * @return the palvelunPaattymisAika
	 */
	public void raportti(){
		System.out.println("\nAsiakas "+id+" valmis!");
		System.out.println("Asiakas "+id+ " saapui: " +saapumisaika);
		System.out.println("Asiakas "+id+ " poistui: " +poistumisaika);
		System.out.println("Asiakas "+id+ " viipyi: " +(poistumisaika-saapumisaika));
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo tähän asti " + keskiarvo);
	}
}