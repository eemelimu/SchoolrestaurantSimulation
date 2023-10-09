package simu.model;

import simu.framework.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.*;
import eduni.distributions.ContinuousGenerator;

public class Palvelupiste {

	private final LinkedList<Asiakas> jono = new LinkedList<>(); // Tietorakennetoteutus
	private final PriorityQueue<Asiakas> palveltavienJono = new PriorityQueue<>(new AsiakasComparator()); // Tietorakennetoteutus
	private final ContinuousGenerator generator;
	private final Tapahtumalista tapahtumalista;
	private final TapahtumanTyyppi skeduloitavanTapahtumanTyyppi;
	private final String palvelupisteNimi;
	private final int maksimiAsiakasKapasiteetti; // Kuinka monta asiakasta voi olla palvelupisteellä samaan aikaan
	private int asiakasLkm; // Kuinka monta asiakasta on käynyt palvelupisteellä
	private int nykyisetAsiakkaat; // Kuinka monta asiakasta on tällä hetkellä palvelupisteellä
	private double palvelupisteenKokonaisAika; // Kuinka kauan palvelupiste on ollut käytössä

	private boolean varattu = false;
	private int suurinJono = 0;

	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, int maksimiAsiakasKapasiteetti, String palvelupisteNimi){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.maksimiAsiakasKapasiteetti = maksimiAsiakasKapasiteetti;
		this.palvelupisteNimi = palvelupisteNimi;
		this.nykyisetAsiakkaat = 0;
	}

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

	public int getMaksimiAsiakasKapasiteetti() {
		return this.maksimiAsiakasKapasiteetti;
	}

	public int getNykyisetAsiakkaat() {
		return this.nykyisetAsiakkaat;
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
			//e.printStackTrace();
			System.out.println("Jono tyhjä.");
		}
	}

	public boolean onVarattu(){
		return varattu;
	}

	public boolean onJonossa(){
		return jono.size() != 0;
	}

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