package simu.model;

import simu.framework.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.*;
import eduni.distributions.ContinuousGenerator;
/**
 * Palvelupiste-luokka
 */
public class Palvelupiste {

	private final LinkedList<Asiakas> jono = new LinkedList<>(); // Tietorakennetoteutus
	private final PriorityQueue<Asiakas> palveltavienJono = new PriorityQueue<>(new AsiakasComparator()); // Tietorakennetoteutus
	private final ContinuousGenerator generator;
	private final Tapahtumalista tapahtumalista;
	private final TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
	private final String palvelupisteNimi;
	private final int maksimiAsiakasKapasiteetti; // Kuinka monta asiakasta voi olla palvelupisteellä samaan aikaan
	private int asiakasLkm; // Kuinka monta asiakasta on käynyt palvelupisteellä
	private double palvelupisteenKokonaisAika; // Kuinka kauan palvelupiste on ollut käytössä
	private int eka_parametri;
	private int toka_parametri;

	private boolean varattu = false;
	private int suurinJono = 0;

	/**
	 * Konstruktori
	 * @param generator
	 * @param tapahtumalista
	 * @param tyyppi
	 * @param maksimiAsiakasKapasiteetti
	 * @param palvelupisteNimi
	 */
	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, int maksimiAsiakasKapasiteetti, String palvelupisteNimi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.maksimiAsiakasKapasiteetti = maksimiAsiakasKapasiteetti;
		this.palvelupisteNimi = palvelupisteNimi;
	}

	/**
	 * Lisätään asiakas jonoon
	 * @param a
	 */
	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		checkSuurinJono();
	}

	public void checkSuurinJono() {
		if (jono.size() > this.suurinJono) {
			this.suurinJono = jono.size();
		}
	}

	public int getSuurinJono() {
		return this.suurinJono;
	}

	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut
		return jono.poll();
	}

	public Asiakas otaPalveltavienJonosta() {
		varattu = false;
		return this.palveltavienJono.poll();
	}

	public int getPalveltavienJonoSize() {
		return this.palveltavienJono.size();
	}

	public void setEka_parametri(int eka_parametri) {
		this.eka_parametri = eka_parametri;
	}

	public void setToka_parametri(int toka_parametri) {
		this.toka_parametri = toka_parametri;
	}

	public int getEka_parametri() {
		return this.eka_parametri;
	}

	public int getToka_parametri() {
		return this.toka_parametri;
	}

	public int getMaksimiAsiakasKapasiteetti() {
		return this.maksimiAsiakasKapasiteetti;
	}

	public String getPalvelupisteNimi() {
		return palvelupisteNimi;
	}

	public double getPalvelupisteenKokonaisAika() {
		return palvelupisteenKokonaisAika;
	}

	public int getAsiakasLkm() {
		return this.asiakasLkm;
	}

	public int getJonoSize() {
		return this.jono.size();
	}

	/**
	 * Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
	 */
	public void aloitaPalvelu(){  // Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		try  {
			palveltavienJono.add(otaJonosta());
            assert jono.peek() != null;
			System.out.println("Aloitetaan uusi palvelu asiakkaalle " + palveltavienJono.peek().getId());
			this.asiakasLkm++;
			System.out.println("Palvelupiste " + this.palvelupisteNimi + " on täynnä.");
			if (palveltavienJono.size() >= getMaksimiAsiakasKapasiteetti()) {
				varattu = true;
			}
			double palveluaika = generator.sample();
			this.palvelupisteenKokonaisAika += palveluaika;
			tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
		} catch (NullPointerException e) {
			System.out.println("Jono tyhjä.");
		}
	}

	public boolean onVarattu(){
		return varattu;
	}

	public boolean onJonossa(){
		return jono.size() != 0;
	}

	/**
	 * Comparator for sorting the list by roll no
	 */
	class AsiakasComparator implements Comparator<Asiakas>{
		// Overriding compare()method of Comparator
		// for descending order of cgpa
		public int compare(Asiakas a1, Asiakas a2) {
			if (a1.palvelunPaattymisAika < a2.palvelunPaattymisAika)
				return -1;
			else if (a1.palvelunPaattymisAika > a2.palvelunPaattymisAika)
				return 1;
			return 0;
		}
	}
}