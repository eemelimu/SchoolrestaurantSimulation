package simu.model;

import eduni.distributions.Normal;
import simu.framework.*;

// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	protected double palvelunPaattymisAika;
	private int id;
	private static int i = 1;
	private static long sum = 0;
	
	public Asiakas(){
	    id = i++;
	    
		saapumisaika = Kello.getInstance().getAika();
		System.out.println("Uusi asiakas nro " + id + " saapui klo " + saapumisaika);
	}

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
